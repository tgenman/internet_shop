<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hello</title>
</head>
<body>
    <div> <a href="/client/registration">Registration</a> </div>
    <div> <a href="/client/login">Login</a> </div>
    <div> <a href="/client/all">All profiles</a> </div>
    <div>
        <form action="/logout" method="post">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <input type="submit" value="Sign Out"/>
        </form>
    </div>
</body>
</html>
