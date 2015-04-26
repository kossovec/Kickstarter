<%@ page import="ua.goit.model.Category" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

<html>
<head>
    <title>Categories</title>
</head>
<body>
    <c:forEach var="category" items="${categories}">
        <ul>
            <li> <a href="projects?category=<c:out value="${category.id}"/>"> <c:out value="${category.categoryName}"/> </a></li>
        </ul>
    </c:forEach>
</body>
</html>
