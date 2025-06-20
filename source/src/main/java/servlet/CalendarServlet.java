package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.AllDto;

/**
 * Servlet implementation class CalendarServlet
 */
@WebServlet("/CalendarServlet")
public class CalendarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		//セッションからログイン者情報を取得
		AllDto log = (AllDto)session.getAttribute("user");
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		if (session.getAttribute("id") == null) {
			response.sendRedirect("/A2/LoginServlet");
			return;
		}else {//最初のアクション（予定登録を表示）
			//リクエストパラメータの取得
	        String action = request.getParameter("action");
	        String yearString = request.getParameter("year");
	        String monthString = request.getParameter("month");
	        String countString = request.getParameter("count");
	        
	        
		    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/schedule-regi.jsp");
		    dispatcher.forward(request, response);
		}

	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
