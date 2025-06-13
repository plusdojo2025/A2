<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン</title>
</head>
<body>
	<!-- ヘッダーの下ここから-->
	<nav>
		<ul>
			<li>うんち管理</li>
		</ul>
		<ul>
			<li>登録者の名前</li>
		</ul>
		<ul>
			<li>ワンコ選択</li>
		</ul>
		<ul>
			<li>ひとつ前に戻る</li>
		</ul>
		<ul>
			<li>時間</li>
			<li>日付</li>
			<li>写真追加</li>
		</ul>
	<!-- 色の選択 -->
		<ul>
			<li>色</li>
		</ul>
	<!-- 硬さの選択 -->	
		<ul>
			<li>硬さ</li>
			<li>硬</li>
			<li>やや硬</li>
			<li>普</li>
			<li>やや柔</li>
			<li>柔</li>
		</ul>
	<!-- 異常 -->
		<ul>
			<li>異常</li>
			<li>あり</li>
			<li>なし</li>
		</ul>
	<!-- メモ -->	
		<ul>
			<li>メモ</li>
		</ul>
	</nav>
	<!-- 登録 -->
		<ul>
			<li>登録</li>
		</ul>
	<!-- ヘッダーの下ここまで-->
	<form method="POST" action="/webapp/RegistServlet">
	メモ<input type="text" name="memo"><br>
</body>
</html>