package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ReserveServlet
 */
@WebServlet("/ReserveServlet")
public class ReserveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/reserve.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		 
		String name = request.getParameter("name");
		String dogName = request.getParameter("dogName");
		String usernameid = request.getParameter("userNameId");
		String uphon = request.getParameter("uphon");
		String pw = request.getParameter("pw");
		
		
		int result = insertUser(name, dogName, userNameId, gender);

		if (result > 0) {
		    request.setAttribute("message", "登録完了！");
		}
//		 // 成功メッセージをリクエストスコープにセット
//	    request.setAttribute("message", "登録が完了しました！");
//	    
	    
	    // JSPに戻る
	    RequestDispatcher dispatcher = request.getRequestDispatcher("reserve.jsp");
	    dispatcher.forward(request, response);
		//UserDAO uR = new UserDAO();
//		AllDto uR =new AllDto();
//		
//		uR.setName(name);
//		uR.setDogName(dogName);
//		uR.setBirth(birth);
//		uR.setuPhone(uphon);
//		uR.setPw(pw);
//		
	}

}
