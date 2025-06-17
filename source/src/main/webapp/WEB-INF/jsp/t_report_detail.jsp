<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>報告詳細（企業用）</title>
</head>
<body>
	<h1>
		<div style="display: flex; justify-content: space-between;">
  			<div style="text-align: left;">報告詳細</div>
        	<div style="text-align: right;">
        		<a href="javascript:history.back();">
				<span>ひとつ前に戻る</span>							<!-- cssでmargin0にする？ -->
				<img src="/webapp/img/back.png" alt="戻る">		<!-- 戻る画像ボタン -->
		    	</a>
        	</div>
		</div>
	</h1>
	<ul>
		<select name="dogName">
			<!-- ワンコ名をプルダウンで選択できるようにする -->
			<option value="" selected>ワンコ選択</option>
		</select>
		<li>ごはん<input type="submit" name="food" value="${e.food}"></li>
		<li>日付<input type="date" name="reportDate"></li>
		<li>散歩walk</li>
		<li>様子<input type="submit" name="state" value="異常なし"> <input type="submit" name="state" value="異常あり"></li>
		<li>トレーニング<input type="text" name="training"></li>
		<li>メモ<input type="text" name="reportMemo"></li>
		<input type="submit" value="更新"> <input type="submit" value="削除">
		
		<!-- 更新削除の確認ダイアログボックス追加 -->
		

	</ul>
	<!-- メインここまで -->
	<!--  フッターここから -->
	<footer>
	</footer>
	<!--  フッターここまで -->
</body>
</html>