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

import dao.UserDAO;
import dto.AllDto;


@WebServlet("/ContactServlet")
public class ContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		// 条件によって画面を振り分ける
		if("home".equals(action)) {
			HttpSession session = request.getSession();
			AllDto log = (AllDto)session.getAttribute("user");
			
			if(log.isUserUniqueId() == false ) {
				String userNameId = request.getParameter("userid");
				request.setAttribute("userid", userNameId);
				request.getRequestDispatcher("/WEB-INF/jsp/chat.jsp").forward(request, response);
				return;
			}else if(log.isUserUniqueId() == true ) {
			//飼い主用の遷移
			AllDto user = (AllDto) session.getAttribute("user");
			String userSchoolId = user.getUserNameId();
			UserDAO cdao = new UserDAO();
			List<AllDto> contactList = cdao.conlistSelect(userSchoolId);
			request.setAttribute("contactList", contactList);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/contact_list.jsp");
			dispatcher.forward(request, response);
			return;
			}
		
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//検索
		UserDAO conDao = new UserDAO();
		AllDto conDto = new AllDto();
		
		List<AllDto> contactList = conDao.select(conDto)  ;
		
		// 検索結果をリクエストスコープに格納する
		request.setAttribute("contactList", contactList);
		
		// 結果
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/contact_list.jsp");
		dispatcher.forward(request, response);
		
//		//1ページ20項目で表示
//		String pageParam = request.getParameter("page");
//		int page = 1;
//		int pageSize = 20;
//		if (pageParam != null && pageParam.matches("\\d+")) {
//		    page = Integer.parseInt(pageParam);
//		}
//		int offset = (page - 1) * pageSize;
//
//		String userSchoolId = request.getParameter("userSchoolId");
//
//		// DAOに必要な情報だけ渡す
//		UserDAO dao = new UserDAO();
//		List<AllDto> clist = dao.conlistSelect(userSchoolId, pageSize, offset);
//
//		// 検索結果をリクエストスコープに格納する
//		request.setAttribute("userList", clist);
		
	
					
			
					
					
	}

}
