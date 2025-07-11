package servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.CalendarDAO;
import dao.WankoDAO;
import dto.AllDto;
import dto.ScDto;
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
		AllDto user = null;
		
		//カレンダーの各タイトル情報を表示 
		 user = (AllDto) session.getAttribute("user");
		 int userSchoolId = user.getUserSchoolId();
		 String userNameId = user.getUserNameId(); 
		 
		 
		 
		 CalendarDAO cdao = new CalendarDAO();
		 List <AllDto> scheduleList = cdao.scheduleTitle(userSchoolId);
		 
		 ArrayList<ScDto> list = new ArrayList<>();
		 for(AllDto dto : scheduleList) {
			 ScDto d = new ScDto();
			 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			 d.setCalendarDate(dto.getCalendarDate().format(formatter));
			 d.setDogName(dto.getDogName());
			 d.setTitle(dto.getTitle());
			 d.setUserSchoolId(dto.getUserSchoolId());
			 d.setWankoDogId(dto.getWankoDogId());
			 list.add(d);
		}
		 
		 ObjectMapper mapper = new ObjectMapper();
		 String json = mapper.writeValueAsString(list); // ← JSON文字列に変換
		 request.setAttribute("scheduleListJson", json);
		 
		 System.out.println("★ userSchoolId = " + userSchoolId);
		 System.out.println(scheduleList.size());

		//トレーナー側
		if(log.isUserUniqueId() == true ) {
			
			//schoolIdをとる
			user = (AllDto) session.getAttribute("user");
			WankoDAO wdao = new WankoDAO();
//			int userSchoolId = user.getUserSchoolId();
			List<AllDto> todayWanko = wdao.todaywanko(userSchoolId);
			//今日のワンコセッション入れた
			session.setAttribute("todaysDog",todayWanko);
			
			
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/t_home.jsp");
			dispatcher.forward(request, response);
			
		//飼い主側
		}else if(log.isUserUniqueId() == false ) {
			

			//ログインわんこの表示
			user = (AllDto) session.getAttribute("user");
			
			WankoDAO wdao = new WankoDAO();
			
//			String userNameId = user.getUserNameId();
			
			AllDto uDog = wdao.logdog(userNameId);
			System.out.println("uDog: " + uDog);
			
			//ログインわんこをセッションスコープに入れとく
			session.setAttribute("logdog",uDog);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/o_home.jsp");
			dispatcher.forward(request, response);
			
			
		}

		 
//		 ObjectMapper mapper = new ObjectMapper();
//		 String json = mapper.writeValueAsString(scheduleList); // ← JSON文字列に変換
//		 request.setAttribute("scheduleList", json);
//		 
//		 System.out.println("★ userSchoolId = " + userSchoolId);
//		 System.out.println(scheduleList.size());

		 LocalDate today = LocalDate.now(); int year = today.getYear(); int month =today.getMonthValue();
		 
//		 // わんこの予定を取得 
//		 //int dogId = user.getWankoDogId(); 
//		 CalendarDAO cdao = new CalendarDAO(); 
//		 List<AllDto> calendarList = cdao.selectDogIdAndMonth(dogId,year, month); 
//		 request.setAttribute("calendarList", calendarList);
//		 System.out.println(dogId+"サイズだよ");
//		 System.out.println(calendarList.size()+"サイズだよ");
//		 for(AllDto dto : calendarList) {
//			 System.out.println(dto.getCalendarDate()+"aaaa");
//			 System.out.println(dto.getTitle()+"aaaa");
		 }
		 
	}
	


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

	}
	}

