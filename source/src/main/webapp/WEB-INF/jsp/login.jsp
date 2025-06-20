<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン</title>
<!-- <link rel="stylesheet" type="text/css" href="/webapp/css/style.css"> -->
</head>
<body>

  <!-- ヘッダー（ここから） -->
  <h1 id="logo">
    <a href="/webapp/LoginServlet"><img src="/webapp/img/ぶりログ.png" width="250" height="200" alt="ぶりログ">
    </a>
  </h1>
  <!-- ヘッダー（ここまで） -->
  
   <form  method="POST" action="<c:url value='/LoginServlet'/>" >
    ID<input type="text" name="id"><br>
    パスワード<input type="password" name="pw"><br>
    <input type="submit" name="logbut" value="ログイン">
    <input type="reset" name="reset" value="リセット">
    </form>
    <div>
    ${errMsg}
    </div>
    <!--  <table>
      <tr>
        <td>
          <label>ID<br>
          <input type="text" name="id">
          </label>
        </td>
      </tr>
      <tr>
        <td>
          <label>パスワード<br>
          <input type="password" name="pw">
          </label>
        </td>
         <tr>
        <td colspan="2">
          <input type="submit" name="submit" value="ログイン">
          <input type="reset" name="reset" value="リセット">
          <span id="error_message"></span>
        <td>
      </tr>
    </table>
  </form>-->
  <div id="footer">
  <p><a href="LoginServlet?action=regi">新規登録はこちら</a></p>
  <p>ID/パスワードを忘れた方は〇〇までご連絡ください</p>
  </div>
  <script>
/* HTML要素をオブジェクトとして取得する */
let formObj = document.getElementById('login_form');
let errorMessageObj = document.getElementById('error_message');

/* [ログイン]ボタンをクリックしたときの処理 */
formObj.onsubmit = function() {
  if (!formObj.id.value || !formObj.pw.value) {
    errorMessageObj.textContent = '※IDまたはパスワードが間違っています';
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