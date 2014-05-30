<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Список доступных сотрудников</title>
    <script type="text/javascript" src="<c:url value="/resources/js/jquery-2.1.1.min.js"/>"></script>
    <script type="text/javascript">
        function selectEmployee(id) {
            $('#selected_employee_id').val(id);
            close();
        }
    </script>
</head>
<body>
<input type="hidden" id="selected_employee_id">
<table class="table table-hover">
    <tr>
        <td>
            <strong>Имя</strong>
        </td>
        <td>
            <strong>E-mail</strong>
        </td>
        <td>
            <strong>Выбрать</strong>
        </td>
    </tr>
    <c:forEach items="${employeesList}" var="employee">
        <tr>
            <td>
                <c:out value="${employee.lastName} ${employee.firstName} ${employee.middleName}" />
            </td>
            <td>
                <c:out value="${employee.email}" />
            </td>
            <td>
                <button onclick="selectEmployee(${employee.id});" class="btn btn-primary">
                    <span class="glyphicon glyphicon-plus"></span>
                </button>
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
