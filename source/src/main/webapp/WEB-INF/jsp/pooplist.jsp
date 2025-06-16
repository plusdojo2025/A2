<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- ↓↓横並びにする！ -->
	<div>
		<h1>うんち一覧</h1>
		<a href="poop.jsp">新規登録</a>		<!--  -->
		<text=		<!-- 全項目から検索するから -->
	</div>
	<!-- 拡張For文と思っていい -->
		<div>
			<form method="POST" action="/webapp/PoopServlet">
				<input type="hidden"  name="number" value="2">
				会社名<input type="text" name="company" value="特務機関NERV" class="company"><br>
				部署名<input type="text" name="department" value="技術開発部技術局第一課所属"><br>
				役職名<input type="text" name="position" value="開発総責任者"><br>
				氏名　　<input type="text" name="name" value="赤木リツコ" ><br>
				ふりがな<input type="text" name="ruby" value="あかぎりつこ"><br>
				郵便番号<input type="text" name="zipcode" value="248-8588"><br>
				会社住所<input type="text" name="address" value="神奈川県鎌倉市雪ノ下2-1-31"><br>
				電話番号<input type="text" name="phone" value="0466-29-9960"><br>
				FAX　　<input type="text" name="fax" value=""><br>
				Email　<input type="text" name="email" value="akagirituko@gmail.com"><br>
				
				会社URL<input type="text" name="url" value="https://www.kotoku-in.jp/" ><br>
				<a href="https://www.kotoku-in.jp/" target="_blank">▶ 開く</a><br><br>
				備考<input type="text" name="remarks" value=""><br>
				<br><br>
				<input type="submit" name="submit" onclick="return confirm('更新内容を確認して[OK]を押してください。');" value="更新">
				<input type="submit" name="submit" onclick="return confirm('本当に削除しますか？');" value="削除">
					 <input type="reset" name="reset" value="リセット">
				 <span id="error_message"></span>
			 <br><br>
				
			</form>
		</div>

</body>
</html>