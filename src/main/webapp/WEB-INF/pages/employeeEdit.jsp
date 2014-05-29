<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta charset="UTF-8" />
    <title>Редактировать данные сотрудника ${personBean.person.firstName}</title>

    <script type="text/javascript" src="<c:url value="/resources/js/jquery-2.1.1.min.js"/>"></script>

    <script type="text/javascript">
        function addContact() {
            $.ajax({
                type: "post",
                url: "addContact",
                data: {
                    'contactType': $("#contactType").val(),
                    'contactValue': $("#contactValue").val()
                },
                success: function(response){
                    $("#contactList").find("> tbody:last").append(
                            "<tr>" +
                                    "<td>" +
                                    response.contact.type +
                                    "</td>" +
                                    "<td>" +
                                    response.contact.value +
                                    "</td>" +
                                    "<td>" +
                                    "</td>" +
                                    "</tr>"
                    );
                    $("#contactType").val("");
                    $("#contactValue").val("");
                },
                error: function(){
                    alert('Не удалось сохранить запись');
                }
            });
        }


        function addSchedule() {
            $.ajax({
                type: "post",
                url: "addSchedule",
                data: {
                    'scheduleName' : $("#scheduleName").val()
                },
                success: function(response){
                    $("#scheduleList").find("> tbody:last").append(
                            "<tr>" +
                                    "<td>" +
                                    "<a href='schedule/" + response.schedule.businessCode + "/'/'>" +
                                    response.schedule.name +
                                    "</a>" +
                                    "</td>" +
                                    "<td>" +
                                    "..." +
                                    "</td>" +
                                    "</tr>"
                    );
                },
                error: function(){
                    alert('Не удалось сохранить запись');
                }
            });
        }

    </script>

</head>
<body>

    <form:form commandName="personBean" action="/employee/save/" acceptCharset="UTF-8" cssClass="form-horizontal">

        <form:hidden path="persisted" />

        <div class="form-group">
            <label for="person_id" class="col-sm-2 control-label">ID</label>
            <div class="col-sm-10">
                <form:input path="person.id" readonly="true" id="person_id" cssClass="form-control" />
            </div>
        </div>

        <div class="form-group">
            <label for="person_last_name" class="col-sm-2 control-label">Фамилия</label>
            <div class="col-sm-10">
                <form:input path="person.lastName" id="person_last_name" cssClass="form-control"/>
             </div>
        </div>

        <div class="form-group">
            <label for="person_first_name" class="col-sm-2 control-label">Имя</label>
            <div class="col-sm-10">
                <form:input path="person.firstName" id="person_first_name" cssClass="form-control" />
            </div>
        </div>

        <div class="form-group">
            <label for="person_middle_name" class="col-sm-2 control-label">Отчество</label>
            <div class="col-sm-10">
                <form:input path="person.middleName" id="person_middle_name" cssClass="form-control" />
            </div>
        </div>

        <div class="form-group">
            <label for="person_email" class="col-sm-2 control-label">E-mail</label>
            <div class="col-sm-10">
                <form:input path="person.email" id="person_email" cssClass="form-control" />
            </div>
        </div>

        <div class="form-group">
            <input type="submit" value="Сохранить" class="btn btn-primary"/>
        </div>

    </form:form>

    <h2>Контакты</h2>

    <table id="contactList">
        <thead>
        <tr>
            <td>Тип</td>
            <td>Значеие</td>
            <td>Действия</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${personBean.person.contacts}" var="contact">
            <tr>
                <td>${contact.type}</td>
                <td>${contact.value}</td>
                <td></td>
            </tr>
        </c:forEach>
        </tbody>
        <tfoot>
        <tr>
            <td>
                <input type="text" id="contactType"  <c:if test="${!personBean.persisted}"><c:out value="disabled='disabled'"/></c:if> />
            </td>
            <td>
                <input type="text" id="contactValue" <c:if test="${!personBean.persisted}"><c:out value="disabled='disabled'"/></c:if>/>
            </td>
            <td>
                <input type="button" name="saveContact" onclick="addContact();" value="+" <c:if test="${!personBean.persisted}"><c:out value="disabled='disabled'"/></c:if>/>
            </td>
        </tr>
        </tfoot>
    </table>

    <h2>Графики работы</h2>

    <table id="scheduleList">
        <thead>
        <tr>
            <td>Наименование</td>
            <td>Действия</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${personBean.person.workSchedules}" var="schedule">
            <tr>
                <td>
                    <a href="schedule/${schedule.businessCode}/">${schedule.name}</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
        <tfoot>
            <tr>
                <td>
                    <input type="text" id="scheduleName" />
                </td>
                <td>
                    <input type="button" name="saveSchedule" onclick="addSchedule();" value="+" />
                </td>
            </tr>
        </tfoot>
    </table>

</body>
</html>
