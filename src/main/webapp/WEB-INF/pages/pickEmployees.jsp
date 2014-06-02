<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Подбор сотрудников</title>
    <script type="text/javascript">
        function selectEmployee(id) {
            $('#personId').val(id);
            close();
        }
    </script>
</head>
<body>
    <input type="hidden" id="personId" />
    <table class="table table-hover">

        <tr>
            <td>Имя</td>
            <td>E-mail</td>
            <td>Выбрать</td>
        </tr>

        <c:forEach items="${employeesList}" var="person">
            <tr>
                <td>
                    ${person.lastName} ${person.firstName} ${person.middleName}
                </td>
                <td>
                    ${person.email}
                </td>
                <td>
                    <input type="button" class="btn btn-primary btn-xs" value="Выбрать" onclick="selectEmployee(${person.id})" />
                </td>
            </tr>
        </c:forEach>

    </table>

</body>
</html>
