package servlet;

import java.io.IOException;

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
 * Servlet implementation class ReportServlet
 */
@WebServlet("/ReportServlet")
public class ReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	/**
//	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
//	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		if (session.getAttribute("id") == null) {
			response.sendRedirect("A2/LoginServlet");
			return;
		}
	
		// 後でやるにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/report_regi.jsp/");
		dispatcher.forward(request, response);
	}
	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		if (session.getAttribute("id") == null) {
			response.sendRedirect("A2/LoginServlet");
			return;
		}

		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		
		//共通で使うID
		String reportId = request.getParameter("reportId");
		String reportDogId = request.getParameter("reportDogId");
		
		ReportDAO rDao = new ReportDAO();
		AllDto rDto = new AllDto();
		
		if("insert".equals(action) || "update".equals(action)) {
		Boolean food = Boolean.parseBoolean(request.getParameter("food"));
		int walk = Integer.parseInt(request.getParameter("walk"));
		Boolean reportState = Boolean.parseBoolean(request.getParameter("reportState"));
		String training = request.getParameter("training");
		String reportMemo = request.getParameter("reportMemo");
		String reportDate = request.getParameter("reportDate");
		
		
		rDto.setReportId(reportId);
		rDto.setFood(food);
		rDto.setWalk(walk);
		rDto.setReportState(reportState);
		rDto.setTraining(training);
		rDto.setReportMemo(reportMemo);
		rDto.setReportDate(reportDate);
		rDto.setReportDogId(reportDogId);
		}
		
		if("insert".equals(action)) {
			if (rDao.insert(rDto)) {
				request.setAttribute("message", "レポートの登録に成功しました。");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/o_report_list.jsp");
				dispatcher.forward(request, response);
			} else { 
				request.setAttribute("error", "レポートの登録に失敗しました。");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/report_regi.jsp");
				dispatcher.forward(request, response);
			}
		}else if ("update".equals(action)) {
			if (rDao.update(rDto)) {
				request.setAttribute("message", "レポートの更新に成功しました。");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/o_report_list.jsp");
				dispatcher.forward(request, response);
			} else { 
				request.setAttribute("error", "レポートの更新に失敗しました。");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/report_regi.jsp");
				dispatcher.forward(request, response);
			}
		}else if ("delete".equals(action)) {
			if (rDao.delete(reportId)) {
				request.setAttribute("message", "レポートの削除に成功しました。");
				
			} else { 
				request.setAttribute("error", "レポートの削除に失敗しました。");
		}
		}}}
	
	
	
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
//    public ReportServlet() {
//        super();
//        // TODO Auto-generated constructor stub
//    }

//	/**
//	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//	}
//
//	/**
//	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		doGet(request, response);
//	}


