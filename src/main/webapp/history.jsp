<%@ page import="Service.HistoryService" %>
<%@ page import="Domain.History" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    HistoryService history = new HistoryService();
    List<History> historyList = history.Historylist();
%>
<html>
<head>
    <title>히스토리 저장소</title>
    <style type = "text/css">
        html,
        body {
            margin: 0;
            padding: 0;
            min-width: 1080px;
            min-height: 100%;
            font-family: '나눔 고딕', NanumGothic, '돋움', Dotum, sans-serif;
        }
        .link-menu {
            margin: 0;
            padding: 0;
            list-style: none;
        }
        a {
            text-decoration: none;
            color: #000;
        }
        .wifi-history-info-table{
            width :100%;
        }
        .wifi-history-info-table .header {
            width: 100%;
            border: 1px solid gray;
            background-color: #3fc83f;
            text-align: center;
            color: white;
        }
        tbody{
            text-align: center;
            font-size: 10px;
        }
        tr:nth-child(even){
            background-color: #f2f2f2;
        }
        tr, td{
            border: 1px solid #ddd;
        }
        td{
            font-weight: 600;
        }
    </style>
</head>
<body>
<h1>위치 히스토리 목록</h1>
<nav class = "link-menu">
    <a href ="/"> 홈</a> |
    <a href ="/history.jsp"> 위치 히스토리 목록</a>|
    <a href ="/load-wifi.jsp">Open API 와이파이 정보 요청 및 저장</a>
</nav>
<table class = "wifi-history-info-table">
    <thread>
        <tr class = "header">
            <th>ID</th>
            <th>X좌표</th>
            <th>Y좌표</th>
            <th>조회일자</th>
            <th>비고</th>
    </thread>
    <tbody>
        <%
            for (int i = 0; i < historyList.size(); i++) {
                History his = historyList.get(i);
        %>
        <tr>
            <td><%=his.getID()%></td>
            <td><%=his.getPosx()%></td>
            <td><%=his.getPosy()%></td>
            <td><%=his.getCurDate()%></td>
            <td>
                <a href="/HistoryDeleteServlet?id=<%=his.getID()%>">삭제</a>
            </td>
        </tr>
        <%
            }
        %>
    </tbody>
</table>
</body>
</html>
