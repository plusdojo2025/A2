package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.WankoDAO;


@WebServlet("/WankoServlet")
public class WankoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/t_dog_regi.jsp");
		dispatcher.forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//値の取得
		request.setCharacterEncoding("UTF-8");
		// "butt" が押されたときその中身が "登録" なら実行
		if(request.getParameter("butt").equals("登録")) {
			
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
				request.setAttribute("msg","登録できなかったよ");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/t_dog_regi.jsp");
				dispatcher.forward(request, response);
			}
		}
		
	}

}
