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
<table>
    <tr>
        <td>Имя</td>
        <td>E-mail</td>
        <td>Выбор</td>
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
                <input type="button" value="+" onclick="selectEmployee(${employee.id});"/>
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
