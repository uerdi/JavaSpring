<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 12/20/2023
  Time: 11:58 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>Insert title here</title>
</head>
<body >
<h1>New Dojo </h1>
<%--@elvariable id="dojo" type="jakarta"--%>
<form:form action="/dojos" method="post" modelAttribute="dojo">
    <p>
        <form:label path="name">Title</form:label>
        <form:errors path="name"/>
        <form:input path="name"/>
    </p>
    <input type="submit" value="Submit"/>
</form:form>

</body>
</html>