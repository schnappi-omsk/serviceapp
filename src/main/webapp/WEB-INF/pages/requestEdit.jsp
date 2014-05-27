<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Заявка на обслуживание ${requestBean.request.title}</title>
    <script type="text/javascript" src="<c:url value="/resources/js/jquery-2.1.1.min.js" />"></script>
    <script type="text/javascript">

        function employeesPopup(){
            $("#msg").html('Загрузка списка сотрудников...');
            $.ajax({
                type: 'post',
                url: 'free_employees/',
                data: {'targetDate' : $('#targetDate').val()},
                success: function(response) {
                    popupWin = window.open('', 'Подбор сотрудника', '');
                    popupWin.document.write(response);
                    popupWin.onbeforeunload = function() {
                        var id = popupWin.$("#selected_employee_id").val();
                        $("#employee_id").val(id);
                        selectAssignee();
                    };
                    $("#msg").html('');
                },
                error: function() {
                    $("#msg").html('Cannot open popup');
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
                    $("#msg").html('Cannot get assignee info');
                }
            });
        }

    </script>
</head>
<body>

    <div id="msg"></div>

    <form:form commandName="requestBean" method="post" action="/request/save/">

        <form:hidden path="persisted" />

        Заголовок <form:input path="request.title" /> <br />

        Краткое описание<form:textarea path="request.description" /> <br />

        Желаемая дата <form:input path="targetDate" id="targetDate" /> <br />

        Крайний срок <form:input path="dueDate" /> <br />

        <input type="hidden" id="employee_id" />

        Сотрудник <input type = "text" id="employee_name" disabled /> <input type="button" value="..." onclick="employeesPopup();"> <br />

        <input type="submit" value="Сохранить" />

    </form:form>

</body>
</html>
