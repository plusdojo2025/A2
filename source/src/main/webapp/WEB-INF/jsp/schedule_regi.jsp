<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>予定登録</title>
</head>
<body>
	<h1>〇月〇日</h1>                                        <!-- 日付を表示 -->
	<a href = "javascript:history.back()">
		<span>ひとつ前に戻る
		<img src = "images/back.png" alt= "戻る">
	</span></a>
	<h2>登録</h2>													<!-- 登録と一覧は縦に並べて表示する -->
	<form>
	タイトル<input type="text" name="title"><br>
	時間<input type="time" name="nowTime" value="${e.nowTime}"><br>
	メモ<input type="text" name="memo"><br>
	ワンコ情報
	<input type="text" name="name" value="${e.name}"><br>						
	<input type="submit" name="regist" value="登録">
	<h2>一覧</h2>
	タイトル<input type="text" name="title"><br>
	時間<input type = "time"><br>
	メモ<input type="text" name="memo"><br>
	ワンコ情報
	<input type="text" name="name" value="${e.name}"><br>
	<input type="submit" name="regist" value="登録">
	</form>	
</body>
<!-- JavaScript（ここから） -->
	<script>
	/* submitボタンをクリックしたときの処理 */
	function submitClick() {
	  /* 確認ダイアログボックスを表示します */
	  if (!window.confirm('実行します。よろしいですか？')) {
	    return false;
	  }
	}
	/* 日付を必須入力項目とします */
	  if (!formObj.time.value) {
	    errorMessageObj.textContent = '※日付を入力してください。';
	    return false;
	  }
	
	/* HTML要素をオブジェクトとして取得する */
	let formObjs = document.getElementsByClassName('');
	
	/* 取得したすべてのオブジェクトに同じイベントを適用する */
	for (let item of formObjs) {
	  item.onsubmit = submitClick;
	}
	</script>
<!-- JavaScript（ここまで） -->
</html>