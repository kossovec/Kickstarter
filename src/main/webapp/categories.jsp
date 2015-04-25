<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Categories</title>
</head>
<body>
    <c:forEach var="category" items="${categories}">
        <c:out value="${category.name}"></c:out>
    </c:forEach>
</body>
</html>
