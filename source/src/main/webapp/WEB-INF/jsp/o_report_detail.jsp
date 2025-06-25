<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>報告詳細飼い主用</title>
</head>
<body>
	<h1>
		<div style="display: flex; justify-content: space-between;">
  			<div style="text-align: left;">報告詳細</div>
        	<div style="text-align: right;">
        		<a href="javascript:history.back();">
				<span>ひとつ前に戻る</span>							<!-- cssでmargin0にする？ -->
				<img src="/A2/img/back.png" alt="戻る">		<!-- 戻る画像ボタン -->
		    	</a>
        	</div>
		</div>
	</h1>
	<!-- 後でDBとひもづけてデータを取ってこれるようにする -->
	<form method="POST" action="<c:url value='/ODetailReportServlet' />">
	<c:forEach var="e" items="" >
		<ul>
			<select name="dogName">
				<!-- ワンコ名をプルダウンで選択できるようにする -->
				<option value="" selected>ワンコ選択</option>
			</select>
			<li>ごはん<input type="submit" name="food" value="${e.food}"></li>
			<li>日付<input type="date" name="reportDate" value="${e.date}"></li>
			<li>散歩<input type="number" name="walk" value="${e.walk}">分</li>
			<li>様子<input type="submit" name="state" value="${e.state}"></li>
			<li>トレーニング<input type="text" name="training" value="${e.training}"></li>
			<li>メモ<input type="text" name="reportMemo" value="${e.reportMemo}"></li>
		</ul>
	</c:forEach>
	</form>
	<!--  フッターここから -->

	<!--  フッターここまで -->
</body>
</html>