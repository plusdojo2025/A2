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
	<a href = "javascript:history.back()">
		<span>ひとつ前に戻る
		<img src = "images/back.png" alt= "戻る">
	</span></a>
	<!-- ウンチ管理の文字表示 -->
	<h1>うんち管理</h1>
	<!-- 登録者の名前も後で表示させる -->
	<h2>登録者の名前</h2>
	<!-- ワンコ選択 -->
	<!-- メイン -->
	<form>
	<c:forEach var="e" items="" >
	<form method="POST" action="">
		時間<input type="time" name="nowTime" value="${e.nowTime}"><br>
		日付<input type="date" name="date" value="${e.date}"><br>
		写真追加<input type = "file" name = "photo"><br>
		わんこ選択<input type="text" name="name" value="${e.name}"><br>
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
		異常<input type="checkbox" name="abnormal" value="true"><br>
		メモ<input type="text" name="memo" value="${e.memo}"><br>
		<input type="submit" value="登録"><br>
	</form>
	</c:forEach>
	
	
<!-- メインここまで -->
<!--  フッターここから -->
<footer>
</footer>
<!--  フッターここまで -->
<!-- JavaScript（ここから） -->
	<script>
	/* submitボタンをクリックしたときの処理 */
	function submitClick() {
	  /* 確認ダイアログボックスを表示します */
	  if (!window.confirm('実行します。よろしいですか？')) {
	    return false;
	  }
	}
	
	/* HTML要素をオブジェクトとして取得する */
	let formObjs = document.getElementsByClassName('');
	
	/* 取得したすべてのオブジェクトに同じイベントを適用する */
	for (let item of formObjs) {
	  item.onsubmit = submitClick;
	}
	</script>
<!-- JavaScript（ここまで） -->
</body>
</html>