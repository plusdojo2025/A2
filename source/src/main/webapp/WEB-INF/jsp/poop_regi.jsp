<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>うんち登録</title>
<link rel="stylesheet" href="<c:url value='/css/poop_regi.css' />">
</head>
<body>
<%@ include file="header.jsp" %>
	<!-- 1つ前へ戻る -->
	<div class="back-option">
	<a href = "javascript:history.back()">
		<span>ひとつ前に戻る<br>
		<img src="<c:url value='/images/back.png'/>" alt= "戻る">
	</span></a>
	</div>
	<!-- ウンチ管理の文字表示 -->
	<h1>うんち管理</h1>
	<!-- 登録者の名前も後で表示させる -->

	<!-- ワンコ選択 -->
	<!-- メイン -->
	<div>${wankoDogId}</div>
	
	<form method="POST" action="<c:url value='/PoopServlet'/> " enctype="multipart/form-data">
	<div class="left-section">
		時間<input type="time" name="nowTime" required><br>
		日付<input type="date" name="date" required><br>
		<label>
		写真追加<input type="file" name="photo" accept="image/*" onchange="previewImage(this);" required><br>
				<canvas id="preview" style="max-width:200px;"></canvas><br>
		</label>
		</div>
	<div class="right-section">
		わんこID<input type="text" name="PoopDogId" value="${wankoDogId}" required><br>
		
			<!-- 色の選択 -->
	
		<p>色を選んでください：</p>
	  	<div class="color-option">
		<label>
	      <input type="radio" name="color" value="1" >
	      <span class="color-label color-black"></span>
	    </label>
	    <label>
	      <input type="radio" name="color" value="2">
	      <span class="color-label color-yellow"></span>
	    </label>
	    <label>
	      <input type="radio" name="color" value="3"checked>
	      <span class="color-label color-brown"></span>
	    </label>
	    <label>
	      <input type="radio" name="color" value="4">
	      <span class="color-label color-brownred"></span>
	    </label>
	    <label>
	     	<input type="radio" name="color" value="5">
	     	<span class="color-label color-red"></span>
	   	</label>
	  	</div>
		<!-- 硬さの選択 -->
		<p>硬さを選んでください：</p>
	  	<div class="hardness-option">
	    <label>
	      <input type="radio" name="hardness" value="1">硬
	      <span class="hardness-label hardness-硬"></span>
	    </label>
	    <label>
	      <input type="radio" name="hardness" value="2">やや硬
	      <span class="hardness-label hardness-やや硬"></span>
	    </label>
	    <label>
	      <input type="radio" name="hardness" value="3"checked>普通
	      <span class="hardness-label hardness-普"></span>
	    </label>
	    <label>
	      <input type="radio" name="hardness" value="4">やや柔
	      <span class="hardness-label hardness-やや柔"></span>
	    </label>
	    <label>
	      <input type="radio" name="hardness" value="5">柔
	      <span class="hardness-label hardness-柔"></span>
	    </label>
	    </div>
		異常<input type="radio" name="abnormal" value="1" required>あり
			<input type="radio" name="abnormal" value="0"checked>なし<br>
		メモ<input type="text" name="memo" value="${e.memo}" required><br>
		<input type="submit" name="pbutt" value="登録"><br>
		</div>
	</form>
	<div>${msg} </div>
	
	
<!-- メインここまで -->
<!--  フッターここから -->
<footer>
</footer>
<!--  フッターここまで -->
<!-- JavaScript（ここから） -->
<script>

function previewImage(input) {
    const file = input.files[0];
    if (!file) return;

    const reader = new FileReader();
    reader.onload = function(e) {
        const img = new Image();
        img.onload = function() {
            const canvas = document.getElementById('preview');
            const ctx = canvas.getContext('2d');
            
            // キャンバスのサイズを画像に合わせる（または固定サイズ）
            canvas.width = img.width;
            canvas.height = img.height;

            ctx.clearRect(0, 0, canvas.width, canvas.height);
            ctx.drawImage(img, 0, 0);
        };
        img.src = e.target.result;
    };
    reader.readAsDataURL(file);
}

</script>
<!-- JavaScript（ここまで） -->
</body>
</html>