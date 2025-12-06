<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Welcome</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      background-color: #f2f2f2;
      display: flex;
      flex-direction: column;
      align-items: center;
      justify-content: center;
      height: 100vh;
      margin: 0;
    }
    h1 {
      color: #333;
    }
    .links {
      margin-top: 20px;
    }
    a {
      display: block;
      margin: 10px 0;
      padding: 10px 20px;
      width: 200px;
      text-align: center;
      background-color: #4CAF50;
      color: white;
      text-decoration: none;
      border-radius: 5px;
      transition: background-color 0.3s ease;
    }
    a:hover {
      background-color: #45a049;
    }
  </style>
</head>
<body>
<h1>Welcome! Please choose an option:</h1>
<div class="links">
  <a href="http://localhost:8080/login.jsp">Sign In</a>
  <a href="http://localhost:8080/register.jsp">Register</a>
</div>
</body>
</html>
