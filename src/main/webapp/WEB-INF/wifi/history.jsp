<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <title>Title</title>
</head>
<body>
<div class="m-3">
    <div>
        <div class="shadow rounded p-3 mb-3 bg-dark text-white fs-2 fw-bold">위치 히스토리 목록</div>
        <a class="btn btn-outline-primary" href="/" role="button">홈</a>
        <a class="btn btn-outline-primary" style="display:none" href="/wifi.history.do" role="button">위치 히스토리 목록</a>
        <a class="btn btn-outline-primary" style="display:none" href="/wifi.load.do" role="button">Open API 와이파이 정보
            가져오기</a>
    </div>
    <div class="table-responsive mt-3">
        <table class="table table-bordered table-hover">
            <thead>
            <tr style="vertical-align: middle; height: 50px;">
                <th>ID</th>
                <th>X좌표</th>
                <th>Y좌표</th>
                <th>조회일자</th>
                <th style="vertical-align: middle; text-align: center;
                width: 120px;">비고</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${historyList}" var="history">
                <tr>
                    <td><c:out value="${history.id}"/></td>
                    <td><c:out value="${history.xlocation}"/></td>
                    <td><c:out value="${history.ylocation}"/></td>
                    <td><c:out value="${history.searchDate}"/></td>
                    <td style="text-align: center;">
                        <button id="delete-btn" type="button" class="btn btn-danger">삭제</button>
                    </td>
                </tr>

            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<script>
    // 버튼 클릭시 Row 값 가져오기
    $(".btn-danger").click(function () {
        var checkBtn = $(this);
        var tr = checkBtn.parent().parent();
        var td = tr.children();

        var no = td.eq(0).text();
        init(no);
    });

    function init(no) {
        $.ajax({
            type: "POST",
            url: "http://localhost:8080/wifi.history.delete.do?deleteId=" + no
        }).done(function() {
            location.reload();
        });
    }
</script>
</body>
</html>
