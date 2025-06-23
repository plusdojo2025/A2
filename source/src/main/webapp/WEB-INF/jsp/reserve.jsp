<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>予約希望</h1>
<form method="post" action="<c:url value='/WankoServlet' />">
	<table>
		<tr>
			<td>
				<label>わんこのお名前<br>
				<input type="text" name="dogPhoto">
				</label>
			</td>
		</tr>
		<tr>
			<td>
				<label>飼い主様のお名前<br>
				<input type="text" name="dogPhoto">
				</label>
			</td>
		</tr>
		<tr>
			<td>
				<label>ログインID<br>
				<input type="text" name="dogPhoto">
				</label>
			</td>
		</tr>
		<tr>
			<td>
				<label>電話番号<br>
				<input type="text" name="dogPhoto">
				</label>
			</td>
		</tr>
		<tr>
			<td>
				<label>予約希望日<br>
				<input type="date" name="dogPhoto">
				</label>
			</td>
		</tr>
		<tr>
			<td>
				<label>備考<br>
				<input type="text" name="">
				</label>
			</td>
		</tr>
		<tr>
	        <td colspan="2">
	          <input type="submit" id="register" name="submit" value="送信">
	          <span id="error_message"></span>
	        </td>
      	</tr>
	</table>
</form>
</body>
</html>