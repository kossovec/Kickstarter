<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

<html>
<head>
    <title>Categories</title>
</head>
<body>
    <c:forEach var="c" items="${categories}">
        <c:out value="${c.name}"/>
    </c:forEach>
</body>
</html>
