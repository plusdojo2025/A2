package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SampleDAO {

    // チャットデータをデータベースに挿入するメソッドやで
    public int insert(String user_id_speaker, String user_id_listener, String talk, String image, int check, String inputTime) {
        Connection conn = null;
        int result = 0;

        try {
            // JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sample?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");

            // SQL文を準備するで
            String sql = "INSERT INTO chat (user_id_speaker, user_id_listener, talk, image, `check`, created_at) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement pStmt = conn.prepareStatement(sql);

            // パラメータを設定するで
            pStmt.setString(1, user_id_speaker);
            pStmt.setString(2, user_id_listener);
            pStmt.setString(3, talk);
            pStmt.setString(4, image);
            pStmt.setInt(5, check);
            pStmt.setString(6, inputTime);

            // SQL文を実行するで
            result = pStmt.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            // データベースを切断するで
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
}
