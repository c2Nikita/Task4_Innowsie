<%--
  Created by IntelliJ IDEA.
  User: nikit
  Date: 30.11.2025
  Time: 17:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>Write your data:</h1>
    <c:if test="${not empty errorMessage}">
        <p style="color:red;">${errorMessage}</p>
    </c:if>
    <form method="post" action="register">
        <input type="text" name="login" placeholder="login"><br>
        <input type="password" name="password" placeholder="password"><br>
        <input type="text" name="name" placeholder="name"><br>
        <input type="text" name="email" placeholder="email"><br>
        <button type="submit">Create Account</button>
    </form>
    <a href="http://localhost:8080">Back</a>
</body>
</html>
