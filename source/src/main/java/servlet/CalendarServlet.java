package servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
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
			response.sendRedirect("<c:url value='/LoginServlet' />");
			return;
		}else {//最初のアクション（予定登録を表示）
			//リクエストパラメータの取得
	        //String action = request.getParameter("action");
	        int year = Integer.parseInt(request.getParameter("year"));
	        int month = Integer.parseInt(request.getParameter("month"));
	        int count = Integer.parseInt(request.getParameter("count"));
	        
	        //Dateになおす
	        LocalDate date = LocalDate.of(year, month, count);
	        //
	        request.setAttribute("selectedDate", date);
			//リクエストパラメーター２ request.setCharacterEncoding("UTF-8"); String dogName =
			 request.getParameter("dogName");
			 
	        
			// 検索処理を行う //カレンダーDAOで作ったscheList
			CalendarDAO CaleDao = new CalendarDAO();
			int schoolId = log.getUserSchoolId();
			System.out.println(date+"kkkkk"+schoolId +"aaaaa");
			List<AllDto> scheList = CaleDao.selectAll(date,schoolId);
			// 検索結果をリクエストスコープに格納する
			request.setAttribute("scheList", scheList);
			System.out.println(scheList.size()+"bbbbbbbbb");

		    
			
			 // ワンコ一覧を取得 
		    int userSchoolId = log.getUserSchoolId();
		    List<AllDto> wankoList= CaleDao.selectWanko(userSchoolId);
			request.setAttribute("wankoList",wankoList); 
			for(AllDto dto : wankoList) {
				System.out.println(dto.getDogName()+"aaaaa"); }
					   //System.out.println("wankoList: "+wankoList); 
		   
			// 結果ページにフォワードする
		    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/schedule_regi.jsp");
		    dispatcher.forward(request, response);
					}

	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		
		CalendarDAO cDao = new CalendarDAO();
		AllDto cDto = new AllDto();
		//cDto.setCalendarId(calendarId);
		
		//登録のゲットパラメータ
		if("regist".equals(action) ) {
			
			//int calendarId = Integer.parseInt(request.getParameter("calendarId"));
			//LocalDateTime calendarDate = LocalDateTime.parse(request.getParameter("calendarDate"));
			
			String title = request.getParameter("title");
			LocalTime nowTime = LocalTime.parse(request.getParameter("nowTime"));
			//System.out.println("aiueo"+nowTime);
			String memo = request.getParameter("memo");
			String dogIdString = request.getParameter("calendarDogId");
				int calendarDogId = 0;
				//dogIdのNullチェック
				if (dogIdString != null && !dogIdString.isEmpty()) {
				    try {
				        calendarDogId = Integer.parseInt(dogIdString);
				    } catch (NumberFormatException e) {
				        System.out.println("calendarDogIdが数字ではありません: " + dogIdString);
				        // エラー処理（必要なら）
				    }
				} else {
				    System.out.println("calendarDogIdがnullまたは空文字です");
				    // エラー処理（またはデフォルトのまま処理を続ける）
				}
				
			//LocalDate selectedDate = (LocalDate) session.getAttribute("selectedDate");
			int year = Integer.parseInt(request.getParameter("year"));
	        int month = Integer.parseInt(request.getParameter("month"));
	        int count = Integer.parseInt(request.getParameter("count"));
	        
	        //Dateになおす
	        LocalDate date = LocalDate.of(year, month, count);
	        
	        //
	        request.setAttribute("selectedDate", date);
				
			cDto.setCalendarDate(date);
			cDto.setTitle(title);
			cDto.setTime(nowTime);
			cDto.setCalendarMemo(memo);
			cDto.setCalendarDogId(calendarDogId);
			//cDto.setCalendarDogId(calendarDogId);
			
		//更新のゲットパラメータ―とか
		}else if("update".equals(action)) {
			
			//int calendarId = Integer.parseInt(request.getParameter("calendarId"));
			//LocalDateTime calendarDate = LocalDateTime.parse(request.getParameter("calendarDate"));
			
			String calendarIdStr = request.getParameter("calendarId");
			String title = request.getParameter("title");
			LocalTime nowTime = LocalTime.parse(request.getParameter("nowTime"));
			//System.out.println("aiueo"+nowTime);
			String memo = request.getParameter("memo");
			String dogIdStr = request.getParameter("calendarDogId");
			
			/*
			 * int calendarDogId = 0; //dogIdのNullチェック if (dogIdString != null &&
			 * !dogIdString.isEmpty()) { try { calendarDogId =
			 * Integer.parseInt(dogIdString); } catch (NumberFormatException e) {
			 * System.out.println("calendarDogIdが数字ではありません: " + dogIdString); // エラー処理（必要なら）
			 * } } else { System.out.println("calendarDogIdがnullまたは空文字です"); //
			 * エラー処理（またはデフォルトのまま処理を続ける） }
			 */
				
			//LocalDate selectedDate = (LocalDate) session.getAttribute("selectedDate");
			int year = Integer.parseInt(request.getParameter("year"));
	        int month = Integer.parseInt(request.getParameter("month"));
	        int count = Integer.parseInt(request.getParameter("count"));
	        
	        //Dateになおす
	        LocalDate date = LocalDate.of(year, month, count);
	        
	        //
	        request.setAttribute("selectedDate", date);
	        
	        int calendarDogId = Integer.parseInt(dogIdStr);
	        int calendarId = Integer.parseInt(calendarIdStr);
				
			cDto.setCalendarDate(date);
			cDto.setTitle(title);
			cDto.setTime(nowTime);
			cDto.setCalendarMemo(memo);
			cDto.setCalendarDogId(calendarDogId);
			cDto.setCalendarId(calendarId);
			
		//削除のゲットパラメータとか
		}else if("delete".equals(action)) {
			
			//int calendarId = Integer.parseInt(request.getParameter("calendarId"));
			//LocalDateTime calendarDate = LocalDateTime.parse(request.getParameter("calendarDate"));
			
			String calendarIdStr = request.getParameter("calendarId");
			System.out.println("削除対象ID：" + calendarIdStr);
			/*
			 * String title = request.getParameter("title"); LocalTime nowTime =
			 * LocalTime.parse(request.getParameter("nowTime"));
			 * //System.out.println("aiueo"+nowTime); String memo =
			 * request.getParameter("memo"); String dogIdStr =
			 * request.getParameter("calendarDogId");
			 */
				
			//LocalDate selectedDate = (LocalDate) session.getAttribute("selectedDate");
			int year = Integer.parseInt(request.getParameter("year"));
	        int month = Integer.parseInt(request.getParameter("month"));
	        int count = Integer.parseInt(request.getParameter("count"));
	        
	        //Dateになおす
	        LocalDate date = LocalDate.of(year, month, count);
	        
	        //
	        request.setAttribute("selectedDate", date);
	        
			/*
			 * int calendarDogId = Integer.parseInt(dogIdStr);*/	   
	        int calendarId = Integer.parseInt(calendarIdStr);
				
			/*
			 * cDto.setCalendarDate(date); 
			 * cDto.setTitle(title); 
			 * cDto.setTime(nowTime);
			 * cDto.setCalendarMemo(memo); 
			 * cDto.setCalendarDogId(calendarDogId);
			 */
			cDto.setCalendarId(calendarId);
		}
		
		
		if("regist".equals(action)) {
			if (cDao.insert(cDto)) {
				
				//
				//リクエストパラメータの取得
		        //String action = request.getParameter("action");
		        int year = Integer.parseInt(request.getParameter("year"));
		        int month = Integer.parseInt(request.getParameter("month"));
		        int count = Integer.parseInt(request.getParameter("count"));
		        //リダイレクトする
		        response.sendRedirect(request.getContextPath() + "/CalendarServlet?year=" + year + "&month=" + month + "&count=" + count);
		        session.setAttribute("message", "登録に成功しました。");
		        return;
		        
		        
				/*
				 * //フォワード RequestDispatcher dispatcher =
				 * request.getRequestDispatcher("/WEB-INF/jsp/schedule_regi.jsp");
				 * dispatcher.forward(request, response);
				 */
			} else { 
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/schedule_regi.jsp");
				dispatcher.forward(request, response);
				session.setAttribute("message", "登録に失敗しました。");
			}
		}else if ("update".equals(action)) {
			if (cDao.update(cDto)) {
				//リクエストパラメータの取得
		        //String action = request.getParameter("action");
		        int year = Integer.parseInt(request.getParameter("year"));
		        int month = Integer.parseInt(request.getParameter("month"));
		        int count = Integer.parseInt(request.getParameter("count"));
		        //リダイレクトする
		        response.sendRedirect(request.getContextPath() + "/CalendarServlet?year=" + year + "&month=" + month + "&count=" + count);
		        session.setAttribute("message", "更新に成功しました。");
				return;
			} else { 
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/schedule_regi.jsp");
				dispatcher.forward(request, response);
				session.setAttribute("message", "更新に失敗しました。");
			}
		}else if ("delete".equals(action)) {
			System.out.println("delete入ったよ");
			
			if (cDao.delete(cDto)) {
				//リクエストパラメータの取得
		        //String action = request.getParameter("action");
		        int year = Integer.parseInt(request.getParameter("year"));
		        int month = Integer.parseInt(request.getParameter("month"));
		        int count = Integer.parseInt(request.getParameter("count"));
		        //リダイレクトする
		        response.sendRedirect(request.getContextPath() + "/CalendarServlet?year=" + year + "&month=" + month + "&count=" + count);
		        session.setAttribute("message", "削除に成功しました。");
		        return;
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/schedule_regi.jsp");
				dispatcher.forward(request, response); 
				session.setAttribute("message", "削除に失敗しました。");
			}
		
		}
			
	}	
	}

