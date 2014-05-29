<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>[RFS] <decorator:title default="Неизвестная страница" /></title>
    <link rel="icon" type="image/png" href="/resources/img/logo_icon.png"/>
    <decorator:head />
    <link rel="stylesheet" href="/resources/css/bootstrap.min.css" />
    <style type="text/css">
        body { padding-top: 70px; }
    </style>
</head>
<body>

<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container">
            <a class="navbar-brand"><img src="/resources/img/logo_brand.png"/></a>
            <ul class="nav navbar-nav">
                <li><a href="/employee/">Сотрудники</a></li>
                <li><a href="/request/new/">Завести заявку</a></li>
            </ul>
        </div>
    </div>
</nav>


<div class="container">
    <div class="row">
        <h1><decorator:title/></h1>

        <div class="col-md-8">
            <decorator:body/>
        </div>

        <div class="col-md-4">
            <blockquote>
                <h2>Sidebar</h2>
                <footer>Чем-то нужно заполнить</footer>
            </blockquote>
        </div>
    </div>
</div>
</body>
</html>
