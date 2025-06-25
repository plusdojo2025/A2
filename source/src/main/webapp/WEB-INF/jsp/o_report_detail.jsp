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
<%@ include file="header.jsp" %>
	<h1>
		<div style="display: flex; justify-content: space-between;">
  			<div style="text-align: left;">報告詳細　<c:out value="${sessionScope.user.name}" />さん</div>
        	<div style="text-align: right;">
        		<a href="javascript:history.back();">
				<span>ひとつ前に戻る</span>							<!-- cssでmargin0にする？ -->
				<img src="/A2/images/back.png" alt="戻る">		<!-- 戻る画像ボタン -->
		    	</a>
        	</div>
		</div>
	</h1>
	<!-- 後でDBとひもづけてデータを取ってこれるようにする -->
	<form method="POST" action="<c:url value='/ReportServlet' />" class="report_datail" enctype="multipart/form-data">
	<c:if test="${not empty ord}">
	<c:forEach var="e" items="${ord}" >
		<ul>
			<select name="dogName">
				<!-- ワンコ名をプルダウンで選択できるようにする -->
				<option value="${e.dogName}" selected>ワンコ選択</option>
			</select>
			<input type="hidden" name="id" value="${e.reportId}">
			<li>ごはん<input type="submit" name="food" value="1"<c:if test="${e.food == 'true'}">checked</c:if>>食べた
			        <input type="submit" name="food" value="0"<c:if test="${e.food == 'false'}">checked</c:if>>食べてない</li>
			<li>日付<input type="date" name="reportDate" value="${e.reportdate}"></li>
			<li>散歩<input type="number" name="walk" value="${e.walk}">分</li>
			<li>様子<input type="submit" name="state" value="1"<c:if test="${e.reportState == 'true'}">checked</c:if>>異常なし
					<input type="submit" name="state" value="0"<c:if test="${e.reportstate == 'false'}">checked</c:if>>異常あり</li>
			<li>トレーニング<input type="text" name="training" value="${e.training}"></li>
			<li>メモ<input type="text" name="reportMemo" value="${e.reportMemo}"></li>
		</ul>
	</c:forEach>
	</c:if>
	</form>

</body>
</html>