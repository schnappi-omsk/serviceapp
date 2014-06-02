<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta charset="UTF-8" />
    <title>
        Данные сотрудника -
        <c:if test="${personBean.persisted}">
            ${personBean.person.lastName} ${personBean.person.firstName} ${personBean.person.middleName}
        </c:if>
        <c:if test="${!personBean.persisted}">
            Новый сотрудник
        </c:if>
    </title>

    <script type="text/javascript" src="<c:url value="/resources/js/jquery-2.1.1.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/bootstrap.min.js" /> "></script>

    <script type="text/javascript">

        $(function(){
            $('#person_password').val($('#confirm_password').val());
        })

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

        $('#tabs a').click(function(e) {
            e.preventDefault();
            $(this).tab('show');
        });

    </script>

    <security:authorize access="isAuthenticated() and hasRole('ROLE_ADMIN')">
    <script>
        function enableChangeCredentials() {
            $('#person_username').prop('disabled', false).prop('readonly', '');
            $('#person_password').prop('disabled', false).prop('readonly', '').val('');
            $('#confirm_password').prop('disabled', false).prop('readonly', '').val('');
        }

        function beforeSend() {
            if ($('#person_password').prop('disabled') == false) {
                if ($('#person_username').val() == '') {
                    alert("Логин не может быть пустым");
                    return false;
                }
                if ($("#person_password").val() == '') {
                    alert("Пароль не может быть пустым");
                    return false;
                }
                if ($('#person_password').val() == $('#confirm_password').val() && $('#person_password').val() != '') {
                    return true;
                } else {
                    alert("Пароль и подтверждение пароля не совпадают");
                    return false;
                }
            } else return true;
        }
    </script>
    </security:authorize>

    <security:authorize access="isAuthenticated() and !hasRole('ROLE_ADMIN')">
        <script>
        function beforeSend() {
            return true;
        }
        </script>
    </security:authorize>

</head>
<body>
<ul class="nav nav-tabs" id="tabs">
    <li class="active">
        <a href="#personal_data" data-toggle="tab">Персональные данные</a>
    </li>
    <li>
        <a href="#contacts" data-toggle="tab">Контакты</a>
    </li>
    <li>
        <a href="#schedules" data-toggle="tab">График работы</a>
    </li>
</ul>

<div id="tab_content" class="tab-content">
    <div class="tab-pane fade active in" id="personal_data">
        <p>
        <form:form commandName="personBean" action="/employee/save/" acceptCharset="UTF-8" cssClass="form-horizontal" id="personForm" onsubmit="beforeSend();">

            <form:hidden path="persisted"/>

            <div class="form-group">
                <label for="person_id" class="col-sm-2 control-label">ID</label>

                <div class="col-sm-10">
                    <form:input path="person.id" readonly="true" id="person_id" cssClass="form-control"/>
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
                    <form:input path="person.firstName" id="person_first_name" cssClass="form-control"/>
                </div>
            </div>

            <div class="form-group">
                <label for="person_middle_name" class="col-sm-2 control-label">Отчество</label>

                <div class="col-sm-10">
                    <form:input path="person.middleName" id="person_middle_name" cssClass="form-control"/>
                </div>
            </div>

            <div class="form-group">
                <label for="person_email" class="col-sm-2 control-label">E-mail</label>

                <div class="col-sm-10">
                    <form:input path="person.email" id="person_email" cssClass="form-control"/>
                </div>
            </div>

            <div class="form-group">
                <label for="person_username" class="col-sm-2 control-label">Логин</label>
                <security:authorize access="isAuthenticated() and hasRole('ROLE_ADMIN')">
                    <div class="col-sm-8">
                        <form:input path="person.username" id="person_username" cssClass="form-control" readonly="true"/>
                    </div>
                    <div class="col-sm-2">
                        <input type="button" value="Сменить" onclick="enableChangeCredentials();" class="btn btn-primary">
                    </div>
                </security:authorize>
                <security:authorize access="isAuthenticated() and !hasRole('ROLE_ADMIN')">
                    <div class="col-sm-10">
                        <form:input path="person.username" id="person_username" cssClass="form-control" readonly="true"/>
                    </div>
                </security:authorize>
            </div>

            <div class="form-group">
                <label for="person_password" class="col-sm-2 control-label">Новый пароль</label>
                <div class="col-sm-10">
                    <form:password path="person.password" id="person_password" cssClass="form-control" readonly="true"/>
                </div>
            </div>

            <div class="form-group">
                <label for="confirm_password" class="col-sm-2 control-label">Подтверждение пароля</label>
                <div class="col-sm-10">
                    <input type="password" value="${personBean.person.password}" id="confirm_password" readonly class="form-control">
                </div>
            </div>

            <div class="form-group">
                <label for="person_role" class="col-sm-2 control-label">Роль в RFS</label>
                <div class="col-sm-10">
                    <form:select path="person.role" id="person_role" cssClass="form-control">
                        <form:options items="${personBean.person.role.declaringClass}" />
                    </form:select>
                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <input type="submit" value="Сохранить" class="btn btn-primary"/>
                </div>
            </div>
        </p>

        </form:form>
    </div>

    <div class="tab-pane fade" id="contacts">

        <table id="contactList" class="table table-hover">
            <thead>
            <tr>
                <td>Тип</td>
                <td>Значение</td>
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
                    <input type="text" id="contactType"  <c:if test="${!personBean.persisted}"><c:out
                            value="disabled='disabled'"/></c:if> />
                </td>
                <td>
                    <input type="text" id="contactValue" <c:if test="${!personBean.persisted}"><c:out
                            value="disabled='disabled'"/></c:if>/>
                </td>
                <td>
                    <button class="btn btn-primary btn-xs" onclick="addContact();" <c:if
                            test="${!personBean.persisted}"><c:out value="disabled='disabled'"/></c:if>>
                        <span class="glyphicon glyphicon-plus"></span>
                    </button>
                </td>
            </tr>
            </tfoot>
        </table>
    </div>

    <div class="tab-pane fade" id="schedules">

        <table id="scheduleList" class="table table-hover">
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
                    <td>
                        <a href="#" class="btn btn-danger btn-xs">
                            <span class="glyphicon glyphicon-remove"></span> Удалить
                        </a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
            <tfoot>
            <tr>
                <td>
                    <div class="form-group">
                        <label for="scheduleName" class="col-sm-2 control-label"></label>
                        <div class="col-sm-10">
                            <input type="text" id="scheduleName" class="form-control"
                                   placeholder="Имя (рекомендуется называть по имени месяца)"
                                    <c:if test="${!personBean.persisted}"><c:out
                                    value="disabled='disabled'"/></c:if>/>
                        </div>
                    </div>
                </td>
                <td>
                    <button name="saveSchedule" onclick="addSchedule();"
                            class="btn btn-primary btn-xs"
                            <c:if test="${!personBean.persisted}"><c:out
                                    value="disabled='disabled'"/></c:if>>
                        <span class="glyphicon glyphicon-plus"></span> Добавить
                    </button>
                </td>
            </tr>
            </tfoot>
        </table>
    </div>
</div>


</body>
</html>
