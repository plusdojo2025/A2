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

import dao.CalendarDAO;
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
		if (log == null) {
			response.sendRedirect("/LoginServlet");
			return;
		}else {//最初のアクション（予定登録を表示）
			//リクエストパラメータの取得
	        String action = request.getParameter("action");
	        int year = Integer.parseInt(request.getParameter("year"));
	        int month = Integer.parseInt(request.getParameter("month"));
	        int count = Integer.parseInt(request.getParameter("count"));
	        //Dateになおす
	        LocalDate date = LocalDate.of(year, month, count);
	        
			// 検索処理を行う //カレンダーDAOで作ったpublicList
			CalendarDAO CaleDao = new CalendarDAO();
			List<AllDto> scheList = CaleDao.selectAll(date);

			// 検索結果をリクエストスコープに格納する
			request.setAttribute("scheList", scheList);

			// 結果ページにフォワードする
		    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/schedule-regi.jsp");
		    dispatcher.forward(request, response);
		}

	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
