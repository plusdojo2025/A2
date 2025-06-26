<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="<c:url value="https://cdn.datatables.net/t/bs-3.3.6/jqc-1.12.0,dt-1.10.11/datatables.min.css" />"/>
<script src="<c:url value="https://cdn.datatables.net/t/bs-3.3.6/jqc-1.12.0,dt-1.10.11/datatables.min.js" />"></script>
<link rel="stylesheet" href="https://cdn.datatables.net/t/bs-3.3.6/jqc-1.12.0,dt-1.10.11/datatables.min.css"/>
<link rel="stylesheet" href="<c:url value='/css/o_report_list.css' />">
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
        $("#re-table").DataTable();
    });
</script>
<meta charset="UTF-8">
<title>報告一覧飼い主用</title>

</head>
<body>
<%@ include file="header.jsp" %>
<h1 class="page-title">報告一覧</h1>
<div style="display: flex; justify-content: space-between; align-items: center;">
  <div style="text-align: left;"></div>
  <div class="target" style="text-align: right;">
    <a href="javascript:history.back();" style="display: flex; flex-direction: column;">
      <span style="margin: 0;">前に戻る</span>
      <img src="/A2/images/back.png" alt="戻る" style="height: 60px; width: auto;" />
    </a>
  </div>
</div>
	
	<table border="1" id="re-table" class="table table-bordered">
		<thead>
			<tr>
				<th>お写真</th>
				<th>ワンコのお名前</th>
				<th>飼い主様</th>
				<th>登録日</th>
		</thead>
		<stbody>
			<!-- 報告一覧 -->
			<c:forEach var="e" items="${reportList}" varStatus="status">
				<tr>

					<td><img src="<c:url value='${e.dogPhoto}' />" width="170" alt="${e.dogName}の写真"></td>
					<td>${e.dogName}</td>
					<td>${e.name}</td>
					<td>${e.reportDate}</td>
					<td>	<!-- 詳細に飛ぶボタン -->
					  <a href="<c:url value='/ReportServlet?id=${e.reportDogId}&action=reportDetail' />">
    					<button type="button">詳細</button>
						</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<a>${msg } </a>
</body>
</html>