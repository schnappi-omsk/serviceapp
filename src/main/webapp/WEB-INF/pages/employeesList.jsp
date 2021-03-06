<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Список сотрудников</title>
</head>
<body>

    <table class="table table-hover">
        <tr>
            <td>ID</td>
            <td>Имя</td>
            <td>
                E-mail
                <a href="<c:url value="/employee/edit/new/"/>" class="btn btn-primary btn-xs pull-right">
                    <span class="glyphicon glyphicon-plus"></span> Добавить
                </a>
            </td>
        </tr>
        <c:forEach items="${employees}" var="person">
            <tr>
                <td><c:out value="${person.id}"/></td>
                <td>
                    <a href="<c:url value="/employee/edit/${person.id}/" /> ">
                        <c:out value="${person.lastName} ${person.firstName} ${person.middleName}"/>
                    </a>
                </td>
                <td><c:out value="${person.email}" /></td>
            </tr>
        </c:forEach>
    </table>

</body>
</html>
