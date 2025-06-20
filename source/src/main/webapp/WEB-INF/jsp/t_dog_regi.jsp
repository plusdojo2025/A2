<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>わんこ登録</title>
</head>
<body>

<h1>わんこ登録</h1>
<form method="post" action="<c:url value='/WankoServlet'/>">
	
	写真<input type="file" name="dogPhoto" accept="image/*" onchange="previewImage(this);"><br>
	わんこの名前<input type="text" name="dogName"><br>
	飼い主ID<input type="text" name="name"><br>
	ワクチン歴<input type="file" name="wakuchin"><br>
	去勢歴<input type="radio" name="kyosei" value="true">あり
				<input type="radio" name="kyosei" value="false">なし<br>
	性別<input type="radio" name="gender" value="true">オス
				<input type="radio" name="gender" value="false">メス<br>
	犬種<input type="text" name="dogBreed"><br>
	誕生日<input type="Date" name="dogBirth"><br>
	備考<input type="text" name="remarks" placeholder="備考 1"><br>
	<input type="submit" name="butt" value="登録"><br>
	
</form>
<div>
${msg}
</div>
<!--  	<table class="inputFields">
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
				<input type="text" name="dogName">
				</label>
			</td>
		</tr>
		<tr>
			<td>
				<label>飼い主ID<br>
				<input type="text" name="name">
				</label>
			</td>
		</tr>
		<tr>
			<td>
				<label>ワクチン歴<br>
				<input type="file" name="wakuchin">
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
		          <input type="submit" id="register" name="submit" value="登録">
		          <span id="error_message"></span>
		        </td>
	      	</tr>		
	</table>

-->




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


