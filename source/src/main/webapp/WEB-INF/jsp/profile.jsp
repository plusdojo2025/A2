<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


<div>
<form id="newregi_form" method="post" action="<c:url value='/NewRegiServlet'/>"> <!-- サーブレットまだない -->
    <table>
      <tr>
        <td>
          <label>お名前<br>
          <input type="text" name="name" placeholder="必須" value="${name}" required>
          </label>
        </td>
        <td>
          <label>フリガナ<br>
          <input type="text" name="rudy" placeholder="必須" value="${ruby}" required>
          </label>
        </td>
        <td>
          <label>生年月日<br> <!-- 企業側とユーザー側で変わる？ -->
          <input type="date" name="birth" placeholder="必須" value="${birth}" required>
          </label>
        </td>

        <td>
          <label>ID(メールアドレス)<br>
          <input type="text" name="nameId" placeholder="必須" value="${userNameId}" required>
          </label>
        </td>
        </tr>
        <tr>
        <td>
          <label>パスワード<br>
          <input type="text" name="pw" placeholder="必須" value="${pw}" required>
          </label>
        </td>
        <td>
          <label>電話番号<br>
          <input type="text" name="uPhone" placeholder="必須" value="${uPhone}" required>
          </label>
        </td>
        <td>
          <label>連絡先2<br>
          <input type="text" name="uPhone2" value="${uPhone2}">
          </label>
        </td>
        <td>
        	<label>スクールID<br>
        	<input type="text" name="schoolId" placeholder="必須" value="${userSchoolId}" required>
        	</label>
        </td>
      </tr>
      <tr>
        <td colspan="2">
		<input type="submit" name="submit" value="更新">
		<input type="submit" name="submit" value="削除"><br>
          <span id="error_message"></span>
        </td>
      </tr>
    </table>
  </form>
</div>
</body>
</html>