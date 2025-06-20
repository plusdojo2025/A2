<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<!-- 
 <link rel="stylesheet" href="https://cdn.datatables.net/t/bs-3.3.6/jqc-1.12.0,dt-1.10.11/datatables.min.css"/>
 <script src="https://cdn.datatables.net/t/bs-3.3.6/jqc-1.12.0,dt-1.10.11/datatables.min.js"></script>
  -->
 <script>
    jQuery(function($){
    	 // デフォルトの設定を変更（日本語化）--------------------
        $.extend( $.fn.dataTable.defaults, {
            language: {
                url: "http://cdn.datatables.net/plug-ins/9dcbecd42ad/i18n/Japanese.json"
            }
        });
    	 //------------------------------------------------
    	//データテーブルを使用
        $("#foo-table").DataTable();
    });
 </script>
<meta charset="UTF-8">
<title>うんち一覧</title>
<style>
/* 戻る画像ボタン */
	.back_button {
		width: 40px;
	}
	
	.back_text {
		font-size: 10px;
	}	
	
	/* ○ページ目 */
	.page_count {
		text-align: center;
	}
	/*「○○一覧」*/
	.page-title {
		color:#FFA500;
		font-family: "Meiryo";
	}
	/*「新規登録ボタン」*/
	.regist-link {
		
	}
 </style>
</head>
<body>
	<!-- headerのページインクルード -->
	<h2 class=page-title>うんち一覧</h2>
	
	<!-- ↓↓横並びにする！ -->
	<div>
		<a href="<c:url value='/PoopServlet' />" class="regist-link">新規登録</a>	
		<form>				
			<!-- 検索↓ -->
				<input type="text" name="keyword">				<!-- 全項目から検索する(DAO) -->
				<input type="submit" name="search" value="検索">		
		
			<!-- ▼ 並び順プルダウン --><!-- Servlet+DAO -->
		    <select name="sort">
		        <option value="date_desc">登録日（新しい順）</option>		<!-- 降順 -->
		        <option value="breed_asc">犬種名順</option>
		        <option value="strage_asc">預かり日順</option>
		    </select>
	    </form>
		<!-- 戻る画像ボタン -->
		<div>
			<span class="back_text">前に戻る</span>	<br>												<!-- cssでmargin0にする？ -->
			<a href="javascript:history.back();">
 				<img src="<c:url value='/images/back.png' />" alt="戻る" class="back_button">
			</a>	
		</div>
	</div>
	
	<!-- うんち一覧 -->
	<c:forEach var="poop" items="">							<!-- itemsをサーブレットに合わせる。（サーブレットでリストを作る） -->
	<div>
			<span><img src="${poop.dogphoto}" alt="${poop.dogName}の写真"></span>
			<span>${poop.dogName}</span>		<!-- わんこの名前 -->	
			<span>${poop.name}</span>			<!-- 飼い主様 -->	
			<span>${poop.hardness}</span>			<!-- 硬さ -->	
			<span>${poop.color}</span>			<!-- 色 -->	
			<span>${poop.date}</span>			<!-- 登録日 -->	
	</div>
	</c:forEach>
	
	<!-- 20件ずつ表示（次のページへ、前のページへ） -->	
	<!-- Servlet+DAO -->	<!-- Servletでpageを定義する必要あり -->
	<div class="page_count">
		<c:if test="${page > 1}">								<!-- 前へ -->
			<a href=PoopServlet?page=${page - 1}>前へ</a>
		</c:if>
		<span>${page}ページ目</span>								
		<c:if test="${page < hasNext}">							<!-- 次のページがあるか？なければ「次へ」を表示しない -->
			<a href=PoopServlet?page=${page + 1}>次へ</a>		<!-- 次へ -->
		</c:if>
	</div>
	<!-- footerのページインクルード -->
</body>
</html>