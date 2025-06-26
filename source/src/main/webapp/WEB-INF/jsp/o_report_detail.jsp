<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>報告詳細飼い主用</title>
<link rel="stylesheet"  href="<c:url value='/css/etc.css' />">
<style>
/* 戻る画像ボタン */
.back-option {
  position: absolute;      /* 画面上の位置を絶対指定 */
  top: 10px;               /* 上から10px */
  right: 10px;             /* 右から10px */
  font-size: 12px;         /* テキストを小さく */
  display: flex;
  align-items: center;
  gap: 5px;                /* 画像との間に隙間 */
  z-index: 1000;           /* 上に表示されるようにする */
}

.back-option img {
  width: 40px;             /* 小さめサイズに変更 */
  height: 40px;
}
</style>
</head>
<body>
<%@ include file="header.jsp" %>
	<h1>
		<div style="display: flex; justify-content: space-between;">
  			<div style="text-align: left;">報告詳細　<c:out value="${sessionScope.user.name}" />さん</div>
        	<div class="back-option" style="text-align: right;">
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
			<input type="hidden" name="id" value="${e.reportId}">
			<li>名前<input type="text" name="dogName" value="${e.dogName}"></li>
			<li>ごはん<input type="radio" name="food" value="1"<c:if test="${e.food == 'true'}">checked</c:if>>食べた
			        <input type="radio" name="food" value="0"<c:if test="${e.food == 'false'}">checked</c:if>>食べてない</li>
			<li>日付<input type="date" name="reportDate" value="${e.reportDate}"></li>
			<li>散歩<input type="number" name="walk" value="${e.walk}">分</li>
			<li>様子<input type="radio" name="reportState" value="1"<c:if test="${e.reportState == 'true'}">checked</c:if>>異常なし
					<input type="radio" name="reportState" value="0"<c:if test="${e.reportState == 'false'}">checked</c:if>>異常あり</li>
			<li>トレーニング<input type="text" name="training" value="${e.training}"></li>
			<li>メモ<input type="text" name="reportMemo" value="${e.reportMemo}"></li>
		</ul>
	</c:forEach>
	</c:if>
	</form>

</body>
</html>