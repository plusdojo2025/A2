<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ワンコ詳細</title>
<link rel="stylesheet"  href="<c:url value='/css/t_dog_detail.css' />">
</head>
<body>
	<h1>ワンコ詳細　（名前）さん</h1>
        	<div>
				<span class="back_text">前に戻る</span>	<br>												<!-- cssでmargin0にする？ -->
				<a href="<c:url value='javascript:history.back();'/>" >
 					<img src="<c:url value='/images/back.png' />" alt="戻る" class="back_button">
				</a>	
			</div>
		
	
<form>
写真追加 <input type="text"><br>
 <p class="dog_deta">
	<c:forEach var="e" items="${dogid}" >
    	<form method="POST" action="<c:url value='/WankoServlet'/>" class="dog_datail"> <!-- A2の後に何サーブレットを指定するか -->
     
		名前<input type="text" name="name" value="${e.name}"><br>
		性別<input type="submit" name="gender" value="${e.gender}"><br>
		去勢歴<input type="submit" name="kyosei" value="${e.kyosei}"><br>
		犬種<input type="text" name="dogbreed" value="${e.dogbreed}"><br>
		誕生日<input type="text" name="dogbirth" value="${e.dogbirth}"><br>
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
  
  
		<input type="submit" name="submit" value="更新">
		<input type="submit" name="submit" value="削除"><br>
	</form>
</c:forEach>
</form>

<h2>飼い主情報</h2>
<c:forEach var="u" items="${nameid}">
飼い主名前 <input type="text" name="nameid" value="${u.nameid}"><br>
飼い主フリガナ <input type="text" name="ruby" value="${u.ruby}"><br>
生年月日 <input type="text" name="birth" value="${u.birth}"><br>
電話番号 <input type="text" name="uphone" value="${u.ruby}"><br>
連絡先 <input type="text" name="uphone2" value="${u.ruby}"><br>
</c:forEach>
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