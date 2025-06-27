<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>報告詳細（企業用）</title>
<%-- <link rel="stylesheet"  href="<c:url value='/css/etc.css' />"> --%>
<style>
.back_text {
	font-size: 10px;
	float: right;
	margin-top:-20px;
	}
/* 戻る画像ボタン */
.back_button {
	width: 40px;
	float: right;
	margin-top:0px;
}
.container {
	width: 80%;
	max-width: 800px;
	margin: 0 auto;
	text-align: left;
}
</style>
</head>
<body>
<%@ include file="header.jsp" %>

	<h1>
		<div style="display: flex; justify-content: space-between;">
  			<div style="text-align: left;">報告詳細</div>
        	<div style="text-align: right;">
        		<span class="back_text">前に戻る</span>							<!-- cssでmargin0にする？ -->
        		<a href="javascript:history.back();">
				<img src="<c:url value='/images/back.png' />" alt="戻る"/ class="back_button"> 		<!-- 戻る画像ボタン -->
		    	</a>
        	</div>
		</div>
	</h1>
	<!-- 後でDBとひもづけてデータを取ってこれるようにする -->
	
	
	<form method="POST" action="<c:url value='/ReportServlet' />">
	<input type="hidden" name="action" id="action" value="update">
			<select name="dogName">
				<!-- ワンコ名をプルダウンで選択できるようにする -->
				<option value="${ord.dogName}" selected>ワンコ選択</option>
			</select>
		<ul>
			
			<li>ごはん<input type="radio" name="food" value="1"<c:if test="${ord.food == 'true'}">checked</c:if>>食べた
					<input type="radio" name="food" value="1"<c:if test="${ord.food == 'false'}">checked</c:if>>食べてない
			</li>
			<li>日付<input type="date" name="reportDate" value="${e.date}"></li>
			<li>散歩<input type="number" name="walk" value="${ord.walk}">分</li>
			<li>様子<input type="radio" name="reportState" value="1"<c:if test="${ord.reportState == 'true'}">checked</c:if>>異常なし
					<input type="radio" name="reportState" value="1"<c:if test="${ord.reportState == 'false'}">checked</c:if>>異常あり</li>
			<li>トレーニング<input type="text" name="training" value="${ord.training}"></li>
			<li>メモ<input type="text" name="reportMemo" value="${ord.reportMemo}"></li>
			
		</ul>
		<input type="submit" value="更新" onclick="document.getElementById('action').value='update'"> <input type="submit" value="削除" onclick="document.getElementById('action').value='delete'">	
	</form>	
	
	
	<!--  フッターここから -->

	<!--  フッターここまで -->
	<!-- JavaScript（ここから） -->
	<script>
	/* submitボタンをクリックしたときの処理 */
	function submitClick() {
	  /* 確認ダイアログボックスを表示します */
	  if (!window.confirm('実行します。よろしいですか？')) {
	    return false;
	  }
	}
	
	/* HTML要素をオブジェクトとして取得する */
	let formObjs = document.getElementsByClassName('');
	
	/* 取得したすべてのオブジェクトに同じイベントを適用する */
	for (let item of formObjs) {
	  item.onsubmit = submitClick;
	}
	</script>
	<!-- JavaScript（ここまで） -->
</body>
</html>