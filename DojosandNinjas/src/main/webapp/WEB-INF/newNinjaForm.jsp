<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 12/20/2023
  Time: 11:59 AM
  To change this template use File | Settings | File Templates.
--%>
<!-- New line below to use the JSP Standard Tag Library -->
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="/css/main.css"/>
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>

<html>
<head>
    <title>Title</title>
</head>
<body>
<form:form action="/ninjas" method="post" modelAttribute="ninja">
    <p>
        <form:label path="firstName">Last Name</form:label>
        <form:input path="firstName"/>
    </p>
    <p>
        <form:label path="lastName">Last Name</form:label>
        <form:input path="lastName"/>
    </p>
    <p>
        <form:label path="age">Age</form:label>
        <form:input path="age"/>
    </p>
    <p>
        <form:label path="dojo">Dojo</form:label>
        <form:select path="dojo">
            <c:forEach var="eachDojo" items="${dojos}">
                <option value="${eachDojo.id}"><c:out value="${eachDojo.name}"></c:out></option>
            </c:forEach>
        </form:select>
    </p>
    <input type="submit" value="Submit"/>
</form:form>

</body>
</html>
