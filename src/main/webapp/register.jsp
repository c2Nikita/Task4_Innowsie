<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Register</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f2f5;
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
            box-shadow: 0 4px 12px rgba(0,0,0,0.1);
            width: 300px;
            text-align: center;
        }
        h1 {
            margin-bottom: 20px;
            color: #333;
        }
        input, select, button {
            width: 100%;
            padding: 10px;
            margin: 8px 0;
            border-radius: 5px;
            border: 1px solid #ccc;
            box-sizing: border-box;
            font-size: 14px;
        }
        button {
            background-color: #007bff;
            color: white;
            border: none;
            cursor: pointer;
            margin-top: 15px;
            font-weight: bold;
        }
        button:hover {
            background-color: #0069d9;
        }
        a {
            display: inline-block;
            margin-top: 15px;
            text-decoration: none;
            color: #007bff;
            font-size: 14px;
        }
        .message {
            margin: 10px 0;
            font-weight: bold;
        }
        .message.error {
            color: red;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Register</h1>
    <c:if test="${not empty errorMessage}">
        <p class="message error">${errorMessage}</p>
    </c:if>
    <form method="post" action="register">
        <input type="text" name="login" placeholder="Login" required>
        <input type="password" name="password" placeholder="Password" required>
        <input type="text" name="name" placeholder="Name" required>
        <input type="email" name="email" placeholder="Email" required>
        <label for="roleSelect">Choose your role:</label>
        <select id="roleSelect" name="role" required>
            <option value="COURIER">Courier</option>
            <option value="CLIENT">Client</option>
        </select>
        <button type="submit">Create Account</button>
    </form>
    <a href="http://localhost:8080">Back</a>
</div>
</body>
</html>
