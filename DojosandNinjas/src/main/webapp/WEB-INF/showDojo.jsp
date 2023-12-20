<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 12/20/2023
  Time: 11:59 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
</head>
<body>
<h1><c:out value="${dojo.name}"/></h1>
<ul>
    <c:forEach var="ninja" items="${dojos.ninjas}">

        <li><c:out value="${ninja.firstName}"/><c:out value="${ninja.lastName}"/></li>

    </c:forEach>
</ul>



</body>
</html>
