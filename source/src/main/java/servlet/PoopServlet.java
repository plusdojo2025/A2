package servlet;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import dao.PoopDAO;
import dto.AllDto;



/**
 * Servlet implementation class RegistServlet
 */
@MultipartConfig(
	    fileSizeThreshold = 1024 * 1024,
	    maxFileSize = 1024 * 1024 * 5, // 5MB
	    maxRequestSize = 1024 * 1024 * 10) // 10MB
@WebServlet("/PoopServlet")
public class PoopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		AllDto log = (AllDto)session.getAttribute("user");
		
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		if (session.getAttribute("user") == null) {
			response.sendRedirect(request.getContextPath()+"/LoginServlet");
			return;
		//ホームからリストへ
		}else if("home".equals(action)) {
			
			if(log.isUserUniqueId() == true ) {
			//トレーナー
			AllDto user = (AllDto) session.getAttribute("user");
			int userSchoolId = user.getUserSchoolId();	
			PoopDAO pdao = new PoopDAO();
			List<AllDto> poopList = pdao.tpooplistSelect(userSchoolId);	
			request.setAttribute("poopList", poopList);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/poop_list.jsp"); 
			dispatcher.forward(request, response);
			
			
			}else if(log.isUserUniqueId() == false ) {
			//飼い主用の遷移
				
			AllDto user = (AllDto) session.getAttribute("user");
			String userNameId = user.getUserNameId();
			PoopDAO pdao = new PoopDAO();
			List<AllDto> poopList = pdao.pooplistSelect(userNameId);
			request.setAttribute("poopList", poopList);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/poop_list.jsp"); 
			dispatcher.forward(request, response);
			}
		//リストから詳細へ
		}else if("pooplist".equals(action)) {
			
				request.setCharacterEncoding("UTF-8");
				
				String id = request.getParameter("id");	
				PoopDAO pdao = new PoopDAO();
				List<AllDto> pDogDet = pdao.pDogDet(id);
				request.setAttribute("pDogDet", pDogDet);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/poop_detail.jsp");
				dispatcher.forward(request, response);
			
		//登録へ
		}else if("poopregi".equals(action)) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/poop_regi.jsp");
			dispatcher.forward(request, response);
			return;
		}else {
			if(log.isUserUniqueId() == true ) {
				//トレーナー
					
				}else if(log.isUserUniqueId() == false ) {
				//飼い主用の遷移
					
				AllDto user = (AllDto) session.getAttribute("user");
				String userNameId = user.getUserNameId();
				PoopDAO pdao = new PoopDAO();
				List<AllDto> poopList = pdao.pooplistSelect(userNameId);
				request.setAttribute("poopList", poopList);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/poop_list.jsp"); 
				dispatcher.forward(request, response);
				}
		}
	

				
	}
	 


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		request.setCharacterEncoding("UTF-8");
		System.out.println("doPostが呼ばれました");
		System.out.println("pbutt=" + request.getParameter("pbutt"));
		HttpSession session = request.getSession();
		if (session.getAttribute("user") == null) {
			response.sendRedirect(request.getContextPath()+"/LoginServlet");
			return;
		}
		//登録ボタンが押されたら	
		if("登録".equals(request.getParameter("pbutt")))	{
			//うんち写真保存
			System.out.println("登録ボタン押された");
			Part photoPart = request.getPart("photo");
			String photo = null;
			if (photoPart != null && photoPart.getSize() > 0) {
			    String fileName = Paths.get(photoPart.getSubmittedFileName()).getFileName().toString();
			    String savePath = "upload/poop";
			    String appPath = request.getServletContext().getRealPath("");
			    String fullSavePath = appPath + File.separator + savePath;
			    String filePath = fullSavePath + File.separator + fileName;
			    photoPart.write(filePath);
			    photo = savePath + "/" + fileName;
			}
			String nowTime = request.getParameter("nowTime");
			String date = request.getParameter("date");
			String dogName = request.getParameter("dogName");
			String color = request.getParameter("color");
			String hardness = request.getParameter("hardness");
			String abnormal = request.getParameter("abnormal");
			String memo =request.getParameter("memo");
			String PoopDogId = request.getParameter("PoopDogId");
			
			PoopDAO pdao = new PoopDAO();
			int ans = pdao.insert(photo, nowTime, date, dogName, color, hardness, abnormal, memo, PoopDogId);			
			if(ans == 1) {
				request.setAttribute("msg","登録完了");
				AllDto user = (AllDto) session.getAttribute("user");
				String userNameId = user.getUserNameId();				
				List<AllDto> poopList = pdao.pooplistSelect(userNameId);
				request.setAttribute("poopList", poopList);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/poop_list.jsp"); 
				dispatcher.forward(request, response);
				return;
			}else {
				request.setAttribute("msg","登録失敗");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/poop_regi.jsp");
				dispatcher.forward(request, response);
				return;
			}
		}else if("削除".equals(request.getParameter("pbutt"))) {
			String id = request.getParameter("poopId");
			System.out.println("poopId"+id);
			PoopDAO pdao =new PoopDAO();
			if(pdao.delete(id)) {
				request.setAttribute("msg","削除完了");
				AllDto user = (AllDto) session.getAttribute("user");
				String userNameId = user.getUserNameId();				
				List<AllDto> poopList = pdao.pooplistSelect(userNameId);
				request.setAttribute("poopList", poopList);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/poop_list.jsp"); 
				dispatcher.forward(request, response);
				return;
			}else {
				request.setAttribute("msg","削除失敗");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/poop_list.jsp");
				dispatcher.forward(request, response);
				return;
			}
		}else if("更新".equals(request.getParameter("pbutt"))) {
			String savePath = "upload/poop";
			String appPath = request.getServletContext().getRealPath("");
			String fullSavePath = appPath + File.separator + savePath;
			
			Part part = request.getPart("dogPhoto");
			String fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();

			String photo = null;
			// パスとファイル名を結合
			if (part != null && part.getSize() > 0) {
			String filePath = fullSavePath + File.separator + fileName;

			// 実際にファイルを保存
			part.write(filePath);
			String dFilePath = savePath +"/"+  fileName;
			
			photo = dFilePath;
			
			}else {
				photo = request.getParameter("oldPhoto");
			}
			
			String nowTime = request.getParameter("nowTime");
			String date = request.getParameter("date");
			String dogName = request.getParameter("dogName");
			String color = request.getParameter("color");
			String hardness = request.getParameter("hardness");
			String abnormal = request.getParameter("abnormal");
			String memo =request.getParameter("memo");
			String PoopDogId = request.getParameter("PoopDogId");
			
			PoopDAO pdao = new PoopDAO();
			if(pdao.pupdate(nowTime, date, dogName, color, hardness, abnormal, memo, PoopDogId)) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/poop_detail.jsp");
				dispatcher.forward(request, response);
			}
		
		
		
		
		//値の取得
		// リクエストパラメータを取得する
		
