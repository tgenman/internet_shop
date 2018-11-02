<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hello</title>
</head>
<body>
    <div> <a href="/user/registration">Registration</a> </div>
    <div> <a href="/user/login">Login</a> </div>
    <div> <a href="/user/all">All profiles</a> </div>
    <div>
        <form action="/logout" method="post">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <input type="submit" value="Sign Out"/>
        </form>
    </div>
</body>
</html>
