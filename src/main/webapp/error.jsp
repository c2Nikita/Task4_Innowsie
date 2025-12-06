<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Error</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8d7da;
            color: #721c24;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            height: 100vh;
            margin: 0;
        }
        .error-container {
            background-color: #f5c6cb;
            padding: 30px 50px;
            border-radius: 10px;
            text-align: center;
            box-shadow: 0 0 10px #aaa;
        }
        h1 {
            margin-bottom: 20px;
        }
        p {
            margin: 5px 0;
        }
        a {
            color: #721c24;
            text-decoration: underline;
        }
    </style>
</head>
<body>
<div class="error-container">
    <h1>Oops! Something went wrong.</h1>
    <p>We're sorry, but an unexpected error occurred.</p>

    >
    <c:if test="${not empty errorMessage}">
        <p>Error details: ${errorMessage}</p>
    </c:if>


    <c:if test="${not empty error}">
        <p>Error details: ${error.message}</p>
    </c:if>

    <p><a href="<c:url value='/index.jsp' />">Go back to Home</a></p>
</div>
</body>
</html>
