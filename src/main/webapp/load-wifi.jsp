<%@ page import="Service.WifiService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    WifiService wifiService = new WifiService();
%>
<html>
<head>
    <title>와이파이로드</title>
    <style type = "text/css">
        .result{
            font-size: 200%;
            text-align: center;
        }
        a{
            font-size: 100%;
            text-align: center;
            text-decoration: none;
        }
    </style>
</head>
<body>
<p class = "result">
    <%=wifiService.WifiMain()%>
    개의 정보를 저장하였습니다.
</p>
<a href="/">홈으로 가기</a>

</body>
</html>
