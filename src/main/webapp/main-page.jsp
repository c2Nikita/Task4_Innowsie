<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Главная страница</title>
    <style>
        body {
            margin: 0;
            font-family: Arial, sans-serif;
            background-color: #f4f6f9;
        }

        .header {
            display: flex;
            align-items: center;
            padding: 15px 30px;
            background-color: #2c3e50;
            color: white;
        }

        .header a {
            color: white;
            text-decoration: none;
            font-weight: bold;
            margin-right: 20px;
        }

        .header a:hover {
            text-decoration: underline;
        }

        .content {
            padding: 40px;
            text-align: center;
        }
    </style>
</head>
<body>

<div class="header">
    <!-- Ссылка на профиль -->
    <a href="http://localhost:8080/profile.jsp">Profile</a>
    <!-- Здесь можно будет добавить другие элементы шапки -->
</div>

<div class="content">
    <!-- Пока пустое содержимое -->
</div>

</body>
</html>
