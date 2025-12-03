<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<html>
<head>
    <title>Мой профиль</title>

    <style>
        body {
            margin: 0;
            padding: 0;
            font-family: Arial, sans-serif;
            background: #f4f6f9;
        }

        .container {
            max-width: 600px;
            margin: 80px auto;
            background: #fff;
            padding: 30px;
            border-radius: 12px;
            box-shadow: 0 4px 12px rgba(0,0,0,0.1);
        }

        h1 {
            text-align: center;
            margin-bottom: 30px;
            color: #333;
            font-size: 28px;
        }

        .profile-info {
            font-size: 18px;
            line-height: 1.8;
        }

        .label {
            font-weight: bold;
            color: #555;
        }

        .logout-btn {
            display: block;
            margin: 30px auto 0;
            padding: 12px 25px;
            background: #e53935;
            color: #fff;
            border: none;
            border-radius: 6px;
            font-size: 16px;
            cursor: pointer;
            text-align: center;
            text-decoration: none;
            transition: 0.2s;
        }

        .logout-btn:hover {
            background: #c62828;
        }

        .avatar {
            width: 110px;
            height: 110px;
            border-radius: 50%;
            display: block;
            margin: 0 auto 25px;
            background: #ddd url('https://cdn-icons-png.flaticon.com/512/847/847969.png') center / cover;
        }
    </style>
</head>

<body>

<div class="container">

    <div class="avatar"></div>

    <h1>Ваш профиль</h1>

    <div class="profile-info">
        <p><span class="label">Имя:</span> ${sessionScope.user.name}</p>
        <p><span class="label">Логин:</span> ${sessionScope.user.login}</p>
        <p><span class="label">Email:</span> ${user.email}</p>
    </div>

    <a class="logout-btn" href="http://localhost:8080/logout">Выйти</a>
</div>



</body>
</html>
