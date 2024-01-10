<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 1/4/2024
  Time: 7:51 PM
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
    <title>Elm Street</title>
    <a href="/home">dashboard</a>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/main.css"> <!-- change to match your file/naming structure -->
    <script src="/webjars/jquery/jquery.min.js"></script>
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
<div>
<h3>Adress:15 Elm Street</h3>
<h3>Listing Date:2028-06-22</h3>
<h3>Price:$349000</h3>
</div>

<div>
<h2>Notes</h2>
<h3>Added by Tom- Roof will need to be replaced soon</h3>
<h3>Added by Jane-Too many trees around the house</h3>
<h3>Added by Jane- Nice new deck</h3>
</div>

<div class="form-row">
<h2>Add note</h2>
    <form:errors path="note" class="error"/>
    <form:label for="note"  path="note">Note</form:label>
    <form:input type="text" path="note" class="form-control"/>
    <button>Add Note</button>

</div>


</body>
</html>
