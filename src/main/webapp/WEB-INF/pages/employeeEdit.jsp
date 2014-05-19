<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta charset="UTF-8" />
    <title>Редактировать данные сотрудника ${personBean.person.name}</title>
</head>
<body>

    <form:form commandName="personBean" action="/employee/save/" acceptCharset="UTF-8">

        <form:hidden path="persisted" />

        ID <form:input path="person.id" readonly="true" /> <br />
        Имя <form:input path="person.name" />

        <input type="submit" value="Сохранить" />

    </form:form>

</body>
</html>
