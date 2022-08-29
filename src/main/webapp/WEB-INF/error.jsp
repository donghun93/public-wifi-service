<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="css/style.css">
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <title>Title</title>
</head>
<body>
<div>
        <div class="page-404">
            <div class="outer">
                <div class="middle">
                    <div class="inner">
                        <!--BEGIN CONTENT-->
                        <div class="inner-circle"><i class="fa fa-home"></i><span><c:out value="${errorCode}"/></span></div>
                        <span class="inner-status">Oops!</span>
                        <span class="inner-detail">
                            <p style="font-size: 20px"><c:out value="${errorMessage}"/></p>
                            <a href="/" class="btn btn-info mtl"><i class="fa fa-home"></i>&nbsp;
                            Return home
                            </a>
                    </span>
                    </div>
                </div>
            </div>
        </div>
</div>
</body>
</html>
