<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ワンコ詳細</title>
</head>
<body>
<%@ include file="header.jsp" %>
	<h1>
		<div style="display: flex; justify-content: space-between;">
  			<div style="text-align: left;">ワンコ詳細　<c:out value="${sessionScope.user.name}" />さん</div>
        	<div style="text-align: right;">
        		<a href="javascript:history.back();"></a>	
				<span class="back_text">前に戻る</span>	<br>
        		<a href="<c:url value="javascript:history.back();"/>" >
 					<img src="<c:url value='/images/back.png' />"  alt="戻る" class="back_button">
				</a>
			</div>
		</div>	
	</h1>	
<form method="POST" action="<c:url value='/WankoServlet'/>" class="dog_datail" enctype="multipart/form-data">
<c:forEach var="e" items="${oDogDet}" >
<input type="hidden" name="oldDogPhoto" value="${e.dogPhoto}">
写真追加 <input type="file" name="dogPhoto"  accept="image/*"   onchange="previewImage(this);"><br>
				<img src="${e.dogPhoto}" width="400"  alt="${e.dogName}の写真"><br>
 <p class="dog_deta">
					
    	<!-- A2の後に何サーブレットを指定するか -->
        <input type="hidden" name="id" value="${e.wankoDogId}">
		名前<input type="text" name="dogName" value="${e.dogName}"><br>
		性別	<input type="radio" name="gender" value="1"<c:if test="${e.gender == 'true'}">checked</c:if>>オス
			<input type="radio" name="gender" value="0"<c:if test="${e.gender == 'false'}">checked</c:if>>メス<br>
		ワクチン歴<input type="text" name="wakuchin" value="${e.wakuchin }"><br>
		去勢歴<input type="radio" name="kyosei" value="1"<c:if test="${e.kyosei == 'true'}">checked</c:if>>あり
			<input type="radio" name="kyosei" value="0"<c:if test="${e.kyosei == 'false'}">checked</c:if>>なし<br><br>
		犬種<input type="text" name="dogBreed" value="${e.dogBreed}"><br>
		誕生日<input type="date" name="dogBirth" value="${e.dogBirth}"><br>
		<table class="inputFields">
        <tr class="inputField">
            <th>
                <input type="text" name="content[]" placeholder="備考"></th>
            <td>
                <!--input要素を削除するボタン-->
                <button class="deleteFieldBtn">入力欄を削除</button></td>
        </tr>
    </table>
    <!--input要素を追加するボタン-->
    <button class="addFieldBtn">入力欄を追加</button>
  
  
		<input type="submit" name="butt" value="更新">
		<input type="submit" name="butt" value="削除" ><br>
	
</c:forEach>
</form>
<p>${msg}</p>
<script>


/* submitボタンをクリックしたときの処理 */
function submitClick() {

}

/* HTML要素をオブジェクトとして取得する */
let formObjs = document.getElementsByClassName('dog_datail');

/* 取得したすべてのオブジェクトに同じイベントを適用する */
for (let item of formObjs) {
  item.onsubmit = submitClick;
}

//イベントリスナーでform上の全てのclickイベントを監視する
document.forms[0].addEventListener("click", function (e) {
    var inputFielsContainer = e.target.parentNode.querySelector(".inputFields");
    // クリックされた要素に、addFieldBtnが含まれていたら
    if (e.target.classList.contains("addFieldBtn")) {
        e.preventDefault();
        
        // 現在の入力欄（.inputField）の数をカウント
        const currentFields = inputFielsContainer.querySelectorAll(".inputField").length;

        if (currentFields >= 5) {
          alert("入力欄は最大5つまでです");
          return;
        }
        var field = document.createElement("tr");
        field.classList.add("inputField");
        field.innerHTML = `
        <th><input type="text" name="content[]" placeholder="備考"></th>
        <td><button class="deleteFieldBtn">入力欄を削除</button></td>
        `;
        var clone = field.cloneNode(true);
        inputFielsContainer.appendChild(clone);
    }

    // クリックされた要素に、deleteFieldBtnクラスが含まれていたら
    else if (e.target.classList.contains("deleteFieldBtn")) {
        e.preventDefault();
        var deleteFieldBtns = document.querySelectorAll(".deleteFieldBtn");
        //deleteFieldBtクラスを持つ要素がDOM上に1つしかない場合は削除できないようにする
        if(deleteFieldBtns.length == 1){
            alert("入力欄は最低1つ必要です");
            return;
        }else{
            var field = e.target.parentNode.parentNode;
            field.remove();
        }
    }
});

//エンターキー押下でinput要素が追加されないようにする
document.addEventListener("keypress", function (e) {
    if (e.key === "Enter") {
        e.preventDefault();
    }
});

</script>
</body>
</html>