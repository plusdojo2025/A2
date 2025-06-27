<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"  href="<c:url value='/css/poop_detail.css' />">
<title>うんち詳細</title>

</head>
<body>
<%@ include file="header.jsp" %>
	<!-- 登録者の名前も後で表示させる -->
	<h1 class="headline">
		
  			うんち詳細　<c:out value="${sessionScope.user.name}"/>さん

	</h1>
	<div>
		<span class="back_text">前に戻る</span>	<br>												<!-- cssでmargin0にする？ -->
		<a href="javascript:history.back();">
 			<img src="<c:url value='/images/back.png' />" alt="戻る" class="back_button">
		</a>	
	</div>
	<!-- 後でDBとひもづけてデータを取ってこれるようにする -->
	
	
	
	<form class="poopdetailform" method="POST" action="<c:url value='/PoopServlet' />" enctype="multipart/form-data">
	<c:forEach var="e" items="${pDogDet}" >
		<div>
		<input type="hidden" name="oldPoopPhoto" value="${e.photo}">
		
			<ul>
				<li>時間<input type="time" name="nowTime" value="${e.time}"></li>
				<li>日付<input type="date" name="date" value="${e.date}"></li>				
				<li>写真追加<input type="file" name="photo" value="${e.photo}">
				<img src="${e.photo}" width="300"  alt="${e.dogName}のうんち写真"><br></li>
				
			</ul>
		</div>
		<div>
			<ul>
				<li>わんこID<input type="text" name="poopDogId" value="${e.poopDogId}"></li>
				<li>色を選んでください：</li>
	  	<div class="color-option">
		<label>
	      <input type="radio" name="color" value="1" <c:if test="${e.color == 1}">checked</c:if> >
	      <span class="color-label color-black"></span>
	    </label>
	    <label>
	      <input type="radio" name="color" value="2"<c:if test="${e.color == 2}">checked</c:if> >
	      <span class="color-label color-yellow"></span>
	    </label>
	    <label>
	      <input type="radio" name="color" value="3"<c:if test="${e.color == 3}">checked</c:if> >
	      <span class="color-label color-brown"></span>
	    </label>
	    <label>
	      <input type="radio" name="color" value="4"<c:if test="${e.color == 4}">checked</c:if> >
	      <span class="color-label color-brownred"></span>
	    </label>
	    <label>
	     	<input type="radio" name="color" value="5"<c:if test="${e.color == 5}">checked</c:if> >
	     	<span class="color-label color-red"></span>
	   	</label>
	  	</div>
				<!-- 硬さの選択 -->
		<p>硬さを選んでください：</p>
	  	<div class="hardness-option">
	    <label>
	      <input type="radio" name="hardness" value="1"<c:if test="${e.hardness == '1'}">checked</c:if>>硬
	      <span class="hardness-label hardness-硬"></span>
	    </label>
	    <label>
	      <input type="radio" name="hardness" value="2"<c:if test="${e.hardness == '2'}">checked</c:if>>やや硬
	      <span class="hardness-label hardness-やや硬"></span>
	    </label>
	    <label>
	      <input type="radio" name="hardness" value="3"<c:if test="${e.hardness == '3'}">checked</c:if>>普通
	      <span class="hardness-label hardness-普"></span>
	    </label>
	    <label>
	      <input type="radio" name="hardness" value="4"<c:if test="${e.hardness == '4'}">checked</c:if>>柔
	      <span class="hardness-label hardness-やや柔"></span>
	    </label>
	    <label>
	      <input type="radio" name="hardness" value="5"<c:if test="${e.hardness == '5'}">checked</c:if>> 柔
	      <span class="hardness-label hardness-柔"></span>
	    </label>
	    </div>
	    
			
	    <div>
	    <p>異常</p>
	    <label>	    
				<input type="radio" name="abnormal" value="true"<c:if test="${e.abnormal == 'true'}">checked</c:if>>あり
		</label>
		<label>
				<input type="radio" name="abnormal" value="false"<c:if test="${e.abnormal == 'false'}">checked</c:if>>なし<br>
		</label>
		</div>
		<div>
			<ul>
				<li>メモ<input type="text" name="memo" value="${e.memo}"></li>
			</ul>
		</div>
		 <input type="hidden" name="poopId" value="${e.poopId}">
		<input type="submit" name="pbutt" value="更新"> <input type="submit" name="pbutt" value="削除">
	
	
	</c:forEach>
	</form>
	<p>${msg}</p>
	
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