//	request.setCharacterEncoding("UTF-8");
//	String action = request.getParameter("action");
//	
//	int  poopId =Integer.parseInt(request.getParameter("poopId"));
//	LocalDateTime  nowtime=LocalDateTime.parse(request.getParameter("nowtime"));
//	String photo =request.getParameter("photo");
//	int  hardness =Integer.parseInt(request.getParameter("hardness"));
//	boolean  abnormal = Boolean.parseBoolean(request.getParameter("abnormal"));
//	int  poopdogid =Integer.parseInt(request.getParameter("poopdogid"));
//	String memo =request.getParameter("memo");
//	LocalDate date =LocalDate.parse(request.getParameter("date"));
//	
	

//登録処理を行う
//		PoopDAO bDao = new PoopDAO();
//		AllDto pDto = new AllDto();
//		pDto.setPoopId(poopId);
//		pDto.setTlName(tlName);
//		pDto.setNowTime(nowtime);
//		pDto.setPhoto(photo);
//		pDto.setHardness(hardness);
//		pDto.setAbnormal(abnormal);
//		pDto.setPoopDogId(poopdogid);
//		pDto.setMemo(memo);
//		pDto.setDate(date);
//		
//		
//		int ans = bDao.insert(pDto);
//		
//		if( ans == 1) {
//			request.setAttribute("msg","登録完了");
//			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/poop_list.jsp");
//			dispatcher.forward(request, response);
//		}else {
//			request.setAttribute("msg","登録できなかったよ");
//			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/poop_list.jsp");
//			dispatcher.forward(request, response);
//		}
//		
	
		//検索
			// 検索処理を行う
			
//			List<AllDto> poopList = bDao.select(pDto);
//
//			// 検索結果をリクエストスコープに格納する
//			request.setAttribute("poopList", poopList);
//
//			// 結果ページにフォワードする
//			
//			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/poop_detail.jsp");
//			dispatcher.forward(request, response);
//			
//			//更新・削除
//			if("uppdate".equals(action)) {
//				if (bDao.update(pDto)) {
//					request.setAttribute("message", "レポートの更新に成功しました。");
//					dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/poop_detail.jsp");
//					dispatcher.forward(request, response);	
//				
//			} else { 
//				request.setAttribute("error", "レポートの更新に失敗しました。");
//				dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/poop_detail.jsp");
//				dispatcher.forward(request, response);
//			}
//			
//		}else  if("delete".equals(action)) {
//			if (bDao.delete(pDto)) {
//				request.setAttribute("message", "レポートの削除に成功しました。");
//				 dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/poop_detail.jsp");
//				dispatcher.forward(request, response);
//			} else { 
//				request.setAttribute("error", "レポートの削除に失敗しました。");
//				dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/poop_detail.jsp");
//				dispatcher.forward(request, response);
//				
//			}
//		
//			/*
//			// 更新または削除を行う
//			if (request.getParameter("submit").equals("更新")) {
//				if (bDao.update(new AllDto())) { // 更新成功
//					request.setAttribute("result", new Result("更新成功！", "レコードを更新しました。", "/servlet/PoopServlet"));
//				} else { // 更新失敗
//					request.setAttribute("result", new Result("更新失敗！", "レコードを更新できませんでした。", "/servlet/PoopServlet"));
//				}
//			} else {
//				if (bDao.delete(new AllDto())) { // 削除成功
//					request.setAttribute("result", new Result("削除成功！", "レコードを削除しました。", "/servlet/PoopServlet"));
//				} else { // 削除失敗
//					request.setAttribute("result", new Result("削除失敗！", "レコードを削除できませんでした。", "/servlet/PoopServlet"));
//				}
//			}*/
//			// 結果ページにフォワードする
//			dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/poop_list.jsp");
//			dispatcher.forward(request, response);
//		}
//	}
//}
		}	
	}
}
