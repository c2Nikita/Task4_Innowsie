<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Register</title>
    <style>
        body { font-family: Arial, sans-serif; background-color: #f0f2f5; display: flex; justify-content: center; align-items: center; height: 100vh; margin: 0; }
        .container { background-color: #fff; padding: 30px 40px; border-radius: 10px; box-shadow: 0 4px 12px rgba(0,0,0,0.1); width: 300px; text-align: center; }
        h1 { margin-bottom: 20px; color: #333; }
        input, select, button { width: 100%; padding: 10px; margin: 8px 0; border-radius: 5px; border: 1px solid #ccc; box-sizing: border-box; font-size: 14px; }
        button { background-color: #007bff; color: white; border: none; cursor: pointer; margin-top: 15px; font-weight: bold; }
        button:hover { background-color: #0069d9; }
        a { display: inline-block; margin-top: 15px; text-decoration: none; color: #007bff; font-size: 14px; }
        .message { margin: 10px 0; font-weight: bold; }
        .message.error { color: red; }
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
        <select id="roleSelect" name="role" required onchange="toggleTransport()">
            <option value="CLIENT">Client</option>
            <option value="COURIER">Courier</option>
        </select>

        <!-- Скрытый select для транспорта, появится только для курьера -->
        <div id="transportDiv" style="display:none;">
            <label for="transportSelect">Choose Transport Type:</label>
            <select id="transportSelect" name="transportType">
                <option value="CAR">Car</option>
                <option value="LEGS">Legs</option>
                <option value="BIKE">Bike</option>
                <option value="MOTORBIKE">Motorbike</option>
            </select>
        </div>

        <button type="submit">Create Account</button>
    </form>
    <a href="http://localhost:8080">Back</a>
</div>

<script>
    function toggleTransport() {
        const roleSelect = document.getElementById('roleSelect');
        const transportDiv = document.getElementById('transportDiv');
        if (roleSelect.value === 'COURIER') {
            transportDiv.style.display = 'block';
        } else {
            transportDiv.style.display = 'none';
        }
    }

    // вызов при загрузке страницы, если надо
    window.onload = toggleTransport;
</script>
</body>
</html>
