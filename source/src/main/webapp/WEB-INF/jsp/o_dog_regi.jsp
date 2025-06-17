<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>わんこ登録</h1>
<form method="post" action="/webapp/WankoServlet">
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
				<input type="text" name="dogName">
				</label>
			</td>
		</tr>
		<tr>
			<td>
				<label>飼い主ID<br>
				<a>${sessionScope.nameId}</a>
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
		<tbody id="remarksContainer">
			<tr class="inputField">
			
			  <th><input type="text" name="remarks1" placeholder="備考"></th>
			  <td><button type="button" class="deleteFieldBtn">入力欄を削除</button></td>
			  <!-- 追加ボタンは JS が自動で最後の行にだけ付ける -->
			</tr>
		</tbody>
		<tbody>
	      	<tr>
		        <td colspan="2">
		          <input type="submit" id="register" name="submit" value="登録">
		          <span id="error_message"></span>
		        </td>
	      	</tr>
      	</tbody>		
	</table>





</form>
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


function updateAddButtons() {
	  const fields = document.querySelectorAll("#remarksContainer .inputField");
	  fields.forEach((field, index) => {
	    const existingAddBtn = field.querySelector(".addFieldBtn");
	    if (existingAddBtn) existingAddBtn.remove();

	    if (index === fields.length - 1) {
	      // 最後の行に追加ボタンを追加
	      const addBtnTd = document.createElement("td");
	      addBtnTd.innerHTML = `<button type="button" class="addFieldBtn">入力欄を追加</button>`;
	      field.appendChild(addBtnTd);
	    }
	  });
	}

	document.forms[0].addEventListener("click", function (e) {
	  const remarksContainer = document.getElementById("remarksContainer");

	  if (e.target.classList.contains("addFieldBtn")) {
	    e.preventDefault();

	    const currentFields = remarksContainer.querySelectorAll(".inputField").length;

	    if (currentFields >= 5) {
	      alert("入力欄は最大5つまでです");
	      return;
	    }

	    const nextIndex = currentFields + 1;

	    const field = document.createElement("tr");
	    field.classList.add("inputField");
	    field.innerHTML = `
	      <th><input type="text" name="remarks${nextIndex}" placeholder="備考${nextIndex}"></th>
	      <td><button type="button" class="deleteFieldBtn">入力欄を削除</button></td>
	    `;
	    remarksContainer.appendChild(field);

	    updateAddButtons(); // 追加ボタンを最新の行に付け直す
	  }

	  else if (e.target.classList.contains("deleteFieldBtn")) {
	    e.preventDefault();

	    const remarksContainer = document.getElementById("remarksContainer");
	    const deleteFieldBtns = remarksContainer.querySelectorAll(".deleteFieldBtn");

	    if (deleteFieldBtns.length === 1) {
	      alert("入力欄は最低1つ必要です");
	      return;
	    }

	    const field = e.target.closest("tr");
	    const input = field.querySelector("input");

	    if (input.value.trim() !== "") {
	      alert("中身が入力されているため削除できません");
	      return;
	    }

	    field.remove();

	    // 名前とプレースホルダを振り直す
	    const fields = remarksContainer.querySelectorAll(".inputField");
	    fields.forEach((field, index) => {
	      const input = field.querySelector("input");
	      input.name = `remarks${index + 1}`;
	      input.placeholder = `備考${index + 1}`;
	    });

	    updateAddButtons(); // ボタン再配置
	  }
	});

	document.addEventListener("keypress", function (e) {
	  if (e.key === "Enter") {
	    e.preventDefault();
	  }
	});

	// 初期化時にボタン整える
	document.addEventListener("DOMContentLoaded", updateAddButtons);


	  // フォーム送信時に備考欄の name と value を表示
document.forms[0].addEventListener("submit", function (e) {
    e.preventDefault(); // ← ★ 送信を止める！

    const remarksInputs = document.querySelectorAll("input[name^='remarks']");

    console.log("=== 登録される備考欄 ===");
    remarksInputs.forEach(input => {
      console.log(`name: ${input.name}, value: ${input.value}`);
    });

    alert("備考欄の内容を確認しました（送信は行われません）");
  });
</script>
</body>
</html>
