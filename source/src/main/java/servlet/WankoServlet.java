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

import dao.WankoDAO;
import dto.AllDto;


@WebServlet("/WankoServlet")
@MultipartConfig(
	    fileSizeThreshold = 1024 * 1024,
	    maxFileSize = 1024 * 1024 * 5, // 5MB
	    maxRequestSize = 1024 * 1024 * 10) // 10MB
public class WankoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		AllDto log = (AllDto)session.getAttribute("user");
		
		// 条件によって画面を振り分ける
		if("home".equals(action)) {
			
			if(log.isUserUniqueId() == true ) {
				//トレーナー側
				AllDto user = (AllDto) session.getAttribute("user");
				int userSchoolId = user.getUserSchoolId();
				WankoDAO wdao = new WankoDAO();
				List<AllDto> owankoList = wdao.wList(userSchoolId);
				
				session.setAttribute("owankoList",owankoList);
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/dog_list.jsp");
				dispatcher.forward(request, response);
			
			}else if(log.isUserUniqueId() == false ) {
				//飼い主用の遷移
				
				AllDto user = (AllDto) session.getAttribute("user");
				String userNameId = user.getUserNameId();
				WankoDAO wdao = new WankoDAO();
				List<AllDto> owankoList = wdao.olistSelect(userNameId);
				request.setAttribute("owankoList", owankoList);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/dog_list.jsp");
				dispatcher.forward(request, response);
			}
			
		}else if("list".equals(action)) {
			//犬一覧

				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/o_dog_regi.jsp");
				dispatcher.forward(request, response);
			
			
		}else if("dogDetail".equals(action) ) {
			//犬詳細

				request.setCharacterEncoding("UTF-8");
				
				String id = request.getParameter("id");				
				WankoDAO wdao = new WankoDAO();
				List<AllDto> oDogDet = wdao.oDogDet(id);
				request.setAttribute("oDogDet", oDogDet);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/o_dog_detail.jsp");
				dispatcher.forward(request, response);
			
		}else {
			if(log.isUserUniqueId() == true ) {
				
			}else if(log.isUserUniqueId() == false ) {
				//飼い主用の遷移
				AllDto user = (AllDto) session.getAttribute("user");
				String userNameId = user.getUserNameId();
				WankoDAO wdao = new WankoDAO();
				List<AllDto> owankoList = wdao.olistSelect(userNameId);
				request.setAttribute("owankoList", owankoList);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/dog_list.jsp");
				dispatcher.forward(request, response);
				
				
			}
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//値の取得
		request.setCharacterEncoding("UTF-8");
		// "butt" が押されたときその中身が "登録" なら実行
		if("登録".equals(request.getParameter("butt"))) {
			
			Part dogPhotoPart = request.getPart("dogPhoto");
			String dogPhoto = null;
			if (dogPhotoPart != null && dogPhotoPart.getSize() > 0) {
			    String fileName = Paths.get(dogPhotoPart.getSubmittedFileName()).getFileName().toString();
			    String savePath = "upload/dogphoto";
			    String appPath = request.getServletContext().getRealPath("");
			    String fullSavePath = appPath + File.separator + savePath;

			    File fileSaveDir = new File(fullSavePath);
			    if (!fileSaveDir.exists()) {
			        fileSaveDir.mkdirs();
			    }

			    String filePath = fullSavePath + File.separator + fileName;
			    dogPhotoPart.write(filePath);
			    dogPhoto = savePath + "/" + fileName;
			}

			// ワクチン歴(wakuchin)処理
			Part wakuchinPart = request.getPart("wakuchin");
			String wakuchin = null;
			if (wakuchinPart != null && wakuchinPart.getSize() > 0) {
			    String fileName = Paths.get(wakuchinPart.getSubmittedFileName()).getFileName().toString();
			    String savePath = "upload/wakuchin";
			    String appPath = request.getServletContext().getRealPath("");
			    String fullSavePath = appPath + File.separator + savePath;

			    String filePath = fullSavePath + File.separator + fileName;
			    wakuchinPart.write(filePath);
			    wakuchin = savePath + "/" + fileName;
			}
			//
			
			String dogName = request.getParameter("dogName");
			String userNameId = request.getParameter("userNameId");
			String kyoseiParam = request.getParameter("kyosei");
			int kyosei = "true".equalsIgnoreCase(kyoseiParam) ? 1 : 0;
			String genderParam = request.getParameter("gender");
			int gender = "true".equalsIgnoreCase(genderParam) ? 1 : 0;
			String dogBreed = request.getParameter("dogBreed");
			String dogBirth = request.getParameter("dogBirth");
			String remarks1 = request.getParameter("remarks1");
			String remarks2 = request.getParameter("remarks2");
			String remarks3 = request.getParameter("remarks3");
			String remarks4 = request.getParameter("remarks4");
			String remarks5 = request.getParameter("remarks5");
			
			//
			WankoDAO wdao = new WankoDAO();
			
			int ans = wdao.insert(dogPhoto, dogName, userNameId, wakuchin, kyosei, gender, dogBreed, dogBirth, remarks1, remarks2, remarks3, remarks4, remarks5);
			
			if(ans == 1) {
				request.setAttribute("msg","登録完了");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/o_dog_regi.jsp");
				dispatcher.forward(request, response);
			}else {
				request.setAttribute("msg","登録失敗");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/o_dog_regi.jsp");
				dispatcher.forward(request, response);
			}
		}else if("更新".equals(request.getParameter("butt"))) {
			
			String savePath = "upload/dogphoto";
			String appPath = request.getServletContext().getRealPath("");
			String fullSavePath = appPath + File.separator + savePath;

			File fileSaveDir = new File(fullSavePath);
			if (!fileSaveDir.exists()) {
			    fileSaveDir.mkdirs();
			}
			
			Part part = request.getPart("dogPhoto");
			String fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();

			String dogPhoto = null;
			
			// パスとファイル名を結合
			if (part != null && part.getSize() > 0) {
			String filePath = fullSavePath + File.separator + fileName;

			// 実際にファイルを保存
			part.write(filePath);
			String dFilePath = savePath +"/"+  fileName;
			
			dogPhoto = dFilePath;
			
			}else {
				dogPhoto = request.getParameter("oldDogPhoto");
			}
			// デバッグ出力
			
			String id = request.getParameter("id");
			String dogName = request.getParameter("dogName");
			String gender = request.getParameter("gender");
			String wakuchin = request.getParameter("wakuchin");
			String kyosei = request.getParameter("kyosei");
			String dogBreed = request.getParameter("dogBreed");
			String dogBirth = request.getParameter("dogBirth");
			String remarks1 = request.getParameter("remarks1");
			String remarks2 = request.getParameter("remarks2");
			String remarks3 = request.getParameter("remarks3");
			String remarks4 = request.getParameter("remarks4");
			String remarks5 = request.getParameter("remarks5");
			
			WankoDAO wdao = new WankoDAO();
			if(wdao.odogUp(id, dogPhoto, dogName, gender, wakuchin, kyosei, 
					dogBreed, dogBirth, remarks1, remarks2, remarks3, remarks4, remarks5)) {
				request.setAttribute("msg","更新完了");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/o_dog_detail.jsp");
				dispatcher.forward(request, response);
			} else { // 更新失敗
				request.setAttribute("msg","更新失敗");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/o_dog_detail.jsp");
				dispatcher.forward(request, response);
			}
			
		}else if("削除".equals(request.getParameter("butt"))) {
			String savePath = "upload/dogphoto";
			String appPath = request.getServletContext().getRealPath("");
			String fullSavePath = appPath + File.separator + savePath;

			File fileSaveDir = new File(fullSavePath);
			if (!fileSaveDir.exists()) {
			    fileSaveDir.mkdirs();
			}
			
			
			
			String id = request.getParameter("id");
			
			WankoDAO wdao = new WankoDAO();
			if(wdao.delete(id)) {
				request.setAttribute("msg","削除完了");
				response.sendRedirect(request.getContextPath() + "/WankoServlet");
			}else {
				request.setAttribute("msg","削除失敗");
				response.sendRedirect(request.getContextPath() + "/WankoServlet");
			}
			
		}
		
		
		
	}

}
