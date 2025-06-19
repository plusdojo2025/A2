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
import dto.AllDto;
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
			response.sendRedirect("A2/LoginServlet");
			return;
		}
		
		// リクエストパラメータを取得するカレンダー
		
		request.setCharacterEncoding("UTF-8");
		String calendarDate = request.getParameter("calendarDate");
		
		CalendarDAO cDao = new CalendarDAO();
		AllDto cDto = new AllDto();
		
		cDto.setCalendarDate(calendarDate);
		
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
