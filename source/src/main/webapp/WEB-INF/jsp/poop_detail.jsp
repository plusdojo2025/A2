<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>うんち詳細</title>
<style>
  /* 見出しとひとつ前に戻るの色指定 */
 .headline {
 	color: #FFA500;     /* 文字色指定 */
 }
 
 .poopdetailform {
 	color: #FFA500;     /* 文字色指定 */
 }
</style>
</head>
<body>
	<!-- 登録者の名前も後で表示させる -->
	<h1 class="headline">
		<div style="display: flex; justify-content: space-between;">
  			<div style="text-align: left;">うんち詳細　<c:out value="${sessionScope.user.name}"/>さん</div>
        	<div style="text-align: right;">
        		<a href="javascript:history.back();">
				<span>ひとつ前に戻る</span>							<!-- cssでmargin0にする？ -->
				<img src="<c:url value='/images/back.png' />" alt="戻る">	<!-- 戻る画像ボタン -->
		    	</a>
        	</div>
		</div>
	</h1>
	<!-- 後でDBとひもづけてデータを取ってこれるようにする -->
	
	<form class="poopdetailform" method="POST" action="<c:url value='/PoopServlet' />"enctype="multipart/form-data">
	<c:forEach var="e" items="${pDogDet}" >
		<div>
			<ul>
				<li>時間<input type="time" name="nowTime" value="${e.nowTime}"></li><br>
				<li>日付<input type="date" name="date" value="${e.date}"></li><br>
				<li>写真追加<input type="file" name="photo" value="${e.photo}">
				<img src="${e.photo}" width="400"  alt="${e.dogName}のうんち写真"><br></li>
			</ul>
		</div>
		<div>
			<ul>
				<li>ワンコ選択<input type="text" name="name" value="${e.name}"></li><br>
				<li>色<input type="radio" name="color" value="${e.color}"></li><br>
				<li>硬さ<input type="radio" name="hardness" value="${e.hardness}"></li><br>
				<li>異常<input type="submit" name="abnormal" value="${e.abnormal}"></li><br>
				<li>メモ<input type="text" name="memo" value="${e.memo}"></li>
			</ul>
		</div>
		 <input type="hidden" name="poopId" value="${e.poopId}">
		<input type="submit" name="pbutt" value="更新"> <input type="submit" name="pbutt" value="削除">
	</c:forEach>
	</form>
	
	
	<!--  フッターここから --

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