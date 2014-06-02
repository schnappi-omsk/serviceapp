<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Заявки за период</title>
    <link rel="stylesheet" href="/resources/css/calendar.css">
    <script type="text/javascript" src="/resources/js/jquery-1.7.2.js"></script>
    <script type="text/javascript" src="/resources/js/jquery.ui.core.js"></script>
    <script type="text/javascript" src="/resources/js/jquery.ui.datepicker.js"></script>
    <script type="text/javascript">
        $(function(){
            var now = new Date();
            var nowString = ('0' +now.getDate()).slice(-2)
                    + "." + ('0'+ (now.getMonth() + 1) ).slice(-2)
                    + "." + now.getFullYear();
            $('#dateFrom').val(nowString).datepicker();
            $('#dateTo').val(nowString).datepicker();
        });
    </script>
</head>
<body>

<form method="POST" class="form-horizontal" action="<c:url value="/report/period/get"/>">

<h2>Поиск заявок</h2>

<div class="form-group">
    <label for="dateFrom" class="control-label col-sm-2">С:</label>
    <div class="col-sm-10">
        <input type="text" readonly class="form-control" id="dateFrom" name="dateFrom">
    </div>
</div>

<div class="form-group">
    <label for="dateTo" class="control-label col-sm-2">По:</label>
    <div class="col-sm-10">
        <input type="text" readonly class="form-control" id="dateTo" name="dateTo">
    </div>
</div>

<div class="form-group">
    <div class=" col-sm-offset-2 col-sm-10">
        <input type="submit" class="btn btn-primary" value="Получить отчет" id="getReport">
    </div>
</div>

</form>

</body>
</html>
