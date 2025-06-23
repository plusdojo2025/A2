<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
 <link rel="stylesheet" href="https://cdn.datatables.net/t/bs-3.3.6/jqc-1.12.0,dt-1.10.11/datatables.min.css"/>
 <script src="https://cdn.datatables.net/t/bs-3.3.6/jqc-1.12.0,dt-1.10.11/datatables.min.js"></script>
 <script>
    jQuery(function($){
    	 // デフォルトの設定を変更（日本語化）--------------------
        $.extend( $.fn.dataTable.defaults, {
            language: {
                url: "http://cdn.datatables.net/plug-ins/9dcbecd42ad/i18n/Japanese.json"
            }
        });
    	 //------------------------------------------------
    	//データテーブルを使用
        $("#foo-table").DataTable();
    });
 </script>
<meta charset="UTF-8">
<title>うんち一覧</title>

<style>
/* 戻る画像ボタンのCSS */
	.back_button {
		width: 40px;
	}
	
	.back_text {
		font-size: 10px;
	}	
	
	/* ○ページ目 */
	.page_count {
		text-align: center;
	}
	/*「○○一覧」のCSS*/
	.page-title {
		color:#FFA500;
/* 		font-family: "Meiryo"; */
	}
	/*「新規登録ボタン」のCSS*/
	.regist-link {
		
	}
 </style>
</head>
<body>
	<h1 class=page-title>うんち一覧</h1>
	
	<a href="<c:url value='/PoopServlet?action=poopregi' />">新規登録</a>
	<!-- 戻る画像ボタン -->
	<div>
		<span class="back_text">前に戻る</span>	<br>												<!-- cssでmargin0にする？ -->
		<a href="javascript:history.back();">
 			<img src="<c:url value='/images/back.png' />" alt="戻る" class="back_button">
		</a>	
	</div>
	
	<table border="1" id="foo-table" class="table table-bordered">
		<thead>
			<tr>
				<th>お写真</th>
				<th>ワンコのお名前</th>
				<th>飼い主様</th>
				<th>硬さ</th>
				<th>色</th>
				<th>登録日</th>
		</thead>
		<tbody>
			<!-- うんち一覧 -->
			<c:forEach var="e" items="" varStatus="status">
				<tr>
					<td><img src="${e.dogphoto}" alt="${e.dogName}の写真"></td>
					<td>${e.dogName}</td>
					<td>${e.name}</td>
					<td>${e.hardness}</td>
					<td>${e.color}</td>
					<td>${e.date}</td>
					<td>	<!-- 詳細に飛ぶボタン -->
					  <a href="<c:url value='/PoopServlet?id=${e.poopId}' /> ">
					    <button type="button">詳細</button>
					  </a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>