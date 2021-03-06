<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Заявка на обслуживание ${requestBean.request.title}</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/calendar.css" /> " />
    <style type="text/css">
        .left-inner-addon {
            position: relative;
        }
        .left-inner-addon input {
            padding-left: 30px;
        }
        .left-inner-addon i {
            position: absolute;
            padding: 10px 12px;
            pointer-events: none;
        }
    </style>
    <script type="text/javascript" src="<c:url value="/resources/js/jquery-1.7.2.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/jquery.ui.core.js" /> "></script>
    <script type="text/javascript" src="<c:url value="/resources/js/jquery.ui.datepicker.js"/>"></script>
    <script type="text/javascript">

        $(function(){
            $('#msg').css('visibility', 'hidden');
            var today = new Date();
            $('#targetDate').datepicker();
            $('#dueDate').datepicker();
            if ($('#employee_id').val() != 0) {
                $('#employee_name').val('Загрузка...');
                selectAssignee();
            }
        });

        function employeesPopup(){
            $('#msg').css('visibility', 'visible');
            $("#msg").removeClass('alert-success')
                    .removeClass('alert-danger')
                    .addClass('alert-info')
                    .html('Загрузка списка сотрудников...');
            $.ajax({
                type: 'post',
                url: 'free_employees/',
                data: {'targetDate' : $('#targetDate').val()},
                success: function(response) {
                    var popupWin = window.open('', 'Подбор сотрудника', '');
                    popupWin.document.write(response);
                    popupWin.onbeforeunload = function() {
                        var id = popupWin.$("#selected_employee_id").val();
                        $("#employee_id").val(id);
                        selectAssignee();
                    };
                    $("#msg").html('').css('visibility', 'hidden');
                },
                error: function() {
                    $("#msg").removeClass('alert-info')
                            .removeClass('alert-success')
                            .addClass('alert-danger')
                            .html('Невозможно открыть список');
                }
            });
        }

        function selectAssignee() {
            $.ajax({
                type: 'post',
                url: 'get_assignee/',
                data: {'employee_id' : $('#employee_id').val()},
                success: function(response) {
                    var fullName = response.person.lastName + ' ' +
                            response.person.firstName + ' ' +
                            response.person.middleName;
                    $("#employee_name").val(fullName);
                },
                error: function() {
                    $("#msg").removeClass('alert-success')
                            .removeClass('alert-info')
                            .addClass('alert-danger')
                            .html('Cannot get assignee info');
                }
            });
        }

    </script>
</head>
<body>

    <div class="row">
        <div class="col-md-12">
            <div class="btn-group">
                <input type="button" class="btn btn-danger" value="Отказать" <c:if test="${!requestBean.closed}">disabled="disabled" </c:if> />
                <input type="button" class="btn btn-primary" value="Завершить" <c:if test="${!requestBean.closed}">disabled="disabled" </c:if>/>
                <input type="button" class="btn btn-success" value="Закрыть" <c:if test="${!requestBean.closed}">disabled="disabled" </c:if>/>
                <input type="button" class="btn btn-primary" value="Открыть" <c:if test="${requestBean.closed}">disabled="disabled" </c:if>/>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-md-12">
            <div id="msg" class="alert"></div>
        </div>
    </div>

    <div class="row">

        <form:form commandName="requestBean" method="post" action="/request/save/" cssClass="form-horizontal">

            <form:hidden path="persisted" />

            <div class="form-group">
                <label for="request_title" class="col-sm-2 control-label">Заголовок</label>
                <div class="col-sm-10">
                    <form:input path="request.title" id="request_title" cssClass="form-control"/>
                </div>
            </div>

            <div class="form-group">
                <label for="request_description" class="col-sm-2 control-label">Краткое описание</label>
                <div class="col-sm-10">
                    <form:textarea path="request.description" id="request_description" cssClass="form-control" rows="6"/>
                </div>
            </div>

            <div class="form-group">
                <label for="request_address" class="col-sm-2 control-label">Адрес</label>
                <div class="col-sm-10">
                    <form:textarea path="request.address" id="request_address" cssClass="form-control" />
                </div>
            </div>

            <div class="form-group">
                <label for="targetDate" class="col-sm-2 control-label">Желаемая дата</label>
                <div class="col-sm-10">
                    <form:input path="targetDate" id="targetDate" readonly="true" cssClass="form-control" />
                </div>
            </div>

            <div class="form-group">
                <label for="dueDate" class="col-sm-2 control-label">Крайний срок</label>
                <div class="col-sm-10">
                    <form:input path="dueDate" id="dueDate" readonly="true" cssClass="form-control" />
                </div>
            </div>

            <form:hidden path="assignee" id="employee_id" />

            <div class="form-group">
                <label for="employee_name" class="col-sm-2 control-label">Сотрудник</label>
                <div class="col-sm-8">
                    <input type = "text" id="employee_name" class="form-control" disabled />
                </div>
                <div class="col-sm-2 left-inner-addon">
                    <i class="glyphicon glyphicon-list"></i>
                    <input type="button" onclick="employeesPopup();" class="btn btn-primary" value="Подбор">
                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <input type="submit" value="Сохранить" class="btn btn-primary" />
                </div>
            </div>

        </form:form>

    </div>
    <div class="row">
        <security:authorize access="isAuthenticated()">
        <div class="panel panel-default">
            <form method="post" action="/request/${requestBean.request.id}/comment/">
                <div class="panel-heading">
                    <h3 class="panel-title">Оставьте комментарий</h3>
                </div>
                <div class="panel-body">
                    <div class="form-group">
                        <div class="col-sm-10">
                            <textarea name="comment_text" cols="10" rows="3" class="form-control"></textarea>
                        </div>
                        <div class="col-sm-10">
                            <input type="submit" value="Оставить" <c:if test="${!requestBean.persisted}">disabled="disabled" </c:if> />
                        </div>
                    </div>
                </div>
            </form>
        </div>
        </security:authorize>
    </div>

    <c:forEach items="${requestBean.request.comments}" var="comment">
        <div class="panel panel-default">
            <div class="panel-heading">
                <strong>${comment.author}</strong>, от ${comment.date}
            </div>
            <div class="panel-body">
                <p>${comment.text}</p>
            </div>
        </div>
    </c:forEach>

</body>
</html>
