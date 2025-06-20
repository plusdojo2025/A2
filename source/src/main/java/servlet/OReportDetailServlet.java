package servlet;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class OReportDetailServlet
 */
@WebServlet("/OReportDetailServlet")
public class OReportDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		if (session.getAttribute("id") == null) {
			response.sendRedirect("/webapp/LoginServlet");
			return;
		}
	
		// 飼い主報告詳細ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/o_report_detail.jsp");
		dispatcher.forward(request, response);
	
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		if (session.getAttribute("id") == null) {
			response.sendRedirect("/webapp/LoginServlet");
			return;
		}
	
		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		int reportId = Integer.parseInt(request.getParameter("reportId"));
		boolean food = Boolean.parseBoolean(request.getParameter("food"));
		int walk = Integer.parseInt(request.getParameter("walk"));
		boolean reportState = Boolean.parseBoolean(request.getParameter("reportState"));
		String training = request.getParameter("training");
		String reportMemo = request.getParameter("food");
		LocalDate reportDate =LocalDate.parse(request.getParameter("repoertDate"));
		int reportDogId = Integer.parseInt(request.getParameter("reportDogId"));
		
		
		
		// 飼い主報告詳細ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/o_report_detail.jsp");
		dispatcher.forward(request, response);
	
	}

}
