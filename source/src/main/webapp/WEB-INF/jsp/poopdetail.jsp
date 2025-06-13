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

		時間　<input type="time"><br>
		日付　<input type="date"><br>
		写真追加 <input type="text"><br>
			<details>
				<!-- ワンコをプルダウンで選択できるようにする -->
				<summary>ワンコ選択</summary>
			</details>
			<details>
				<!-- 色を選択できるようにしたい -->
				<summary>色</summary>
			</details>
			<details>
				<summary>硬さ</summary>
					<li>硬</li>
					<li>やや硬</li>
					<li>普</li>
					<li>やや柔</li>
					<li>柔</li>
			</details>
		異常　<input type="submit" value="あり"> <input type="submit" value="なし"><br>
		メモ　<input type="text"><br>
		<input type="submit" value="更新"> <input type="submit" value="削除">
	</form>
	<!-- メインここまで -->
	<!--  フッターここから -->
	<footer>
	</footer>
	<!--  フッターここまで -->
</body>
</html>