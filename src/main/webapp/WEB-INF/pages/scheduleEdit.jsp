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
            $('#msg').css('visibility', 'hidden');
        });

        receiveDates();

        function msgVisibility() {
            if ($('#msg').css('visibility') == 'hidden') {
                $('#msg').css('visibility', 'visible');
            } else {
                $('#msg').css('visibility', 'hidden');
            }
        }

        function retrieveDates() {
            return $('#calendar').multiDatesPicker('getDates');
        }

        function sendDates() {
            $('#msg').css('visibility', 'visible');
            $("#msg").removeClass("alert-success")
                    .removeClass("alert-danger")
                    .addClass("alert-info")
                    .html("Подождите...").fadeIn('slow');
            $.ajax({
                type: "post",
                url: 'add_dates',
                data: {
                    'dates' : retrieveDates()
                },
                success: function(response) {
                    $("#msg").removeClass("alert-danger")
                            .removeClass("alert-info")
                            .addClass("alert-success").fadeIn('slow');
                    $("#msg").html("График '" + response.schedule.name + "' сохранен.");
                },
                error: function() {
                    $("#msg").removeClass("alert-info")
                            .removeClass("alert-success")
                            .addClass("alert-danger").fadeIn('slow');
                    $("#msg").html("Неудачная попытка сохранения.");
                }
            });
        }

        function receiveDates() {
            $("#msg").html("Загрузка графика...");
            $.ajax({
                type: "get",
                url: "get_dates",
                success: function(response) {
                    $('#calendar').multiDatesPicker('addDates', response);
                    $('#msg').html("");
                },
                error: function() {
                    $("#msg").html("Cannot receive dates");
                }
            });
        }

    </script>
</head>
<body onload="receiveDates();">

<div class="row">
    <div class="col-md-12">
        <div id="msg" class="alert"></div>
    </div>
</div>

<div class="row">

    <div class="col-md-2"></div>

    <div class="col-md-4">

        <div id="calendar"></div>

    </div>

    <div class="col-md-2"></div>

    <div class="col-md-4">
        <p>
            Выберите нужные даты в графике и нажмите кнопку "Сохранить". Рекомендуемый размер графика - 1 месяц, но
            технически система разрешает любой размер.
        </p>

        <p>
            При возникновении вопросов и/или неполадок, обратитесь к системному администратору.
        </p>
    </div>
</div>

<div class="row">
    <div class="col-md-12">
        <hr />
    </div>
</div>

<div class="row">
    <div class="col-md-6"></div>
    <div class="col-md-2">
        <div class="form-group">
            <input type="button" value="Сохранить" onclick="sendDates();" class="btn btn-primary"/>
        </div>
    </div>
    <div class="col-md-4"></div>
</div>

</body>
</html>
