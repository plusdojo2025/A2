<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>問い合わせ一覧</title>
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
 </style>
</head>
<body>
	<!-- headerのページインクルード -->
	<h2>問い合わせ一覧</h2>
	
	<!-- ↓↓横並びにする！ -->
	<div>
		<a href="<c:url value='/A2/ContactServlet' />">新規登録</a>			<!-- サーブレットで識別ID条件分岐 or　条件分岐の必要がなければ直接jspに飛んでOK -->
		<form>
			<!-- ◆検索　-->
			<input type="text" name="keyword">				<!-- 全項目から検索する(DAO) -->
			<input type="submit" name="search" value="検索">		
			<!-- ▼並び順プルダウン -->
			<select name="sort">
		        <option value="date_desc">登録日（新しい順）</option>		<!-- 降順 -->
		        <option value="breed_asc">犬種名順</option>
		        <option value="strage_asc">預かり日順</option>
	   		</select>
	    </form>
		<!-- 戻る画像ボタン -->
		<div>
			<span class="back_text">前に戻る</span>	<br>												<!-- cssでmargin0にする？ -->
			<a href="<c:url value='/javascript:history.back();'/>　">
 				<img src="<c:url value='images/back.png'/> " alt="戻る" class="back_button">
			</a>	
		</div>
	</div>
	
	<!-- 問い合わせ一覧 -->
	<c:forEach var="wanko" items="">			<!-- itemsをサーブレットに合わせる。（サーブレットでリストを作る） -->
	<div>
			<span><img src="${wanko.dogphoto}" alt="${wanko.dogName}の写真"></span>
			<span>${wanko.dogName}</span>		<!-- わんこの名前 -->	
			<span>${wanko.name}</span>			<!-- 飼い主様 -->	
			<span>${wanko.date}</span>			<!-- 登録日 -->	
	</div>
	</c:forEach>
	
	<!-- 20件ずつ表示（次のページへ、前のページへ） -->	
	<!-- Servlet+DAO -->	<!-- Servletでpageを定義する必要あり -->
	<div class="page_count">
		<c:if test="${page > 1}">								<!-- 前へ -->
			<a href=WankoServlet?page=${page - 1}>前へ</a>
		</c:if>
		<span>${page}ページ目</span>								
		<c:if test="${page < hasNext}">							<!-- 次のページがあるか？なければ「次へ」を表示しない -->
			<a href=WankoServlet?page=${page + 1}>次へ</a>		<!-- 次へ -->
		</c:if>
	</div>	
	<!-- footerのページインクルード -->
</body>
</html>