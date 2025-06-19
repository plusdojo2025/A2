package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CalendarDAO;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet(urlPatterns = "/HomeServlet")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		if (session.getAttribute("id") == null) {
			response.sendRedirect("/webapp/LoginServlet");
			return;
		}
		
		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		String calendarId = request.getParameter("calendarId");
		String calendarDate = request.getParameter("calendarDate");
		String title = request.getParameter("title");
		String time = request.getParameter("time");
		String calendarMemo = request.getParameter("calendarMemo");
		String calendarDogId = request.getParameter("calendarDogId");
		
		CalendarDAO cDao = new CalendarDAO();
		
		//List<AllDto> calendar = cDao.select(new AllDto(calendarId, calendarDate, title, time, calendarMemo, calendarDogId));
		
		// メニューページにフォワードする
		if(session.getAttribute("userUniqueId") == Boolean.TRUE) {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/t_home.jsp");
		dispatcher.forward(request, response);
		}else if(session.getAttribute("userUniqueId") == Boolean.FALSE){
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/o_home.jsp");
			dispatcher.forward(request, response);	
		}
		}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
