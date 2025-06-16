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
		<input type="text" name="company">					<!-- 全項目から検索する(DAO) -->
		<input type="submit" name="search" value="検索">		<!-- submitボタン -->
		<a href="javascript:history.back();">
			<span>ひとつ前に戻る</span>							<!-- cssでmargin0にする？ -->
			<img src="/webapp/img/back.png" alt="戻る">		<!-- 戻る画像ボタン -->
		</a>
	</div>
	
	<!-- うんち一覧 -->
	<c:forEach var="e" items="">
	<div>
		<ul>
			<li>${e.dogName}</li>
			<li><img src="${e.dogphoto}" alt="${e.dogName}の写真"></li>
			<li>うんち記録</li>
			<li>報告</li>
		</ul>
	</div>
</c:forEach>

</body>
</html>