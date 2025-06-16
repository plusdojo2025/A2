<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>うんち登録</title>
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
	    border-color: white;
	    transform: scale(1.5);/*拡大*/
	  }
</style>
</head>
<body>
	<!-- 1つ前へ戻る -->
	</select><li><a href="/webapp/PoopServlet">1つ前に戻る</a></li>
	<!-- ウンチ管理の文字表示 -->
	<h1>うんち管理</h1>
	<!-- 登録者の名前も後で表示させる -->
	<h2>登録者の名前</h2>
	<!-- ワンコ選択 -->
	<h3>わんこ選択</h3>
	<!-- メイン -->
	<form>
	<!-- ワンコ選択 -->
	<select name = "dogname"><br>
		<option>わんこ選択▼</option><br>
	時間	<input type = "time"><br>
	日付	<input type = "date"><br>
	写真追加<input type = "file" name ="photo"><br>
	<!-- 色の選択 -->
	<p>色を選んでください：</p>
  	<div class="color-option">
	<label>
      <input type="radio" name="color" value="black">
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
	<!-- 硬さの選択 -->
	<p>硬さを選んでください：</p>
  	<div class="hardness-option">
    <label>
      <input type="radio" name="hardness" value="hard">硬
      <span class="hardness-label hardness-硬"></span>
    </label>
    <label>
      <input type="radio" name="hardness" value="slightly hard">やや硬
      <span class="hardness-label hardness-やや硬"></span>
    </label>
    <label>
      <input type="radio" name="hardness" value="normal">普通
      <span class="hardness-label hardness-普"></span>
    </label>
    <label>
      <input type="radio" name="hardness" value="slightly soft">やや柔
      <span class="hardness-label hardness-やや柔"></span>
    </label>
    <label>
      <input type="radio" name="hardness" value="soft">柔
      <span class="hardness-label hardness-柔"></span>
    </label>
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