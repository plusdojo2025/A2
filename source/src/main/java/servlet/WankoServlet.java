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

import dao.WankoDAO;
import dto.AllDto;


@WebServlet("/WankoServlet")
public class WankoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		AllDto log = (AllDto)session.getAttribute("user");
		
		// 条件によって画面を振り分ける
		if("home".equals(action)) {
			if(log.isUserUniqueId() == true ) {
				
			}else if(log.isUserUniqueId() == false ) {
			//飼い主用の遷移
			AllDto user = (AllDto) session.getAttribute("user");
			String userNameId = user.getUserNameId();
			WankoDAO wdao = new WankoDAO();
			List<AllDto> owankoList = wdao.olistSelect(userNameId);
			request.setAttribute("owankoList", owankoList);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/dog_list.jsp");
			dispatcher.forward(request, response);
			}
			//犬一覧
		}else if("list".equals(action)) {
			if(log.isUserUniqueId() == true ) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/t_dog_regi.jsp");
				dispatcher.forward(request, response);
			}else if(log.isUserUniqueId() == false ) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/o_dog_regi.jsp");
				dispatcher.forward(request, response);
			}
			//犬詳細
		}else if("dogDetail".equals(action)) {
			
			if(log.isUserUniqueId() == true ) {
				
			}else if(log.isUserUniqueId() == false ) {
			
			request.setCharacterEncoding("UTF-8");
			
			String id = request.getParameter("id");
			System.out.println("aa"+id);
			WankoDAO wdao = new WankoDAO();
			List<AllDto> oDogDet = wdao.oDogDet(id);
			request.setAttribute("oDogDet", oDogDet);
			System.out.print(oDogDet);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/o_dog_detail.jsp");
			dispatcher.forward(request, response);
			}
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//値の取得
		request.setCharacterEncoding("UTF-8");
		// "butt" が押されたときその中身が "登録" なら実行
		if("登録".equals(request.getParameter("butt"))) {
			
			//
			String dogPhoto = request.getParameter("dogPhoto");
			String dogName = request.getParameter("dogName");
			String name = request.getParameter("name");
			String wakuchin = request.getParameter("wakuchin");
			String kyosei = request.getParameter("kyosei");
			String gender = request.getParameter("gender");
			String dogBreed = request.getParameter("dogBreed");
			String dogBirth = request.getParameter("dogBirth");
			String remarks = request.getParameter("remarks");
			
			//
			WankoDAO wdao = new WankoDAO();
			
			int ans = wdao.insert(dogPhoto, dogName, name, wakuchin, kyosei, gender, dogBreed, dogBirth, remarks);
			
			if(ans == 1) {
				request.setAttribute("msg","登録完了");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/t_dog_regi.jsp");
				dispatcher.forward(request, response);
			}else {
				request.setAttribute("msg","登録失敗");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/t_dog_regi.jsp");
				dispatcher.forward(request, response);
			}
		}else if("更新".equals(request.getParameter("butt"))) {
			
			
			String id = request.getParameter("id");
			System.out.println("ddd"+id);
			String dogPhoto = request.getParameter("dogPhoto");
			String dogName = request.getParameter("dogName");
			String gender = request.getParameter("gender");
			String wakuchin = request.getParameter("wakuchin");
			String kyosei = request.getParameter("kyosei");
			String dogBreed = request.getParameter("dogBreed");
			String dogBirth = request.getParameter("dogBirth");
			String remarks1 = request.getParameter("remarks1");
			String remarks2 = request.getParameter("remarks2");
			String remarks3 = request.getParameter("remarks3");
			String remarks4 = request.getParameter("remarks4");
			String remarks5 = request.getParameter("remarks5");
			
			WankoDAO wdao = new WankoDAO();
			if(wdao.odogUp(id, dogPhoto, dogName, gender, wakuchin, kyosei, 
					dogBreed, dogBirth, remarks1, remarks2, remarks3, remarks4, remarks5)) {
				request.setAttribute("msg","更新完了");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/o_dog_detail.jsp");
				dispatcher.forward(request, response);
			} else { // 更新失敗
				request.setAttribute("msg","更新失敗");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/o_dog_detail.jsp");
				dispatcher.forward(request, response);
			}
			
		}
		
		
		
	}

}
