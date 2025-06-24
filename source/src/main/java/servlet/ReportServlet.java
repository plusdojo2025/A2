package servlet;

import java.io.IOException;
import java.time.LocalDate;
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
		if (session.getAttribute("user") == null) {
			response.sendRedirect(request.getContextPath() +"/LoginServlet");
			return;
		} else {
			AllDto log = (AllDto)session.getAttribute("user");
			//トレーナー側と飼い主側それぞれ別ページにフォワードする
			//トレーナー側
			if(log.isUserUniqueId() == true) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/report_regi.jsp");
				dispatcher.forward(request, response);
				
			//飼い主側
			}else if(log.isUserUniqueId() == false) {
				// レポート一覧を表示
				int wankoDogId = log.getWankoDogId();
				ReportDAO rdao = new ReportDAO();
				List<AllDto> reportList = rdao.select(wankoDogId);
				request.setAttribute("reportList", reportList);
				// 後でやるにフォワードする
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/o_report_list.jsp");
				dispatcher.forward(request, response);
				// DAOからユーザー情報とってきて、データベースをもとに報告ｉｄをとってくる
				
					
			}
		}
		}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		if (session.getAttribute("user") == null) {
			response.sendRedirect(request.getContextPath() + "/LoginServlet");
			return;
		}

		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		
		
		//共通で使うID
		int reportId = Integer.parseInt(request.getParameter("reportId"));
		int reportDogId = Integer.parseInt(request.getParameter("reportDogId"));
		
		ReportDAO rDao = new ReportDAO();
		AllDto rDto = new AllDto();
		rDto.setReportId(reportId);
		rDto.setReportDogId(reportDogId);
		
		if("insert".equals(action) || "update".equals(action)) {
		Boolean food = Boolean.parseBoolean(request.getParameter("food"));
		int walk = Integer.parseInt(request.getParameter("walk"));
		Boolean reportState = Boolean.parseBoolean(request.getParameter("reportState"));
		String training = request.getParameter("training");
		String reportMemo = request.getParameter("reportMemo");
		LocalDate reportDate = LocalDate.parse(request.getParameter("reportDate"));
		
		
		rDto.setFood(food);
		rDto.setWalk(walk);
		rDto.setReportState(reportState);
		rDto.setTraining(training);
		rDto.setReportMemo(reportMemo);
		rDto.setReportDate(reportDate);
		
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
			if (rDao.delete(rDto)) {
				request.setAttribute("message", "レポートの削除に成功しました。");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/o_report_list.jsp");
				dispatcher.forward(request, response);
			} else { 
				request.setAttribute("error", "レポートの削除に失敗しました。");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/report_regi.jsp");
				dispatcher.forward(request, response);
			}
		}else if (request.getParameter("ぼたん").equals("nakami")) {
			
		
		// 検索処理を行う
		List<AllDto> rdList = rDao.select(reportId);

		// 検索結果をリクエストスコープに格納する
		request.setAttribute("oReportDetailList", rdList);
		
		// 飼い主報告詳細ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/o_report_detail.jsp");
		dispatcher.forward(request, response);
	
		}
	}}
	
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