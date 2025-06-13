<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>うんち詳細</title>
</head>
<body>
	<!-- 登録者の名前も後で表示させる -->
	<h1>うんち詳細　（名前）さん</h1>
	<!-- メイン -->
	<form>

		時間　<input type="time">
		日付　<input type="date">
		写真追加 <input type="text">
			<details>
				<summary>ワンコ選択▼</summary>
					<li></li>
			</details>
			<details>
				<summary>色▼</summary>
					<li></li>
			</details>
			<details>
				<summary>硬さ▼</summary>
					<li></li>
			</details>
		異常　<input type="submit" value="あり"> <input type="submit" value="なし">
		メモ　<input type="text">
		<input type="submit" value="更新"> <input type="submit" value="削除">
	</form>
	<!-- メインここまで -->
	<!--  フッターここから -->
	<footer>
	</footer>
	<!--  フッターここまで -->
</body>
</html>