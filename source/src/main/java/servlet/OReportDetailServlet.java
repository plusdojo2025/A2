package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ReportDAO;
import dto.AllDto;

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
		if (request.getParameter("ぼたん").equals("nakami")) {
			int reportId = Integer.parseInt(request.getParameter("reportId"));
		
		// 検索処理を行う
		ReportDAO rDao = new ReportDAO();
		List<AllDto> rdList = rDao.select(reportId);

		// 検索結果をリクエストスコープに格納する
		request.setAttribute("oReportDetailList", rdList);
		
		// 飼い主報告詳細ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/o_report_detail.jsp");
		dispatcher.forward(request, response);
	
		}
	}

}
