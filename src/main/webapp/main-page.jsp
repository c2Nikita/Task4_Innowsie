<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Main Page</title>
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
        .order-button {
            padding: 12px 24px;
            background-color: #3498db;
            color: white;
            border: none;
            border-radius: 4px;
            font-size: 16px;
            cursor: pointer;
            transition: background-color 0.3s;
        }
        .order-button:hover {
            background-color: #2980b9;
        }
        .order-form {
            display: none;
            max-width: 400px;
            margin: 30px auto;
            padding: 20px;
            background-color: white;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
            text-align: left;
        }
        .form-group {
            margin-bottom: 15px;
        }
        label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
            color: #2c3e50;
        }
        input, textarea, select {
            width: 100%;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 4px;
            box-sizing: border-box;
            font-size: 14px;
        }
        textarea {
            resize: vertical;
            min-height: 100px;
        }
        .submit-btn {
            width: 100%;
            padding: 12px;
            background-color: #2ecc71;
            color: white;
            border: none;
            border-radius: 4px;
            font-size: 16px;
            cursor: pointer;
        }
        .submit-btn:hover {
            background-color: #27ae60;
        }
        .cancel-btn {
            width: 100%;
            padding: 12px;
            background-color: #e74c3c;
            color: white;
            border: none;
            border-radius: 4px;
            font-size: 16px;
            cursor: pointer;
            margin-top: 10px;
            transition: background-color 0.3s;
        }
        .cancel-btn:hover {
            background-color: #c0392b;
        }
    </style>
</head>
<body>

<div class="header">
    <a href="profile.jsp">Profile</a>
</div>

<div class="content">

    <!-- CLIENT CAN CREATE ORDER -->
    <c:if test="${sessionScope.user != null and sessionScope.user.userRole.name() == 'CLIENT'}">

        <h2>Welcome, client!</h2>
        <button class="order-button" id="openOrderForm">Create Order</button>

        <div class="order-form" id="orderForm">
            <h3>New Order</h3>
            <form id="createOrderForm" method="POST" action="create-order">

                <div class="form-group">
                    <label for="amount">Amount:</label>
                    <input type="number" id="amount" name="amount"
                           placeholder="Enter amount"
                           step="0.01" min="0.01" required>
                </div>

                <div class="form-group">
                    <label for="description">Description:</label>
                    <textarea id="description" name="description"
                              placeholder="Describe your order"
                              required></textarea>
                </div>

                <!-- NEW FIELD: COURIER SELECT -->
                <div class="form-group">
                    <label for="courier">Courier:</label>
                    <select id="courier" name="courierId" required>
                        <option value="">Loading...</option>
                    </select>
                </div>

                <button type="submit" class="submit-btn">Submit Order</button>
                <button type="button" class="cancel-btn" id="cancelOrder">Cancel</button>
            </form>
        </div>

    </c:if>

    <!-- NOT CLIENT -->
    <c:if test="${sessionScope.user == null or sessionScope.user.userRole.name() != 'CLIENT'}">
        <h2>Welcome!</h2>
        <p>Order creation is available only for clients.</p>
    </c:if>
</div>

<script>
    document.addEventListener('DOMContentLoaded', function() {
        const openOrderFormBtn = document.getElementById('openOrderForm');
        const orderForm = document.getElementById('orderForm');
        const cancelOrderBtn = document.getElementById('cancelOrder');

        if (openOrderFormBtn && orderForm) {
            openOrderFormBtn.addEventListener('click', function() {
                orderForm.style.display = 'block';
                openOrderFormBtn.style.display = 'none';

                // ------- LOAD ACTIVE COURIERS -------
                fetch('get-active-couriers')
                    .then(res => res.json())
                    .then(data => {
                        const select = document.getElementById('courier');
                        select.innerHTML = '';

                        if (data.length === 0) {
                            select.innerHTML = '<option value="">No couriers available</option>';
                            return;
                        }

                        data.forEach(courier => {
                            const opt = document.createElement('option');
                            opt.value = courier.id;
                            opt.textContent = "Courier #" + courier.id+" TransportType: "+courier.transportType+" Rating: "+courier.rating; // заменяем name на id
                            select.appendChild(opt);
                        });
                    })
                    .catch(() => {
                        document.getElementById('courier').innerHTML =
                            '<option value="">Error loading couriers</option>';
                    });
                // ------------------------------------
            });
        }

        if (cancelOrderBtn && orderForm && openOrderFormBtn) {
            cancelOrderBtn.addEventListener('click', function() {
                orderForm.style.display = 'none';
                openOrderFormBtn.style.display = 'block';
                document.getElementById('createOrderForm').reset();
            });
        }

        const form = document.getElementById('createOrderForm');
        if (form) {
            form.addEventListener('submit', function(e) {
                const amount = parseFloat(document.getElementById('amount').value);
                if (amount <= 0) {
                    e.preventDefault();
                    alert('Amount must be greater than 0');
                }
            });
        }
    });
</script>


</body>
</html>
