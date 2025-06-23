<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>報告詳細（企業用）</title>
</head>
<body>
	<h1>
		<div style="display: flex; justify-content: space-between;">
  			<div style="text-align: left;">報告詳細</div>
        	<div style="text-align: right;">
        		<a href="javascript:history.back();">
				<span>ひとつ前に戻る</span>							<!-- cssでmargin0にする？ -->
				<img src="<c:url value='/images/back.png' />" alt="戻る"/> 		<!-- 戻る画像ボタン -->
		    	</a>
        	</div>
		</div>
	</h1>
	<!-- 後でDBとひもづけてデータを取ってこれるようにする -->
	<c:forEach var="e" items="" >
	<form method="POST" action="<c:url value='/ReportServlet' />">
	<input type="hidden" name="action" id="action" value="update">
			<select name="dogName">
				<!-- ワンコ名をプルダウンで選択できるようにする -->
				<option value="${e.dogName}" selected>ワンコ選択</option>
			</select>
		<ul>
			
			<li>ごはん<input type="submit" name="food" value="${e.food}"></li>
			<li>日付<input type="date" name="reportDate" value="${e.date}"></li>
			<li>散歩<input type="number" name="walk" value="${e.walk}">分</li>
			<li>様子<input type="submit" name="state" value="${e.state}"></li>
			<li>トレーニング<input type="text" name="training" value="${e.training}"></li>
			<li>メモ<input type="text" name="reportMemo" value="${e.reportMemo}"></li>
			
		</ul>
		<input type="submit" value="更新" onclick="document.getElementById('action').value='update'"> <input type="submit" value="削除" onclick="document.getElementById('action').value='delete'">
	</form>
	</c:forEach>

	<!--  フッターここから -->

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