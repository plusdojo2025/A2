package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDAO;
import dto.AllDto;




@WebServlet(urlPatterns =  "/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 String action = request.getParameter("action");

		    // 条件によって画面を振り分ける
		    if ("regi".equals(action)) {
		        // 新規登録画面へ
		        request.getRequestDispatcher("/WEB-INF/jsp/new_regi.jsp").forward(request, response);
		    } else {
		        // ログイン画面へ（デフォルト）
		        request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
		    }
//		// ログインページにGO！
//		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
//		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 入力データの取得（文字コードの指定も忘れずに）
		request.setCharacterEncoding("UTF-8");
		
		if(request.getParameter("logbut").equals("ログイン")) {
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		//DAOを実体化
		UserDAO dao = new UserDAO();
		//ログインできる？できなかったらdtoはnullになる
		AllDto luser = dao.login(id,pw);
		
		
		//ログイン成功時
		if(luser!=null) {
			//取得したユーザー情報をuserという名前でsessionに保存
			HttpSession session = request.getSession();
			session.setAttribute("user",luser);
			//リダイレクトにてMenuServletに処理を移す
			//MenuServletでトレーナーか顧客かを判別する
			response.sendRedirect("HomeServlet");
		//ログイン失敗時
		}else {
			//エラーメッセージを次の画面で使えるようrequestにセット
			request.setAttribute("errMsg", "※idまたはpwが違います。");
			//元のページへ戻る（login.jspへ)
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
			dispatcher.forward(request, response);
		}
		}
	}
}

