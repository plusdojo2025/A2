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
  .color-black { background-color: #444443; }
  .color-yellow { background-color: #EBD469; }
  .color-brown { background-color: #AD795B; }
  .color-brownred { background-color: #A44F30; }
  .color-red { background-color: #A52A2A; }

  /* 選択されたときの枠線 */
  input[type="radio"]:checked + .color-label {
    border-color: #FFA500;
  }
</style>
</head>
<body>
	<!-- 登録者の名前も後で表示させる -->
	<h1>
		<div style="display: flex; justify-content: space-between;">
  			<div style="text-align: left;">うんち詳細　（名前）さん</div>
        	<div style="text-align: right;">
        		<a href="javascript:history.back();">
				<span>ひとつ前に戻る</span>							<!-- cssでmargin0にする？ -->
				<img src="/webapp/img/back.png" alt="戻る">		<!-- 戻る画像ボタン -->
		    	</a>
        	</div>
		</div>
	</h1>
	<form>

		時間 <input type="time"><br>
		日付 <input type="date"><br>
		写真追加 <input type="file" name="photo" accept="image/*"><br>
			<select name="dogName">
			<!-- ワンコ名をプルダウンで選択できるようにする -->
			<option value="" selected>ワンコ選択</option>
			</select>
			<details>
				<!-- 色を選択できるようにしたい -->
				<summary>色</summary>
					<form>
						<div class="color-option">
							<label>
		      					<input type="radio" name="color" value="black" >
		      					<span class="color-label color-black"></span>
		    				</label>
							<label>
								<input type="radio" name="color" value="yellow">
								<span class="color-label color-yellow"></span>
							</label>
							<label>
								<input type="radio" name="color" value="brown">
								<span class="color-label color-brown"></span>
							</label>
							<label>
								<input type="radio" name="color" value="brownred">
								<span class="color-label color-brownred"></span>
							</label>
							<label>
								<input type="radio" name="color" value="red">
								<span class="color-label color-red"></span>
							</label>
						</div>
					</form>
			</details>
			<details>
				<summary>硬さ</summary>
					<form>
						<input type="radio" name="hardness" value="硬">硬
						<input type="radio" name="hardness" value="やや硬">やや硬
						<input type="radio" name="hardness" value="普">普
						<input type="radio" name="hardness" value="やや柔">やや柔
						<input type="radio" name="hardness" value="柔">柔
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