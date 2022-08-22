<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <style type="text/css">
        html,
        body {
            margin: 0;
            padding: 0;
            min-width: 1080px;
            min-height: 100%;
            font-family: '나눔 고딕', NanumGothic, '돋움', Dotum, sans-serif;
        }
        ul,
        ol,
        li {
            margin: 0;
            padding: 0;
            list-style: none;
        }
        a {
            text-decoration: none;
            color: #000;
        }
        .menu {
            width: fit-content;
            padding: 10px;
            border: 1px solid rgba(94, 84, 84, 0.28);
            margin-bottom: 20px;
        }
        .menu ul li {
            margin-top: 5px;
        }
        .location_form {
            width: fit-content;
            padding: 10px;
            border: 1px solid rgba(94, 84, 84, 0.28);
            margin-bottom: 10px;
        }
        .wifi_info_table {
            width: 100%;
            border: 1px solid gray;
            border-collapse: collapse;
            background-color: #3fc83f;
            text-align: center;
            color: white;
        }
        .wifi_info_table td {
            padding: 0 30px 0 30px;
            min-width: 100px;
            border: 0.1px solid #29e595;
        }
    </style>
</head>
<body>
<div class = "container">
    <h1>와이파이 정보 구하기</h1>
    <nav class = "link menu">
        <a href ="/"> 홈</a> |
        <a href ="history.jsp"> 위치 히스토리 목록</a>|
        <a href ="/load_wifi.jsp">Open API 와이파이 정보 요청 및 저장</a>
    </nav>
    <form class="location_form" action="/location" method="POST">
        <label for="y-pos">LAT:</label>

        <input type="number" id="y-pos" name="y-pos"
               class="input_form" placeholder="0.0"/>

        <label for="x-pos">LON:</label>

        <input type="number" id="x-pos" name="x-pos"
               class="input_form" placeholder="0.0"/>

        <button type="button" id="locationButton">내 위치 가져오기</button>
        <button type="button" id="wifiButton">근처 WIFI 정보 가져오기</button>
    </form>
</div>
<table class = "wifi_info_table">
    <thread>
        <tr>
            <th>거리(km)</th>
            <th>관리번호</th>
            <th>자치구</th>
            <th>와이파이명</th>
            <th>도로명주소</th>
            <th>상세주소</th>
            <th>설치위치(층)</th>
            <th>설치유형</th>
            <th>설치기관</th>
            <th>서비스구분</th>
            <th>망종류</th>
            <th>설치년도</th>
            <th>실내외구분</th>
            <th>WIFI접속환경</th>
            <th>X좌표</th>
            <th>Y좌표</th>
            <th>작업일자</th>
    </thread>
    <tbody>

    </tbody>
</table>

</body>

</html>