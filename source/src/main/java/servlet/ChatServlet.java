package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SampleDAO;

@WebServlet("/SoServlet")
public class ChatServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // JSPにフォワードするで
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/chat.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // リクエストのエンコーディングを設定するで
        request.setCharacterEncoding("UTF-8");
        // フォームからのパラメータを取得するで
        String chatString = request.getParameter("chat_string");
        String userIDSpeaker = request.getParameter("user_id_speaker");
        String userIDListener = request.getParameter("user_id_listener");
        String inputTime = request.getParameter("input_time");

        // データベースにデータを挿入するで
        SampleDAO dao = new SampleDAO();
        int ans = dao.insert(userIDSpeaker, userIDListener, chatString, null, 0, inputTime);

        // 挿入が成功したら、JSPにフォワードするで
        if(ans == 1) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/chat.jsp");
            dispatcher.forward(request, response);
        }
    }
}