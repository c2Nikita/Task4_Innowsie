<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f9f9f9;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .container {
            background-color: #fff;
            padding: 30px 40px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
            text-align: center;
        }
        h1 {
            margin-bottom: 20px;
            color: #333;
        }
        input {
            width: 100%;
            padding: 10px;
            margin: 10px 0;
            border-radius: 5px;
            border: 1px solid #ccc;
            box-sizing: border-box;
        }
        button {
            width: 100%;
            padding: 10px;
            margin-top: 15px;
            background-color: #4CAF50;
            border: none;
            color: white;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
        }
        button:hover {
            background-color: #45a049;
        }
        a {
            display: inline-block;
            margin-top: 15px;
            text-decoration: none;
            color: #333;
            font-size: 14px;
        }
        .message {
            margin: 10px 0;
            font-weight: bold;
        }
        .message.success {
            color: green;
        }
        .message.error {
            color: red;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Login</h1>
    <c:if test="${not empty okMessage}">
        <p class="message success">${okMessage}</p>
    </c:if>
    <c:if test="${not empty errorMessage}">
        <p class="message error">${errorMessage}</p>
    </c:if>
    <form method="post" action="login">
        <input type="text" name="login" placeholder="Login" required>
        <input type="password" name="password" placeholder="Password" required>
        <button type="submit">Sign In</button>
    </form>
    <a href="http://localhost:8080">Back</a>
</div>
</body>
</html>
