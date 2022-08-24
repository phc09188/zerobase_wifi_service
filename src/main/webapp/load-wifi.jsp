<%@ page import="Service.WifiService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    WifiService wifiService = new WifiService();
%>
<html>
<head>
    <title>와이파이로드</title>
</head>
<body>
<p>
    <%=wifiService.WifiMain()%>
    개의 정보를 저장하였습니다.
</p>
<a href="/">홈으로 가기</a>

</body>
</html>
