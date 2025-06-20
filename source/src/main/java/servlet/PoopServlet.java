package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.PoopDAO;
import dto.AllDto;

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
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/regist.jsp");
		dispatcher.forward(request, response);
	}
		

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	
	// リクエストパラメータを取得する
	request.setCharacterEncoding("UTF-8");
	int  poopId =Integer.parseInt(request.getParameter("poopId"));
	String tlName =request.getParameter("tlname");
	LocalDateTime  nowtime=LocalDateTime.parse(request.getParameter("nowtime"));
	String photo =request.getParameter("photo");
	int  hardness =Integer.parseInt(request.getParameter("harness"));
	boolean  abnormal = Boolean.parseBoolean(request.getParameter("abnormal"));
	int  poopdogid =Integer.parseInt(request.getParameter("poopdogid"));
	String memo =request.getParameter("memo");
	LocalDate Date =LocalDate.parse(request.getParameter("date"));
	
	
	

//登録処理を行う
		PoopDAO bDao = new PoopDAO();
		AllDto pDto = new AllDto();
		pDto.setPoopId(poopId);
		pDto.setTlName(tlName);
		pDto.setNowTime(nowtime)
		pDto.setPhoto(photo);
		pDto.setHardness(hardness);
		pDto.setAbnormal(abnormal);
		pDto.setPoopDogId(poopdogid);
		pDto.setMemo(memo);
		pDto.setDate(date);
		
		int ans = bDao.insert(poopId, tlName, nowtime, photo, hardness, abnormal, poopdogid, memo, date);
		if( ans == 1) {
			request.setAttribute("msg","登録完了");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/insert.jsp");
			dispatcher.forward(request, response);
		}else {
			request.setAttribute("msg","登録できなかったよ");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/insert.jsp");
			dispatcher.forward(request, response);
		}	
//検索
/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/SearchServlet")
public class PoopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
