<%@ page import="Domain.Wifi" %>
<%@ page import="java.util.List" %>
<%@ page import="DB.WifiTable" %>
<%@ page import="Service.HistoryService" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    WifiTable wifi = new WifiTable();
    String lat_value = request.getParameter("lat_value");
    String lnt_value = request.getParameter("lnt_value");
%>
<!DOCTYPE html>
<html>
<head>
    <title>와이파 정보 구하기</title>
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.7.min.js"></script>
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
            width :100%;
        }
        .wifi_info_table .header {
            width: 100%;
            font-size : 13px;
            border: 1px solid gray;
            background-color: #3fc83f;
            text-align: center;
            color: white;
        }
        tbody{
            text-align: center;
            font-size: 13px;
        }
    </style>
</head>
<body>
<div class = "container">
    <h1>와이파이 정보 구하기</h1>
    <nav class = "link menu">
        <a href ="/"> 홈</a> |
        <a href ="/history.jsp"> 위치 히스토리 목록</a>|
        <a href ="/load-wifi.jsp">Open API 와이파이 정보 요청 및 저장</a>
    </nav>
    <form class="location_form" action="./">
        <label for="lat_value">LAT:</label>

        <input type="text" id="lat_value" name="lat_value"
               class="input_form" <%if(lat_value !=null){%>
                                    value = <%=lat_value%>
                                            <%}else{%>
                                            value = 0.0
                                            <%}%>
        />

        <label for="lnt_value">LON:</label>

        <input type="text" id="lnt_value" name="lnt_value"
               class="input_form" <%if(lnt_value !=null){%>
                                    value = <%=lnt_value%>
                                            <%}else{%>
                                            value = 0.0
                                            <%}%>
        />
        <button type="button" id = "locationButton">내 위치 가져오기</button>
        <button type="submit">근처 WIFI 정보 가져오기</button>
    </form>
</div>
<table class = "wifi_info_table">
    <thread>
        <tr class = "header">
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
        <%

            if (lat_value == null && lnt_value == null) {

        %>
        <tr id="wait">
            <td colspan="17">위치 정보를 입력한 후에 조회해 주세요.</td>
        </tr>
        <%
        } else {
            List<Wifi> wifiList = wifi.NearByWifi(Double.parseDouble(lat_value), Double.parseDouble(lnt_value));
            HistoryService history = new HistoryService();
            history.register(Double.parseDouble(lat_value), Double.parseDouble(lnt_value));
            for (int i = 0; i < wifiList.size(); i++) {
                Wifi wifi_idx = wifiList.get(i);
        %>
        <tr id = "near-wifi-list">
            <td><%=String.format("%.4f",wifi_idx.getDist())%></td>
            <td><%=wifi_idx.getAdminNumber()%></td>
            <td><%=wifi_idx.getBorough()%></td>
            <td><%=wifi_idx.getWifiName()%></td>
            <td><%=wifi_idx.getLoadName()%></td>
            <td><%=wifi_idx.getDetailAddress()%></td>
            <td><%=wifi_idx.getInstallPosition()%></td>
            <td><%=wifi_idx.getInstallType()%></td>
            <td><%=wifi_idx.getInstallAgency()%></td>
            <td><%=wifi_idx.getServiceType()%></td>
            <td><%=wifi_idx.getNetType()%></td>
            <td><%=wifi_idx.getInstallYear()%></td>
            <td><%=wifi_idx.getInOutDoorType()%></td>
            <td><%=wifi_idx.getWifiConnEnv()%></td>
            <td><%=wifi_idx.getPosX()%></td>
            <td><%=wifi_idx.getPosY()%></td>
            <td><%=wifi_idx.getDateTime()%></td>
        </tr>
    <%
            }
        }
    %>
    </tbody>
</table>
<script type="text/javascript">
    class WifiServiceController{
        constructor() {
            this.location = document.querySelector(".location_form");
            this.locationButton = document.querySelector("#locationButton");
        }
        initWifiServiceFormController() {
            this.initLocationHistoryButton();

        }
        initLocationHistoryButton() {
            const locationButton = this.locationButton;
            locationButton.addEventListener("click", () => {
                if (navigator.geolocation) {
                    navigator.geolocation.getCurrentPosition(position => {
                        this.location["lnt_value"].value = position.coords.longitude;
                        this.location["lat_value"].value = position.coords.latitude;

                    })
                } else {
                    alert('위치 권한을 설정해 주세요.');
                }
            });
        }
    }
    document.addEventListener("DOMContentLoaded", () => {
        const wifiServiceController = new WifiServiceController();
        wifiServiceController.initWifiServiceFormController();
    });
    function self_value($lat, $lnt) {
        jQuery('#lat').val($lat);
        jQuery('#lnt').val($lnt);
    }

</script>
</body>

</html>