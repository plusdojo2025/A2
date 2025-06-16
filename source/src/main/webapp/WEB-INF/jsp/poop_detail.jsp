<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>うんち詳細</title>
<style>
  .color-option {
    display: flex;
    gap: 20px;
    margin: 20px 0;
  }

  .color-option input[type="radio"] {
    display: none;
  }

  .color-label {
    width: 30px;
    height: 30px;
    border-radius: 50%;
    display: inline-block;
    cursor: pointer;
    border: 3px solid transparent;
  }

  /* 色ごとの背景 */
  .color-red { background-color: red; }
  .color-blue { background-color: blue; }
  .color-green { background-color: green; }
  .color-orange { background-color: orange; }
  .color-purple { background-color: purple; }

  /* 選択されたときの枠線 */
  input[type="radio"]:checked + .color-label {
    border-color: white;
  }
</style>
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
					<form>
						<div class="color-option">
							<label>
		      					<input type="radio" name="color" value="red">
		      					<span class="color-label color-red"></span>
		    				</label>
							<label>
								<input type="radio" name="color" value="blue">
								<span class="color-label color-blue"></span>
							</label>
							<label>
								<input type="radio" name="color" value="green">
								<span class="color-label color-green"></span>
							</label>
							<label>
								<input type="radio" name="color" value="orange">
								<span class="color-label color-orange"></span>
							</label>
							<label>
								<input type="radio" name="color" value="purple">
								<span class="color-label color-purple"></span>
							</label>
						</div>
					</form>
			</details>
			<details>
				<summary>硬さ</summary>
					<form>
						<input type="submit" value="硬">
						<input type="submit" value="やや硬">
						<input type="submit" value="普">
						<input type="submit" value="やや柔">
						<input type="submit" value="柔">
					</form>
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