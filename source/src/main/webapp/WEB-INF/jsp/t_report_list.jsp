<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>

 <link rel="stylesheet" href="https://cdn.datatables.net/t/bs-3.3.6/jqc-1.12.0,dt-1.10.11/datatables.min.css"/>
 <link rel="stylesheet"  href="<c:url value='/css/etc.css' />">
 <script src="<c:url value='https://cdn.datatables.net/t/bs-3.3.6/jqc-1.12.0,dt-1.10.11/datatables.min.js' /> "></script>
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
<title>ワンコ一覧</title>

</head>
<body>

<%@ include file="header.jsp" %>
	<h1 class=page-title>レポート一覧</h1>
	<a href="<c:url value='/ReportServlet?action=trepoprtregi' />">新規登録</a>
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
				<th>登録日</th>
		</thead>
		<tbody>
			<!-- ワンコ一覧 -->
			<c:forEach var="e" items="${reportList}" varStatus="status">
				<tr>
					<td><img src="<c:url value='${e.dogPhoto}' />"width="170"  alt="${e.dogName}の写真"></td>
					
					<td>${e.dogName}</td>
					<td>${e.name}</td>
					<td>${e.reportDate}</td>
					<td>	<!-- 詳細に飛ぶボタン -->
					  <a href="<c:url value='ReportServlet?reportId=${e.reportId}&action=reportDetail'/>">
					    <button type="button">詳細</button>
					  </a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>