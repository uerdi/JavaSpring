<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 1/4/2024
  Time: 6:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ page isErrorPage="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>New Listing</title>
    <a href="/home">dashboard</a>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/main.css"> <!-- change to match your file/naming structure -->
    <script src="/webjars/jquery/jquery.min.js"></script>
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <form:form action="/new" modelAttribute="hunters" class="form" method="post">
        <div class="form-row">
            <form:errors path="address" class="error"/>
            <form:label for="address"  path="address">Adress</form:label>
            <form:input type="text" path="address" class="form-control"/>
        </div>
        <div class="form-row">
            <form:errors path="price" class="error"/>
            <form:label for="price"  path="price">Adress</form:label>
            <form:input type="text" path="price" class="form-control"/>
        </div>
        <div class="form-row">
            <input type="submit" value="submit" class="btn-primary"/>
        </div>
        <div class="form-row">
            <button >Delete</button>
        </div>


    </form:form>

</div>


</body>
</html>
