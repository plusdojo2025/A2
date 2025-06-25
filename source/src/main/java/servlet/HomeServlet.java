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

import dao.CalendarDAO;
import dao.WankoDAO;
import dto.AllDto;
/**
 * Servlet implementation class HomeServlet
 */
@WebServlet(urlPatterns = "/HomeServlet")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
			
		HttpSession session = request.getSession();
		if (session.getAttribute("user") == null) {
			response.sendRedirect(request.getContextPath() +"/LoginServlet");
			return;
		}else {
		AllDto log = (AllDto)session.getAttribute("user");
		// メニューページにフォワードする
		//トレーナー側
		if(log.isUserUniqueId() == true ) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/t_home.jsp");
			dispatcher.forward(request, response);
			
		//飼い主側
		}else if(log.isUserUniqueId() == false ) {
			

			//ログインわんこの表示
			AllDto user = (AllDto) session.getAttribute("user");
			String userNameId = user.getUserNameId();
			WankoDAO wdao = new WankoDAO();
			AllDto uDog = wdao.logdog(userNameId);
			System.out.println("uDog: " + uDog);
			//ログインわんこをセッションスコープに入れとく
			session.setAttribute("logdog",uDog);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/o_home.jsp");
			dispatcher.forward(request, response);
			
			
			
		}
		//カレンダーの各タイトル情報を表示
		AllDto user = (AllDto) session.getAttribute("user");
		String userNameId = user.getUserNameId();
		WankoDAO wdao = new WankoDAO();
		AllDto uDog = wdao.logdog(userNameId);
		System.out.println("uDog: " + uDog);
		
		int dogId = uDog.getWankoDogId();
		
		CalendarDAO cdao = new CalendarDAO();
		List<AllDto> calendarList = cdao.selectDogIdAndMonth(dogId, year, month);
		request.setAttribute("calendarList", calendarList);
		
		}
		}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

	}
	}

