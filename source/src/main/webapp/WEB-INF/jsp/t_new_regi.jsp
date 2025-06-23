<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新規登録</title>
</head>
<body>
<div>
<form id="tnewregi_form" method="post" action="<c:url value='/NewRegiServlet'/> "> <!-- サーブレットまだない -->
    <table>
      <tr>
        <td>
          <label>企業名<br>
          <input type="text" name="name">
          </label>
        </td>
      </tr>
      <tr>
        <td>
          <label>フリガナ<br>
          <input type="text" name="rudy">
          </label>
        </td>
        <td>
          <label>住所<br>
          <input type="text" name="address">
          </label>
        </td>
       </tr>
       <tr>
        <td>
          <label>ID(メールアドレス)<br>
          <input type="text" name="nameId">
          </label>
        </td>
        <td>
          <label>パスワード<br>
          <input type="text" name="pw">
          </label>
        </td>
      </tr>
      <tr>
        <td>
          <label>電話番号<br>
          <input type="text" name="uPhone">
          </label>
        </td>
        <td>
          <label>連絡先2<br>
          <input type="text" name="uPhone2">
          </label>
        </td>
      </tr>
      <tr>
        <td colspan="2">
          <input type="submit" id="register" name="submit" value="登録">
          <input type="reset" name="reset" value="リセット">
          <span id="error_message"></span>
        </td>
      </tr>
    </table>
  </form>
</div>

<script>
//HTML要素をオブジェクトとして取得
let formObj = document.getElementById('tnewregi_form');
let errorMessageObj = document.getElementById('error_message');
/* [実行]ボタンをクリックしたときの処理 */
formObj.onsubmit = function() {
  /* 企業名を必須入力項目とします */
  if (!formObj.name.value) {
    errorMessageObj.textContent = '※企業名を入力してください！';
    return false;
  }
  //フリガナを必須入力
  if (!formObj.ruby.value) {
	    errorMessageObj.textContent = '※フリガナを入力してください！';
	    return false;
	  }
  //住所を必須
  if (!formObj.address.value) {
	    errorMessageObj.textContent = '※住所を入力してください！';
	    return false;
	  }
  //ID必須
  if (!formObj.nameId.value) {
	    errorMessageObj.textContent = '※ID(メールアドレス)を入力してください！';
	    return false;
	  }
  //パスワード必須
  if (!formObj.pw.value) {
	    errorMessageObj.textContent = '※パスワードを入力してください！';
	    return false;
	  }
  //電話番号必須
  if (!formObj.uPhone.value) {
	    errorMessageObj.textContent = '※電話番号を入力してください！';
	    return false;
	  }
  /* 確認ダイアログボックスを表示します */
  if (!window.confirm('実行します。よろしいですか？')) {
    return false;
  }

  errorMessageObj.textContent = null;
};

/* [リセット]ボタンをクリックしたときの処理 */
formObj.onreset = function() {
  errorMessageObj.textContent = null;
};
</script>
</body>
</html>