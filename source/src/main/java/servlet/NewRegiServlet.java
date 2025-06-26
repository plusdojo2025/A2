package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;

/**
 * Servlet implementation class NewRegiServlet
 */
@WebServlet("/NewRegiServlet")
public class NewRegiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/new_regi.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		 
		String userNameId = request.getParameter("nameId");
		String ruby = request.getParameter("ruby");
		String birth = request.getParameter("birth");
		String name = request.getParameter("name");
		String pw = request.getParameter("pw");
		String uPhone = request.getParameter("uPhone");
		String uPhone2 = request.getParameter("uPhone2");
		int userSchoolId = Integer.parseInt(request.getParameter("schoolId"));
		boolean userUniqueId = "true".equals(request.getParameter("userUniqueId"));
		
		UserDAO uDao = new UserDAO();
		

		if(uDao.insert(userNameId, ruby, birth, name, pw, uPhone, uPhone2, userSchoolId, userUniqueId)) {
			request.setAttribute("errMsg","登録完了 ログインしてください");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
			dispatcher.forward(request, response);
		} else { // 登録失敗
			request.setAttribute("msg","メールアドレスは使用されています");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/new_regi.jsp");
			dispatcher.forward(request, response);
		}


	}
}
		
