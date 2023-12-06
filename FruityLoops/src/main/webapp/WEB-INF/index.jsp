<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 12/5/2023
  Time: 5:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- New line below to use the JSP Standard Tag Library -->
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>

<html>
    <head>
        <!-- for Bootstrap CSS -->
        <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
        <link rel="stylesheet" href="style.css">
        <title>Title</title>
    </head>
    <body>
        <div>
            <h1>Fruits Store</h1>
            <table class="table table-bordered custom-table">
                <thead>
                    <th scope="col">Name</th>
                    <th scope="col">Price</th>
                </thead>
                <tbody>
                    <c:forEach items="${fruits}" var="item">
                        <tr>
                            <td><c:out value="${item.name}"></c:out></td>
                            <td><c:out value="${item.price}"></c:out></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

    </body>
</html>
