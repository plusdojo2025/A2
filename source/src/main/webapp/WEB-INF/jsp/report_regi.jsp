<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>報告登録</title>
</head>
<body>
	<h1>報告登録</h1>
	<!-- ワンコ選択 -->
	<select name="dogName">
		<!-- ワンコ名をプルダウンで選択できるようにする -->
		<option value="${e.dogName}" selected>ワンコ選択</option>
	</select>
	<div>
		<span class="back_text">前に戻る</span>	<br>												<!-- cssでmargin0にする？ -->
		<a href="<c:url value="javascript:history.back();"/>" >
 			<img src="<c:url value='/images/back.png' />" alt="戻る" class="back_button">
		</a>	
	</div>
<!-- エラー表示 -->

<form id="report_form" action="<c:url value='/ReportServlet'/>" method="post" onsubmit = "return checkForm()">
	<input type="hidden" name="action" value="insert">
	<input type="hidden" name="reportId" value=0>
	<input type="hidden" name="reportDogId" value="${reportDog.id}">

	<label>・ ごはん<br>
		<input type="radio" name="food" value="true">食べた
		<input type="radio" name="food" value="false">食べてない
	</label><br>
	<label>・ 日付
		<input type="datetime-local" name="reportDate">
	</label><br>
	<label>・ 散歩
		<input type="time" name="walk">
	</label><br>
	<label>・ 様子
		<input type="radio" name="reportState" value="true">異常なし
		<input type="radio" name="reportState" value="false">異常あり
	</label><br>
	<label>・ トレーニング
		<input type="text" name="training">
	</label><br>
	<label>・ メモ
		<input type="text" name="reportMemo">
	</label><br>
	
	<input type="submit" value="登録">
	
	<div id = "error"></div>
	</form>


<script>
	function checkForm() {
		//取得する
		let food = document.querySelector('input[name="food"]:checked');
		let walk = document.querySelector('input[name="walk"]').value.trim();
		let state = document.querySelector('input[name="reportState"]:checked');
		let training = document.querySelector('input[name="training"]').value.trim();
		//一つでも未入力ならエラー
		if(!food || walk ==="" || !state || training ==="") {
			document.getElementById("error").textContent = "ごはん、散歩、様子、トレーニングは必須項目です。";
			//フォーム送信をやめる
			return false;
		}
		//フォーム送信
		return true;
	}
</script>
</body>
</html>