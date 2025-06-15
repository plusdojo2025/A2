<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>うんち登録</title>
</head>
<body>
	<!-- 登録者の名前も後で表示させる -->
	<h1>うんち登録　（名前）さん</h1>
	<!-- ワンコ選択 -->
	<h2>ワンコ選択</h2>
	<!-- メイン -->
	<form>
		時間	<input type = "time"><br>
		日付	<input type = "date"><br>
		写真追加<input type = "file" name ="photo"><br>
		<!-- 色の選択やり方分からない -->
		色<input type = "select" name = "color"><br>
		<div>
			<ul>
				<li>黒</li>
				<li>黄色</li>
				<li>茶色</li>
				<li>こげ茶色</li>
				<li>赤</li>
			</ul>
		</div>
		硬さ	<input type = "select" name = "hardness"><br>
		<div>
			<ul>
				<li>硬</li>
				<li>やや硬</li>
				<li>普</li>
				<li>やや柔</li>
				<li>柔</li>
			</ul>
		</div>
		異常<input type = "submit" name = "abnormal" value = "あり"><input type = "submit" name = "abnormal" value = "なし"><br>
		メモ<input type = "text" name ="memo"><br>
		<input type="submit" name="regist" value="登録">
	</form>
	<!-- メインここまで -->
	<!--  フッターここから -->
	<footer>
	</footer>
	<!--  フッターここまで -->
</body>
</html>