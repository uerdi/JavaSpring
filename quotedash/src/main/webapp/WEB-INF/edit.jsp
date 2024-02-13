<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 2/1/2024
  Time: 12:30 PM
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
</head>
<body>
<!-- Inside the modal for editing user information -->
<div class="modal fade" id="editAccountModal" tabindex="-1" role="dialog" aria-labelledby="editAccountModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h2 class="modal-title" id="editAccountModalLabel">Edit Account Information</h2>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form:form action="/update" method="PUT" modelAttribute="editedUser" class="form">
                    <div class="form-group">
                        <form:errors path="userName" class="error"/>
                        <form:label for="name" path="userName">Name:</form:label>
                        <form:input type="text" path="userName" class="form-control"/>
                    </div>
                    <div class="form-group">
                        <form:errors path="email" class="error"/>
                        <form:label for="email" path="email">Email:</form:label>
                        <form:input type="email" path="email" class="form-control"/>
                    </div>
                    <button type="submit" class="btn btn-primary">Save</button>
                </form:form>
            </div>
        </div>
    </div>
</div>


</body>
</html>
