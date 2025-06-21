<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
		<img src = "claeimages/back.png" alt= "戻る">
	</span></a>
	
	<h2>登録</h2>													<!-- 登録と一覧は縦に並べて表示する -->
	<form method="POST" action="<c:url value='A2/CalendarServlet' />">
		タイトル<input type="text" name="title"><br>
		時間<input type="time" name="nowTime" value="${e.nowTime}"><br>
		メモ<input type="text" name="memo"><br>
		ワンコ情報 <!-- プルダウンが必要？ ログイン情報から犬の名前（複数いる場合があるので飼い主IDから？）を取得し、犬情報プルダウンに入れる。-->						
		<input type="submit" name="regist" value="登録">
	</form>	
	
	<h2>一覧</h2>
	<c:forEach var="e" items="${scheList}">
		<form method="POST" action="<c:url value='A2/CalendarServlet' />">
			タイトル<span>${e.title}</span><br>
			時間<span>${e.time}</span><br>
			メモ<span>${e.memo}</span><br>
			ワンコ情報 <!-- プルダウンが必要？ ログイン情報から犬の名前を取得し、犬情報プルダウンに入れる。-->
			<input type="submit" name="update" value="更新">
			<input type="submit" name="delete" value="削除">
		</form>	
	</c:forEach>
	
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