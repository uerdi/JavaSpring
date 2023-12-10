<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 12/6/2023
  Time: 12:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- New line below to use the JSP Standard Tag Library -->
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
    <title>Title</title>
    <!-- for Bootstrap CSS -->
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
    <!-- YOUR own local CSS -->

    <!-- For any Bootstrap that uses JS -->
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>

</head>
<body>
<h1>Here's youre Omikuji!</h1>
<h3>
    <c:out value="${result}"></c:out>
</h3>
<div class="conter">
    <a href="/omikuji">Go Back</a>
</div>
</body>
</html>
