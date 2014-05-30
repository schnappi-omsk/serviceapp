<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Вход в приложение</title>
    <style type="text/css">
        .center-div {
            margin: 0 auto;
            width: 60%;
        }
    </style>
</head>
<body>

<div class="container center-div">

    <form class="form-horizontal" action="<c:url value="/j_spring_security_check"/>" method="POST">

        <div class="form-group">
            <label for="username" class="col-sm-2 control-label">Логин</label>
            <div class="col-sm-10">
                <input type="text" id="username" name="j_username" class="form-control"/>
            </div>
        </div>

        <div class="form-group">
            <label for="password" class="col-sm-2 control-label">Пароль</label>
            <div class="col-sm-10">
                <input type="password" id="password" name="j_password" class="form-control"/>
            </div>
        </div>

        <div class="checkbox">
            <label>
                <input type="checkbox" name="_spring_security_remember_me" /> Запомнить
            </label>
        </div>

        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <input type="submit" value="Войти" class="btn btn-primary"/>
            </div>
        </div>

    </form>

</div>

</body>
</html>
