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
		
		String company = request.getParameter("company");
		String date = request.getParameter("birth");
		
		
		
		
		private int reportId;				/*報告ID*/
		 private boolean food;		/*ごはん*/
		 private int walk;				/*散歩*/
		 private boolean reportState;		/*様子*/
		 private String training;				/*トレーニング*/
		 private String reportMemo;			/*メモ*/
		 private LocalDate reportDate;		/*日付*/
		 private int reportDogId;	
		String company = request.getParameter("company");
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		
		
		
		// 飼い主報告詳細ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/o_report_detail.jsp");
		dispatcher.forward(request, response);
	
	}

}
