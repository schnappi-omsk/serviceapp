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

        //TODO: implement dates pick on page load

        function retrieveDates() {
            return $('#calendar').multiDatesPicker('getDates');
        }

        function sendDates() {
            $.ajax({
                type: "post",
                url: 'add_dates',
                data: {
                    'dates' : retrieveDates()
                },
                success: function(response) {
                    $("#msg").html(response.schedule.name + " saved");
                },
                error: function() {
                    $("#msg").html("Cannot save schedule data.");
                }
            });
        }

    </script>
</head>
<body>

    <div id="msg"></div>

    <div id="calendar"></div>

    <input type="button" value="Check dates" onclick="sendDates();" />

</body>
</html>
