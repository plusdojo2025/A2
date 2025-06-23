<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div style="text-align: center;">
		<span>トップへ</span><br>
		<img src="<c:url value='images/うんち.png'/> " alt="トップへ戻る" style= "width: 50px;" id="GoTop">			<!-- id定義済みGoTop -->
	</div>
</body>
<script>
	//上まで戻るボタン
	document.getElementById("GoTop").addEventListener("click",function(){
		window.scrollTo({ top:0,behaivor:'smooth'});
		});
</script>
</html>