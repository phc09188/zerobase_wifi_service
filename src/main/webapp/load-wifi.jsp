<%--
  Created by IntelliJ IDEA.
  User: chandle
  Date: 2022/08/22
  Time: 12:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>와이파이로드뷰</title>
</head>
<body>
<h2 id = "wifi-load-result"></h2>
<a href="/">홈으로 가기</a>
<script type="text/javascript">
    function submitXMLHttpRequestLoadWifiInfo() {
        const xhr = new XMLHttpRequest();
        xhr.open("GET", "/openapi.seoul.go.kr:8088/sample/xml/TbPublicWifiInfo/1/5/", true);
        xhr.addEventListener("loadend", (evt) => {
            const resultTag = document.querySelector("#wifi-load-result");
            const responseJSON = JSON.parse(evt.target.responseText);
            const reqCount = responseJSON["response"];
            if (reqCount < 0) {
                resultTag.textContent = "문제가 발생하여 저장에 실패하였습니다.";
            } else {
                resultTag.textContent = reqCount + "개의 wifi 정보를 정상적으로 저장하였습니다."
            }
        });
        xhr.send();
    }
    document.addEventListener("DOMContentLoaded", () => {
        submitXMLHttpRequestLoadWifiInfo();
    });
</script>
</body>
</html>
