<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- ↓↓横並びにする！ -->
	<div>
		<h1>うんち一覧</h1>
		<a href="poop_regi.jsp">新規登録</a>					<!--  -->
		<!-- 検索↓ -->
			<input type="text" name="">				<!-- 全項目から検索する(DAO) -->
			<input type="submit" name="search" value="検索">		
		<!-- 検索↑ -->
		<a href="javascript:history.back();">
			<span>ひとつ前に戻る</span>							<!-- cssでmargin0にする？ -->
			<img src="images/back.png" alt="戻る">		<!-- 戻る画像ボタン -->
		</a>
	</div>
	
	<!-- ▼ 並び順プルダウン --><!-- Servlet+DAO -->
    <select name="sort">
        <option value="date_desc">登録日（新しい順）</option>		<!-- 降順 -->
        <option value="breed_asc">犬種名順</option>
        <option value="strage_asc">預かり日順</option>
    </select>
	
	<!-- うんち一覧 -->
	<c:forEach var="poop" items="">							<!-- itemsをサーブレットに合わせる。（サーブレットでリストを作る） -->
	<div>
			<span><img src="${poop.dogphoto}" alt="${e.dogName}の写真"></span>
			<span>${poop.dogName}</span>		<!-- わんこの名前 -->	
			<span>${poop.name}</span>			<!-- 飼い主様 -->	
			<span>${poop.hardness}</span>			<!-- 硬さ -->	
			<span>${poop.color}</span>			<!-- 色 -->	
			<span>${poop.date}</span>			<!-- 登録日 -->	
	</div>
</c:forEach>

<!-- 20件ずつ表示（次のページへ、前のページへ） -->	
<!-- Servlet+DAO -->	<!-- Servletでpageを定義する必要あり -->
<div>
	<c:if test="${page > 1}">								<!-- 前へ -->
		<a href=PoopServlet?page=${page - 1}>前へ</a>
	</c:if>
	<span>${page}ページ目</span>								
	<c:if test="${page < hasNext}">							<!-- 次のページがあるか？なければ「次へ」を表示しない -->
		<a href=PoopServlet?page=${page + 1}>次へ</a>		<!-- 次へ -->
	</c:if>
</div>

</body>
</html>