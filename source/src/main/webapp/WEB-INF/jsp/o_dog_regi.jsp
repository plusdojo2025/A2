<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新規登録</title>
<style>
  body {
    font-family: sans-serif;
    background-color: #f9f9f9;
    margin: 0;
    padding: 20px;
    text-align: center; /* 中央寄せ */
  }

  h1 {
    margin-bottom: 30px;
    color:orange;
  }

  form {
    background: white;
    padding: 30px;
    border-radius: 8px;
    display: inline-block;
    text-align: left;
    width: 100%;
    max-width: 500px;
    box-shadow: 0 0 10px rgba(0,0,0,0.1);
  }

  table.inputFields {
    width: 100%;
  }

  td {
    padding: 10px 0;
  }

  label {
    display: block;
    font-weight: bold;
    margin-bottom: 5px;
  }

  input[type="text"],
  input[type="date"],
  input[type="file"],
  select {
    width: 100%;
    padding: 8px;
    border: 1px solid #ccc;
    border-radius: 4px;
    box-sizing: border-box;
  }

  input[type="radio"] {
    margin-right: 5px;
  }

  .bikou-input {
    display: flex;
    gap: 5px;
    margin-bottom: 5px;
  }

  .bikou-input input[type="text"] {
    flex: 1;
  }

  button {
    padding: 5px 10px;
    font-size: 14px;
    border: none;
    background-color: #ddd;
    border-radius: 4px;
    cursor: pointer;
  }

  button:hover {
    background-color: #ccc;
  }

  #register {
    margin-top: 20px;
    width: 100%;
    padding: 10px;
    background-color: #4CAF50;
    color: white;
    font-weight: bold;
  }

  #error_message {
    color: red;
    margin-top: 10px;
    text-align: center;
  }

  canvas#preview {
    display: block;
    margin: 10px auto;
    max-width: 200px;
  }
</style>
</head>
<body>
<%@ include file="header.jsp" %>
<h1>わんこ登録</h1>
<form method="POST" action="<c:url value='/WankoServlet'/> " enctype="multipart/form-data">
	<table class="inputFields">
		<tr> 
			<td>
				<label>写真<br>
				<input type="file" name="dogPhoto" accept="image/*" onchange="previewImage(this);"><br>
				<canvas id="preview" style="max-width:200px;"></canvas><br>
				</label>
			</td>
		</tr>
		<tr>
			<td>
				<label>わんこの名前<br>
				<input type="text" name="dogName" >
				</label>
			</td>
		</tr>
		<tr>
			<td>
				<label>飼い主ID<br>
				<a>${user.userNameId}</a>
				<input type="hidden" name="userNameId" value="${user.userNameId}">
				</label>
			</td>
		</tr>
		<tr>
			<td>
				<label>ワクチン歴<br>
				<input type="file" name="wakuchin" accept="image/*">
				</label>
			</td>
		</tr>
		<tr>
			<td>
				<label>去勢歴<br>
				<input type="radio" name="kyosei" value="true">あり
				<input type="radio" name="kyosei" value="false">なし
				</label>
			</td>
		</tr>
		<tr>
			<td>
				<label>性別<br>
				<input type="radio" name="gender" value="true">オス
				<input type="radio" name="gender" value="false">メス
				</label>
			</td>
		</tr>
		<tr>
			<td>
				<label>犬種<br>
				<input type="text" name="dogBreed">
				</label>
			</td>
		</tr>
		<tr>
			<td>
				<label>誕生日<br>
				<input type="Date" name="dogBirth">
				</label>
			</td>
		</tr>
		<tr>
		<td>
			<label>備考（最大5個まで追加可）<br>
				<div id="bikou-container">
					<div class="bikou-input">	
						<input type="text" name="remarks" placeholder="備考 1">
						<button type="button" onclick="removeBikou(this)">×</button>
					</div>
				</div>
				<button type="button" onclick="addBikou()">＋追加</button>
			</label>
		</td>
		</tr>
	      	<tr>
		        <td colspan="2">
		          <input type="submit" id="register" name="butt" value="登録">
		          <span id="error_message"></span>
		        </td>
	      	</tr>		
	</table>





</form>
<p>${msg}</p>
<script>

function previewImage(obj){

	var fileReader = new FileReader();

	// 読み込み後に実行する処理
	fileReader.onload = (function() {

		// canvas にプレビュー画像を表示
		var canvas = document.getElementById('preview');
		var ctx = canvas.getContext('2d');
		var image = new Image();
		image.src = fileReader.result;
		console.log(fileReader.result) // ← (確認用)

		image.onload = (function () {
			canvas.width = image.width;
			canvas.height = image.height;
			ctx.drawImage(image, 0, 0);
		});
	});
	// 画像読み込み
	fileReader.readAsDataURL(obj.files[0]);
	console.log(fileReader.result) // ← (確認用)null
}


const maxBikou = 5;

function addBikou() {
	const container = document.getElementById("bikou-container");
	const inputs = container.getElementsByClassName("bikou-input");

	if (inputs.length >= maxBikou) {
		alert("備考は最大 " + maxBikou + " 個までです。");
		return;
	}

	const div = document.createElement("div");
	div.className = "bikou-input";

	const input = document.createElement("input");
	input.type = "text";
	input.name = "remarks-temp"; // 複数扱いたい場合は "remarks[]" もOK
	input.placeholder = "備考 " + (inputs.length + 1);

	const removeBtn = document.createElement("button");
	removeBtn.type = "button";
	removeBtn.textContent = "×";
	removeBtn.onclick = function () {
		removeBikou(removeBtn);
	};

	div.appendChild(input);
	div.appendChild(removeBtn);
	container.appendChild(div);

	renumberBikou();
}

function removeBikou(button) {
	const container = document.getElementById("bikou-container");
	container.removeChild(button.parentElement);
	renumberBikou();
}

function renumberBikou() {
	const inputs = document.querySelectorAll("#bikou-container .bikou-input input");
	inputs.forEach((input, index) => {
		input.name = "remarks" + (index + 1);
		input.placeholder = "備考 " + (index + 1);
	});
}
</script>
</body>
</html>
