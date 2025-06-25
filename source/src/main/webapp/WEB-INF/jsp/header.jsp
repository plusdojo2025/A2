<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <!--　box表示    
    <style>
        *{
            outline: 1px solid #FF0000
        }
    </style>
-->

<style>

header {
  display: flex;
  flex-wrap: wrap;
  align-items: center;
  border-bottom: 2px solid orange;
  padding: 10px 20px;
  color: orange;
  font-weight: bold;
  font-size: 20px;
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
}

.header-left {
  display: flex;
  align-items: center;
  flex-grow: 1;
}

.logo {
  height: 120px;
  margin-right: 10px;
}


.header-right {
  display: flex;
  flex-direction: column;  /* 上下に並べる */
  align-items: flex-end;   /* 右寄せ */
  gap: 10px;               /* 上下の隙間 */
}
.user-info {
  display: flex;
  align-items: flex-end;
}

.logout-btn {
  background: none;
  border: 1px solid orange;
  color: orange;
  padding: 4px 8px;
  margin-left: 20px;
  margin-right: 40px;
  border-radius: 4px;
  cursor: pointer;
  font-size: 20px;
}

.main-nav {
  display: flex;
  gap: 120px;
  list-style: none;
  margin-top: 20px;
  padding-left: 0;
  padding-right: 50px;
  margin-bottom: 0px;
  width: 100%;
  font-size: 40px;
}

.main-nav li {
  display: flex;
  align-items: center;
  cursor: pointer;
}

.icon {
  height: 40px;
  margin-right: 6px;
}

</style>


</head>
<body>
	<!-- ヘッダーここから -->
<header>
  <div class="header-left">
    <a href="<c:url value='HomeServlet'/> "><img src="<c:url value='/images/ぶりログ.png' />"alt="ホームに戻る" class="logo"></a>
  </div>

  <div class="header-right">
    <div class="user-info">
      <span class="welcome">ようこそ<c:out value="${sessionScope.user.name}"/>さん</span>
      <a href="<c:url value='LoginServlet'/>" class="logout-btn">ログアウト</a>
    </div>

    <nav>
      <ul class="main-nav">
        <li><a href="<c:url value='WankoServlet?action=home'/> "><img src="<c:url value='/images/犬のフリーアイコン.png' />" alt="" class="icon">一覧</a></li>
        <li><a href="<c:url value='PoopServlet'/> "><img src="<c:url value='/images/うんち.png' />" alt="" class="icon">記録</a></li>
        <li><a href="<c:url value='ReportServlet'/> ">報告</a></li>
        <li><a href="<c:url value='ContactServlet?action=home'/> ">問い合わせ</a></li>
      </ul>
    </nav>
  </div>
</header>
	<!-- ヘッダーここまで -->
</body>
</html>