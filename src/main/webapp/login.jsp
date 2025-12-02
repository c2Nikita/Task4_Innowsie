<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Write your login and password!</title>
</head>
<body>
<h1>Write login and password:</h1>
<c:if test="${not empty okMessage}">
    <p style="color:green;">${okMessage}</p>
</c:if>
<c:if test="${not empty errorMessage}">
    <p style="color:red;">${errorMessage}</p>
</c:if>
<form method="post" action="login">
    <input type="text" name="login" placeholder="login"><br>
    <input type="password" name="password" placeholder="password"><br>
    <button type="submit">Sign In</button>
</form>
    <a href="http://localhost:8080">Back</a>
</body>
</html>
