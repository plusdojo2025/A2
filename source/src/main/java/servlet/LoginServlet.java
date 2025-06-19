package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




@WebServlet(urlPatterns = {"/", "/LoginServlet"})
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ログインページにGO！
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 入力データの取得（文字コードの指定も忘れずに）
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		/*
		//DAOを実体化
		UserDao dao = new UserDao();
		//ログインできる？できなかったらdtoはnullになる
		AllDto dto = dao.login(id,pw);
		
		//ログイン成功時
		if(dto!=null) {
			//取得したユーザー情報をuserという名前でsessionに保存
			HttpSession session = request.getSession();
			session.setAttribute("user",dto);
			//リダイレクトにてMenuServletに処理を移す
			//MenuServletでトレーナーか顧客かを判別する
			response.sendRedirect("/webappAns/MenuServlet");
		//ログイン失敗時
		}else {
			//エラーメッセージを次の画面で使えるようrequestにセット
			request.setAttribute("errMsg", "※idまたはpwが違います。");
			//元のページへ戻る（login.jspへ)
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
			dispatcher.forward(request, response);
		}
		*/
	}
}

