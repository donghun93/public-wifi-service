<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
            crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.js"
            integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
    <title>Document</title>
</head>
<body>
<div class="m-3">
    <div>
        <div class="shadow rounded p-3 mb-3 bg-dark text-white fs-2 fw-bold">와이파이 정보 구하기</div>
        <a class="btn btn-outline-primary" href="/" role="button">홈</a>
        <a class="btn btn-outline-primary" href="/wifi.history.do" role="button">위치 히스토리 목록</a>
        <a id="load-wifi-btn" class="btn btn-outline-primary" href="/wifi.load.do" role="button">Open API 와이파이 정보 가져오기</a>
    </div>
    <div class="mt-3">
        <form class="row g-4" action="/wifi.near.do" method="get">
            <div class="col-auto">
                <label for="latitude" class="visually-hidden"></label>
                <input id="latitude" name="lat" type="number" step="any" class="form-control" placeholder="LAT">
            </div>
            <div class="col-auto">
                <label for="longitude" class="visually-hidden"></label>
                <input id="longitude" name="lnt" type="number" step="any" class="form-control" placeholder="LNT">
            </div>
            <div class="col-auto">
                <button type="button" class="btn btn-primary mb-3" onclick="getLocation()">내 위치 가져오기</button>
            </div>
            <div class="col-auto">
                <button type="submit" class="btn btn-success mb-3">근처 WIFI 정보 보기</button>
            </div>
        </form>
    </div>
    <div class="table-responsive mt-1">
        <table class="table table-bordered table-hover">
            <thead style="vertical-align: middle; height: 50px">
            <tr>
                <th>거리(Km)</th>
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
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${nearWifiList}" var="wifi">
                <tr>
                    <td><c:out value="${wifi.km}"/></td>
                    <td><c:out value="${wifi.mgrNo}"/></td>
                    <td><c:out value="${wifi.wrdoFc}"/></td>
                    <td><c:out value="${wifi.mainName}"/></td>
                    <td><c:out value="${wifi.address1}"/></td>
                    <td><c:out value="${wifi.address2}"/></td>
                    <td><c:out value="${wifi.instlFloor}"/></td>
                    <td><c:out value="${wifi.instlTy}"/></td>
                    <td><c:out value="${wifi.instlMby}"/></td>
                    <td><c:out value="${wifi.svcSE}"/></td>
                    <td><c:out value="${wifi.cmcwr}"/></td>
                    <td><c:out value="${wifi.cnstcYear}"/></td>
                    <td><c:out value="${wifi.inoutDoor}"/></td>
                    <td><c:out value="${wifi.remars3}"/></td>
                    <td><c:out value="${wifi.lat}"/></td>
                    <td><c:out value="${wifi.lnt}"/></td>
                    <td><c:out value="${wifi.workDateTime}"/></td>
                </tr>

            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<script>
    function getLocation() {
        // if (navigator.geolocation) {
        //     navigator.geolocation.getCurrentPosition(
        //         function (pos) {
        //             console.log(pos);
        //             $('#latitude').val(pos.coords.latitude);     // 위도, y
        //             $('#longitude').val(pos.coords.longitude);   // 경도, x
        //         });
        // } else {
        //     alert("이 브라우저에서는 Geolocation이 지원되지 않습니다.")
        // }
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(function (pos) {
                var latitude = pos.coords.latitude;
                var longitude = pos.coords.longitude;

                document.getElementById('latitude').value = latitude;
                document.getElementById('longitude').value = longitude;
            });
        } else {
            alert("이 브라우저에서는 Geolocation이 지원되지 않습니다.")
        }
    };
</script>
</body>
</html>