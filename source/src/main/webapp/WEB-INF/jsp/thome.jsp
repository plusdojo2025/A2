<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>トレーナー用ホーム画面</title>
</head>
<body>
<h1>カレンダー</h1>
<h2>月間予定</h2>
 <div id="weekcalendar"></div>

<h1>今日のわんこ</h1>
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

<script>



</script>

</body>
</html>