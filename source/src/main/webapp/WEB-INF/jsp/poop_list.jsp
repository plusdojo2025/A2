<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>

 <link rel="stylesheet" href="https://cdn.datatables.net/t/bs-3.3.6/jqc-1.12.0,dt-1.10.11/datatables.min.css"/>
 <link rel="stylesheet" href="<c:url value='/css/poop_list.css' />">
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

</head>
<body>

<%@ include file="header.jsp" %>
	<h1 class=page-title>うんち一覧</h1>
	
	<a href="<c:url value='/PoopServlet?action=poopregi' /> "class="regist-link">新規登録</a>
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
				<th>色</th>
				<th>硬さ</th>
				<th>登録日</th>
			</tr>	
		</thead>
		<tbody>
			<!-- うんち一覧 -->
			<c:forEach var="e" items="${poopList}" varStatus="status">
				<tr>
					<td><img src="<c:url value='${e.dogPhoto}' />"width="170" class="poop-photo" alt="${e.dogName}のうんち写真"></td>
					<td>${e.dogName}</td>
					<td>${e.name}</td>					
					
					<td>
					<c:if test="${e.color == 1}" ><div style="width:100px; height:100px; background-color:#444443; border-radius:50%;"></div></c:if>
					<c:if test="${e.color == 2}"><div style="width:100px; height:100px; background-color:#EBD469; border-radius:50%;"></div></c:if>
					<c:if test="${e.color == 3}"><div style="width:100px; height:100px; background-color:#AD795B; border-radius:50%;"></div></c:if>
					<c:if test="${e.color == 4}"><div style="width:100px; height:100px; background-color:#A44F30; border-radius:50%;"></div></c:if>
					<c:if test="${e.color == 5}"><div style="width:100px; height:100px; background-color:#A52A2A; border-radius:50%;"></div></c:if>
					</td>
					<td>
					<c:if test="${e.hardness == 1}"><p>硬</p></c:if>
					<c:if test="${e.hardness == 2}"><p>やや硬</p></c:if>
					<c:if test="${e.hardness == 3}"><p>普</p></c:if>
					<c:if test="${e.hardness == 4}"><p>やや柔</p></c:if>
					<c:if test="${e.hardness == 5}"><p>柔</p></c:if>
					</td>
					<td>${e.date}</td>
					<td>	<!-- 詳細に飛ぶボタン -->
					  <a href="<c:url value='/PoopServlet?id=${e.poopId}&action=pooplist' /> ">
					  <div class="button-wrapper">
					    <button type="button" class="detail-button">詳細</button>
					   </div>
					  </a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<a>${msg } </a>
</body>
</html>