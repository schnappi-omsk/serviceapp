<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Заполнение графика</title>
    <script src="/resources/js/jquery-1.7.2.js"></script>
    <script src="/resources/js/jquery.ui.core.js"></script>
    <script src="/resources/js/jquery.ui.datepicker.js"></script>
    <script src="/resources/js/jquery-ui.multidatespicker.js"></script>
    <link rel="stylesheet" href="/resources/css/calendar.css">
    <script>
        $(function(){
            $('#calendar').multiDatesPicker();
        });
    </script>
</head>
<body>

    <c:out value="${scheduleBean.schedule.name}" />

    <div id="calendar"></div>

</body>
</html>
