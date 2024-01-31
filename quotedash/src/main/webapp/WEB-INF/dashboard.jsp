<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 12/21/2023
  Time: 12:12 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- New line below to use the JSP Standard Tag Library -->
<!-- New line below to use the JSP Standard Tag Library -->
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>

<html>
<head>
    <title>Title</title>
    <!-- for Bootstrap CSS -->
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
          crossorigin="anonymous">
    <!-- YOUR own local CSS -->
    <link rel="stylesheet" href="/css/main.css"/>
    <!-- For any Bootstrap that uses JS -->
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <h1>Welcome to Quotes!</h1>
    <h3>Please feel free to share your favorite quotes with the World!</h3>

    <form:form action="/dashboard/createQuote" modelAttribute="quote" class="form" method="POST">
    <div class="form-row">
            <div class="col-md-6">
                <form:errors path="author" class="error"/>
                <form:label for="author" path="author">Author:</form:label>
                <form:input type="text" path="author" class="form-control"/>
            </div>
            <div class="col-md-6">
                <form:errors path="quote" class="error"/>
                <form:label for="quote" path="quote">Quote:</form:label>
                <form:input type="text" path="quote" class="form-control"/>
            </div>
        </div>

        <div class="form-row mt-3">
            <div class="col-md-12">
                <button type="submit" class="btn btn-primary">Share Quote</button>
            </div>
        </div>
    </form:form>

    <div id="sharedQuotes">
        <h2>Shared Quotes</h2>
        <c:forEach var="sharedQuote" items="${sharedQuotes}">
            <p><strong>${sharedQuote.author}</strong></p>
            <p>${sharedQuote.quote}</p>
        </c:forEach>
    </div>
</div>

</body>
</html>