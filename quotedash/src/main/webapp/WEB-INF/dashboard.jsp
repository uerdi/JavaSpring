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
    <title>QuoteDash</title>
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
    <div class="header">
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="container-fluid">
                <a class="navbar-brand" href="#">Navbar</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarTogglerDemo02" aria-controls="navbarTogglerDemo02" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarTogglerDemo02">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="#">Home</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/edit">Edit Quote</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">Disabled</a>
                        </li>
                    </ul>
                    <div class="d-flex">
                        <a href="/logout" >LogOut</a>
                    </div>
                </div>
            </div>
        </nav>

    </div>
    <h1>Welcome to Quotes ${user.userName}!</h1>
    <h3>Please feel free to share your favorite quotes with the World!</h3>

    <form:form action="/dashboard/createQuote" modelAttribute="newQuote" class="form" method="POST">
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
    <div>
        <a href="https://c4.wallpaperflare.com/wallpaper/35/750/557/oil-portrait-painting-dostoevsky-wallpaper-preview.jpg">
            <img src="https://c4.wallpaperflare.com/wallpaper/35/750/557/oil-portrait-painting-dostoevsky-wallpaper-preview.jpg">
        </a>
    </div>

    <div id="sharedQuotes">
        <h2>Shared Quotes</h2>
        <c:forEach var="quote" items="${quotes}">
            <p><strong>${quote.author}</strong></p>
            <p>${quote.quote}</p>

            <c:if test="${quote.creator.id != user.id }">
                <c:if test="${quote.likers.contains(user) == false }">

                        <a href="/like/${quote.id}">Like</a>
                </c:if>
                <c:if test="${quote.likers.contains(user) == true }">

                    <a href="/unlike/${quote.id}">Unlike </a>

                </c:if>
            </c:if>
        </c:forEach>
    </div>

</div>

</body>
</html>