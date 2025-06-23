package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.WankoDAO;
import dto.AllDto;


@WebServlet("/ContactServlet")
public class ContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//ただの画面遷移
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/contact_list.jsp");
		dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//検索
		WankoDAO conDao = new WankoDAO();
		AllDto conDto = new AllDto();
		
		List<AllDto> contactList = conDao.select(conDto)  ;
		
		// 検索結果をリクエストスコープに格納する
		request.setAttribute("contactList", contactList);
		
		// 結果
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/contact_list.jsp");
					dispatcher.forward(request, response);
					
					int page = 1; //1ページ目
					
					//ページが整数か確認して、整数ならページ番号を付与する
					String pageParam = request.getParameter("page");
					if (pageParam != null && pageParam.matches("\\d+")) {
					    page = Integer.parseInt(pageParam);
					}
					int pageSize = 20; //一ページに格納できる件数
					//指定されたページ番号から、そのページに表示すべき情報を持ってくる
					int offset = (page - 1) * pageSize;
					
					
	}

}
