package servlet;

import java.io.IOException;

import javax.naming.spi.DirStateFactory.Result;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.PoopDAO;

/**
 * Servlet implementation class RegistServlet
 */
@WebServlet("/RegistServlet")
public class PoopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		if (session.getAttribute("id") == null) {
			response.sendRedirect("/webapp/LoginServlet");
			return;
		}

		// 登録ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/regist.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		if (session.getAttribute("id") == null) {
			response.sendRedirect("/webapp/LoginServlet");
			return;
		}
	
	// リクエストパラメータを取得する
	request.setCharacterEncoding("UTF-8");
	String  poopId =request.getParameter("poopId");
	String tlName =request.getParameter("tlname");
	String  nowtime=request.getParameter("nowtime");
	String photo =request.getParameter("photo");
	String  hardness =request.getParameter("harness");
	String  isabnormal = request.getParameter("abnormal");
	String  poopdogid =request.getParameter("poopdogid");
	String memo =request.getParameter("memo");
	String  date =request.getParameter("getdate");


//登録処理を行う
		PoopDAO bDao = new PoopDAO();
		if (bDao.insert(new Poop(0, poopId, tlName, nowtime, photo, hardness, isabnormal, poopdogid, memo, date))) { // 登録成功
			request.setAttribute("result", new Result("登録成功！", "レコードを登録しました。", "/webapp/PoopServlet"));
		} else { // 登録失敗
			request.setAttribute("result", new Result("登録失敗！", "レコードを登録できませんでした。", "/webapp/PoopServlet"));
		}

		// 結果ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/result.jsp");
		dispatcher.forward(request, response);
	}
}
