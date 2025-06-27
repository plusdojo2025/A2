
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>トレーナー用ホーム画面</title>
<link rel="stylesheet"  href="<c:url value='/css/t_home.css' />">
</head>

<body>
<%@ include file="header.jsp" %>
<h1>カレンダー</h1>
   <div class="wrapper">
	    <!-- xxxx年xx月を表示 -->
	    

<!-- 	    ボタンクリックで月移動-->
	   <div id="header-area">
        <button id="prev" onclick="prev()">‹</button>
        <h1 id="header"></h1>
        <button id="next" onclick="next()">›</button>
    </div>


	    <!-- カレンダー -->
	    <div id="calendar"></div>
	</div>
<h1>今日のわんこ</h1>
<c:forEach var="e" items="${todaysDog}">
	<div>
		<ul>
			<li>${e.dogName}</li>
			<li><img src="${e.dogPhoto}" width="170" alt="${e.dogName}の写真"></li>
			<li><a href="<c:url value='PoopServlet?wankoDogId=${e.wankoDogId}&action=tpoopregi'/> ">うんち記録</a></li>
			<li><a href="<c:url value='ReportServlet?wankoDogId=${e.wankoDogId}&dogName=${e.dogName}&action=trepoprtregi'/> ">報告</a>></li>
			
		</ul>
	</div>
</c:forEach>

  

<script>
const week = ["日", "月", "火", "水", "木", "金", "土"];
const today = new Date();
var showDate = new Date(today.getFullYear(), today.getMonth(), 1);
window.onload = function () {
    showProcess(today);
};
function prev(){
    showDate.setMonth(showDate.getMonth() - 1);
    showProcess(showDate);
}
function next(){
    showDate.setMonth(showDate.getMonth() + 1);
    showProcess(showDate);
}
function showProcess(date) {
    var year = date.getFullYear();
    var month = date.getMonth();
    document.querySelector('#header').innerHTML = year + "年 " + (month + 1) + "月";
    var calendar = createProcess(year, month);
    document.querySelector('#calendar').innerHTML = calendar;
}
function createProcess(year, month) {
	const baseUrl = "${pageContext.request.contextPath}";
    var calendar = "<table><tr class='dayOfWeek'>";
    for (var i = 0; i < week.length; i++) {
        calendar += "<th>" + week[i] + "</th>";
    }
    calendar += "</tr>";
    var count = 0;
    var startDayOfWeek = new Date(year, month, 1).getDay();
    var endDate = new Date(year, month + 1, 0).getDate();
    var lastMonthEndDate = new Date(year, month, 0).getDate();
    var row = Math.ceil((startDayOfWeek + endDate) / week.length);
    let sMonth = String(month + 1).padStart(2, "0");
	const scheduleList = ${scheduleListJson};
	const dtoList = JSON.parse(JSON.stringify(scheduleList));
    for (var i = 0; i < row; i++) {
        calendar += "<tr>";
        for (var j = 0; j < week.length; j++) {
            if (i == 0 && j < startDayOfWeek) {
                calendar += "<td class='disabled'>" + (lastMonthEndDate - startDayOfWeek + j + 1) + "</td>";
            } else if (count >= endDate) {
                count++;
                calendar += "<td class='disabled'>" + (count - endDate) + "</td>";
            } else {
                count++;
                // <td>のclassだけ判定して付ける
                let tdClass = "";
                if (year == today.getFullYear() && month == today.getMonth() && count == today.getDate()) {
                    tdClass = " class='today'";
                }
                calendar += "<td" + tdClass + ">" + count + "<br>"
                    + "<a href='" + baseUrl + "/CalendarServlet?year=" + year
                    + "&month=" + (month + 1)
                    + "&count=" + count + "'>";
                let sCount = String(count).padStart(2, "0");
                if (!sCount || !sMonth) {
                    calendar += "登録";
                } else {
                    let found = false;
                    for (let i = 0; i < dtoList.length; i++) {
                        if (dtoList[i].calendarDate === (year + "-" + sMonth + "-" + sCount)) {
                            calendar += "★";
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        calendar += "登録";
                    }
                }
                calendar += "</a></td>";
            }
        }
        calendar += "</tr>";
    }
    return calendar;
}
</script>
<%@ include file="footer.jsp" %>
</body>
</html>