package servlet;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;
import dto.AllDto;

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
		 
		String nameId = request.getParameter("nameId");
		String ruby = request.getParameter("ruby");
		String dateStr = request.getParameter("birth");
		LocalDate birth = LocalDate.parse(dateStr);
		String name = request.getParameter("name");
		String pw = request.getParameter("pw");
		String uPhone = request.getParameter("uPhone");
		String uPhone2 = request.getParameter("uPhone2");
		int userSchoolId = Integer.parseInt(request.getParameter("userSchoolId"));
		
		UserDAO uDao = new UserDAO();
		AllDto uDto =new AllDto();
		
		uDto.setUserNameId(nameId);
		uDto.setRuby(ruby);
		uDto.setbirth(birth);
		uDto.setName(name);
		uDto.setPw(pw);
		uDto.setuPhone(uPhone);
		uDto.setuPhone2(uPhone2);
		uDto.setUserSchoolId(userSchoolId);
		
		if(uDao.insert(uDto)) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/A2/LoginServlet");
			dispatcher.forward(request, response);
		} else { // 登録失敗
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/new_regi.jsp");
			dispatcher.forward(request, response);
		}

	}

}
