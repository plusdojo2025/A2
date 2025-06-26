<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>予定登録</title>
<link rel="stylesheet"  href="<c:url value='/css/schedule_regi.css' />">
</head>
<body>
<%@ include file="header.jsp" %>
	<h1 class=page-title>${selectedDate}</h1>                                        <!-- 日付を表示 -->
	<!-- 戻る画像ボタン -->
	<div>
		<span class="back_text">前に戻る</span>	<br>												<!-- cssでmargin0にする？ -->
		<a href="javascript:history.back();">
 			<img src="<c:url value='/images/back.png' />" alt="戻る" class="back_button">
		</a>	
	</div>
	
	<div class="aho">
		<h2>登録</h2>													<!-- 登録と一覧は縦に並べて表示する -->
		<form method="POST" action="<c:url value='/CalendarServlet' />">
			タイトル<input type="text" name="title"  placeholder="お名前：予定の内容"><br>
			時間<input type="time" name="nowTime" value="${e.nowTime}" required><br>
			<label for="memo">メモ</label><br>
			<textarea name="memo" rows="5" cols="40"></textarea><br>
			ワンコID <input type="text" name="calendarDogId" required>
			<!-- ワンコ名<input type="text" name="dogName"><br> -->
			<input type="hidden" name="calendarDate" value="${selectedDate}">
			<%-- <select name="selewanko">
	  			<c:forEach var="dog" items="${wankoList}">
	    			<option value="${dog.wankoDogId}">${dog.dogName}</option>
	  			</c:forEach>
			</select>					 --%>
			<input type="hidden" name="action" value="regist">
			<input type="hidden" name="year" value="${param.year }">
			<input type="hidden" name="month" value="${param.month }">
			<input type="hidden" name="count" value="${param.count }">
			
			<input type="submit" name="regist" value="登録">
		</form>	<br>
		
		<!-- 登録完了message -->
		<c:if test="${not empty sessionScope.message}">
		    <p>${sessionScope.message}</p>
		</c:if>
		<%
		    session.removeAttribute("message");
		%>
		
		<h2>一覧</h2>
		<c:forEach var="e" items="${scheList}">
			<div class="button-wrapper">
				<form method="POST" action="<c:url value='/CalendarServlet' />">
					タイトル<input type="text" name="title" value="${e.title}"><br>
					時間<input type="time" name="nowTime" value="${e.time}"><br>
					<label for="memo">メモ</label><br>
					<textarea name="memo" rows="5" cols="40">${e.calendarMemo}"</textarea><br>
					ワンコID <input type="text" name="calendarDogId" value="${e.calendarDogId}"><br>
					<%-- ワンコ名
					<select name="selewanko">
		  				<c:forEach var="dog" items="${wankoList}">
		  				<option value="${dog.wankoDogId}" 
		       		 		<c:if test="${dog.wankoDogId == sche.calendarDogId}">selected</c:if>>
		      				${dog.dogName}
		   				</option>
		    				<option value="${dog.wankoDogId}">${dog.dogName}</option>
		  				</c:forEach>
					</select>	 --%>
					<input type="hidden" name="calendarId" value="${e.calendarId}">
					
					<input type="hidden" name="year" value="${param.year}">
					<input type="hidden" name="month" value="${param.month}">
					<input type="hidden" name="count" value="${param.count}">
					
					<input type="hidden" name="action" value="update">
					<input type="submit" name="update" value="更新">
				</form>
				<form method="POST" action="<c:url value='/CalendarServlet' />"><!-- 削除 -->
				    <input type="hidden" name="action" value="delete">
				    <input type="hidden" name="calendarId" value="${e.calendarId}">
				    <input type="hidden" name="year" value="${param.year}">
				    <input type="hidden" name="month" value="${param.month}">
				    <input type="hidden" name="count" value="${param.count}">
				    
					<input type="hidden" name="action" value="delete">
					<input type="submit" name="delete" value="削除">
				</form>	
	</c:forEach><br><br><br>
	</div>
	<br><br>
	
</body>
<!-- JavaScript（ここから） -->
	<script>
	/* submitボタンをクリックしたときの処理 */
	function submitClick() {
	  /* 確認ダイアログボックスを表示します */
	  if (!window.confirm('実行します。よろしいですか？')) {
	    return false;
	  }
	}
	/* 日付を必須入力項目とします */
	  if (!formObj.time.value) {
	    errorMessageObj.textContent = '※日付を入力してください。';
	    return false;
	  }
	
	/* HTML要素をオブジェクトとして取得する */
	let formObjs = document.getElementsByClassName('');
	
	/* 取得したすべてのオブジェクトに同じイベントを適用する */
	for (let item of formObjs) {
	  item.onsubmit = submitClick;
	}
	</script>
<!-- JavaScript（ここまで） -->
</html>