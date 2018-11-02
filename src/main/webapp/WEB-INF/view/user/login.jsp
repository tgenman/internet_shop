<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>LoginPage</title>
</head>
<body>
    <div> <a href="/">Home page</a> </div>

    <h2>Login Page</h2>

    <div>
        <form action="/user/login" method="post">
            <div class="form-group">
                <input type="text" class="form-control" name="username" placeholder="username" />
            </div>
            <div class="form-group">
                <input type="text" class="form-control" name="password" placeholder="password" />
            </div>
            <div class="form-group">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </div>
            <div class="form-group">
                <input type="submit" value="submit" />
            </div>
        </form>
    </div>

    <div> <a href="/user/registration">Registration</a> </div>

</body>
</html>
