package servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.PoopDAO;
import dto.AllDto;



/**
 * Servlet implementation class RegistServlet
 */
@WebServlet("/PoopServlet")
public class PoopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
				HttpSession session = request.getSession();
				if (session.getAttribute("user") == null) {
					response.sendRedirect(request.getContextPath()+"/LoginServlet");
					return;
				}
				
				String action = request.getParameter("action");
				
				if("poopregi".equals(action)) {
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/poop_regi.jsp");
					dispatcher.forward(request, response);
				}
				
		//ウンチ登録に画面遷移
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/poop_list.jsp");
		dispatcher.forward(request, response);
	}
	 


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
				HttpSession session = request.getSession();
				if (session.getAttribute("user") == null) {
					response.sendRedirect(request.getContextPath()+"/LoginServlet");
					return;
				}
		//登録ボタンが押されたら	
	
		
		// リクエストパラメータを取得する
		
	request.setCharacterEncoding("UTF-8");
	String action = request.getParameter("action");
	
	int  poopId =Integer.parseInt(request.getParameter("poopId"));
	String tlName =request.getParameter("tlname");
	LocalDateTime  nowtime=LocalDateTime.parse(request.getParameter("nowtime"));
	String photo =request.getParameter("photo");
	int  hardness =Integer.parseInt(request.getParameter("hardness"));
	boolean  abnormal = Boolean.parseBoolean(request.getParameter("abnormal"));
	int  poopdogid =Integer.parseInt(request.getParameter("poopdogid"));
	String memo =request.getParameter("memo");
	LocalDate date =LocalDate.parse(request.getParameter("date"));
	
	

//登録処理を行う
		PoopDAO bDao = new PoopDAO();
		AllDto pDto = new AllDto();
		pDto.setPoopId(poopId);
		pDto.setTlName(tlName);
		pDto.setNowTime(nowtime);
		pDto.setPhoto(photo);
		pDto.setHardness(hardness);
		pDto.setAbnormal(abnormal);
		pDto.setPoopDogId(poopdogid);
		pDto.setMemo(memo);
		pDto.setDate(date);
		
		
		int ans = bDao.insert(pDto);
		
		if( ans == 1) {
			request.setAttribute("msg","登録完了");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/poop_regi.jsp");
			dispatcher.forward(request, response);
		}else {
			request.setAttribute("msg","登録できなかったよ");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/poop_regi.jsp");
			dispatcher.forward(request, response);
		}
		
	
		//検索
			// 検索処理を行う
			
			List<AllDto> poopList = bDao.select(pDto);

			// 検索結果をリクエストスコープに格納する
			request.setAttribute("poopList", poopList);

			// 結果ページにフォワードする
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/poop_detail.jsp");
			dispatcher.forward(request, response);
			
			//更新・削除
			if("uppdate".equals(action)) {
				if (bDao.update(pDto)) {
					request.setAttribute("message", "レポートの更新に成功しました。");
					dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/poop_detail.jsp");
					dispatcher.forward(request, response);	
				
			} else { 
				request.setAttribute("error", "レポートの更新に失敗しました。");
				dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/poop_detail.jsp");
				dispatcher.forward(request, response);
			}
			
		}else  if("delete".equals(action)) {
			if (bDao.delete(pDto)) {
				request.setAttribute("message", "レポートの削除に成功しました。");
				 dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/poop_detail.jsp");
				dispatcher.forward(request, response);
			} else { 
				request.setAttribute("error", "レポートの削除に失敗しました。");
				dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/poop_detail.jsp");
				dispatcher.forward(request, response);
				
			}
		
			/*
			// 更新または削除を行う
			if (request.getParameter("submit").equals("更新")) {
				if (bDao.update(new AllDto())) { // 更新成功
					request.setAttribute("result", new Result("更新成功！", "レコードを更新しました。", "/servlet/PoopServlet"));
				} else { // 更新失敗
					request.setAttribute("result", new Result("更新失敗！", "レコードを更新できませんでした。", "/servlet/PoopServlet"));
				}
			} else {
				if (bDao.delete(new AllDto())) { // 削除成功
					request.setAttribute("result", new Result("削除成功！", "レコードを削除しました。", "/servlet/PoopServlet"));
				} else { // 削除失敗
					request.setAttribute("result", new Result("削除失敗！", "レコードを削除できませんでした。", "/servlet/PoopServlet"));
				}
			}*/
			// 結果ページにフォワードする
			dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/poop_list.jsp");
			dispatcher.forward(request, response);
		}
	}
}
