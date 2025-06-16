package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.fasterxml.jackson.databind.ObjectMapper;

@ServerEndpoint("/chat")
public class ChatEndpoint {

    private static final Set<Session> clients = Collections.synchronizedSet(new HashSet<>());
    private static final Logger logger = Logger.getLogger(ChatEndpoint.class.getName());

    @OnOpen
    public void onOpen(Session session) {
        clients.add(session);
        logger.log(Level.INFO, "Connected: {0}", session.getId());
    }

    @OnClose
    public void onClose(Session session) {
        clients.remove(session);
        logger.log(Level.INFO, "Disconnected: {0}", session.getId());
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        logger.log(Level.INFO, "Received message: {0}", message);

        // 初期化メッセージを処理するで
        if (message.startsWith("{")) {
            // JSONメッセージをパースするで
            try {
                ObjectMapper mapper = new ObjectMapper();
                java.util.Map<String, Object> map = mapper.readValue(message, java.util.Map.class);
                if ("init".equals(map.get("type"))) {
                    session.getUserProperties().put("user_id_speaker", map.get("user_id_speaker").toString());
                    session.getUserProperties().put("user_id_listener", map.get("user_id_listener").toString());

                    // チャット履歴を取得して送信するで
                    String user_id_speaker = (String) session.getUserProperties().get("user_id_speaker");
                    String user_id_listener = (String) session.getUserProperties().get("user_id_listener");

                    try {
                        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sample?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");
                        String sql = "SELECT user_id_speaker, user_id_listener, talk, created_at FROM chat WHERE (user_id_speaker = ? AND user_id_listener = ?) OR (user_id_speaker = ? AND user_id_listener = ?) ORDER BY created_at ASC";
                        PreparedStatement pStmt = conn.prepareStatement(sql);
                        pStmt.setString(1, user_id_speaker);
                        pStmt.setString(2, user_id_listener);
                        pStmt.setString(3, user_id_listener);
                        pStmt.setString(4, user_id_speaker);
                        ResultSet rs = pStmt.executeQuery();
                        //ここから重要そう
                        while (rs.next()) {
                            String msg = rs.getString("created_at") + " " + rs.getString("user_id_speaker") + " " + rs.getString("user_id_listener") + " " + rs.getString("talk");
                            session.getBasicRemote().sendText(msg);
                        }

                        rs.close();
                        pStmt.close();
                        conn.close();
                    } catch (SQLException | IOException e) {
                        logger.log(Level.SEVERE, "Error in onOpen", e);
                        e.printStackTrace();
                    }
                }
            } catch (IOException e) {
                logger.log(Level.SEVERE, "Error parsing JSON message", e);
            }
        } else {
            synchronized (clients) {
                for (Session client : clients) {
                    if (client.isOpen()) {
                        client.getBasicRemote().sendText(message);
                    }
                }
            }

            // メッセージをデータベースに保存するで
            String[] parts = message.split(" ", 5); // created_at, user_id_speaker, user_id_listenerとメッセージを分割するで
            if (parts.length >= 5) {
                String createdAt = parts[0] + " " + parts[1];
                String user_id_speaker = parts[2];
                String user_id_listener = parts[3];
                StringBuilder talkBuilder = new StringBuilder();
                for (int i = 4; i < parts.length; i++) {
                    talkBuilder.append(parts[i]).append(" ");
                }
                String talk = talkBuilder.toString().trim();

                SampleDAO dao = new SampleDAO();
                int result = dao.insert(user_id_speaker, user_id_listener, talk, null, 0, createdAt);
                if (result > 0) {
                    logger.log(Level.INFO, "Message saved to database: {0}", message);
                } else {
                    logger.log(Level.WARNING, "Failed to save message to database: {0}", message);
                }
            } else {
                logger.log(Level.WARNING, "Invalid message format: {0}", message);
            }
        }
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        clients.remove(session);
        logger.log(Level.SEVERE, "Error in session " + session.getId(), throwable);
    }
}
