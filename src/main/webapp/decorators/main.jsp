<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>[RFS] <decorator:title default="Неизвестная страница" /></title>
    <link rel="icon" type="image/png" href="/resources/img/logo_icon.png"/>
    <link rel="stylesheet" href="/resources/css/bootstrap.min.css" />
    <style type="text/css">
        body { padding-top: 70px; }
    </style>
    <script type="text/javascript" src="/resources/js/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="/resources/js/bootstrap.min.js" ></script>
    <script type="text/javascript">
        $('.collapse').collapse();
        $('.dropdown-toggle').dropdown();
    </script>
    <decorator:head />
</head>
<body>

<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="navbar-inner">
        <div class="collapse navbar-collapse">
            <a class="navbar-brand"><img src="/resources/img/logo_brand.png"/></a>
            <ul class="nav navbar-nav">
                <security:authorize access="isAuthenticated() and hasRole('ROLE_ADMIN')">
                    <li><a href="/employee/">Сотрудники</a></li>
                </security:authorize>
                <li><a href="/request/new/">Завести заявку</a></li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        Заявки <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="#">По датам</a></li>
                        <li><a href="#">По инженерам</a></li>
                    </ul>
                </li>
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
