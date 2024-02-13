<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 2/1/2024
  Time: 5:13 PM
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
    <!-- Include necessary CSS and JS files -->
</head>
<body>

<div class="container">
    <h1>Update Profile Picture</h1>
    <form:form action="/update" method="post" enctype="multipart/form-data" modelAttribute="profilePicture">
        <div class="form-group">
            <label for="file">Choose File</label>
            <input type="file" id="file" name="file" class="form-control" />
        </div>
        <button type="submit" class="btn btn-primary">Upload</button>
    </form:form>
</div>

</body>
</html>
