<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>うんち詳細</title>
<style>
  .color-option {
    display: flex;
    gap: 20px;
    margin: 20px 0;
  }

  .color-option input[type="radio"] {
    display: none;
  }

  .color-label {
    width: 30px;
    height: 30px;
    border-radius: 50%;
    display: inline-block;
    cursor: pointer;
    border: 3px solid transparent;
  }

  /* 色ごとの背景 */
  .color-black { background-color: #444443; }
  .color-yellow { background-color: #EBD469; }
  .color-brown { background-color: #AD795B; }
  .color-brownred { background-color: #A44F30; }
  .color-red { background-color: #A52A2A; }

  /* 選択されたときの枠線 */
  input[type="radio"]:checked + .color-label {
    border-color: #FFA500;
  }
</style>
</head>
<body>
	<!-- 登録者の名前も後で表示させる -->
	<h1>
		<div style="display: flex; justify-content: space-between;">
  			<div style="text-align: left;">うんち詳細　（名前）さん</div>
        	<div style="text-align: right;">
        		<a href="javascript:history.back();">
				<span>ひとつ前に戻る</span>							<!-- cssでmargin0にする？ -->
				<img src="/webapp/img/back.png" alt="戻る">		<!-- 戻る画像ボタン -->
		    	</a>
        	</div>
		</div>
	</h1>
	
	<c:forEach var="e" items="" >
	<form method="POST" action="">
		時間<input type="time" name="nowTime" value="${e.nowTime}"><br>
		日付<input type="date" name="date" value="${e.date}"><br>
		写真追加<input type="file" name="photo" value="${e.photo}"><br>
		ワンコ選択<input type="text" name="name" value="${e.name}"><br>
		色<input type="radio" name="color" value="${e.color}">
		硬さ<input type="radio" name="hardness" value="${e.hardness}"><br>
		異常<input type="submit" name="abnormal" value="${e.abnormal}"><br>
		メモ<input type="text" name="memo" value="${e.memo}"><br>
		<input type="submit" value="更新">　<input type="submit" value="削除"><br>
	</form>
	</c:forEach>
	
	<!--  フッターここから -->
	<footer>
	</footer>
	<!--  フッターここまで -->
</body>
</html>