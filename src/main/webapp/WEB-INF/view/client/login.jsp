<%--
  Created by IntelliJ IDEA.
  User: tgenman
  Date: 29/10/2018
  Time: 17:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>LoginPage</title>
</head>
<body>
    <div> <a href="/">Home page</a> </div>

    <h2>Login Page</h2>

    <div>
        <form action="login" method="post">
            <div class="form-group">
                <input type="text" class="form-control" name="email" placeholder="email" />
            </div>
            <div class="form-group">
                <input type="text" class="form-control" name="password" placeholder="password" />
            </div>
            <div class="form-group">
                <input type="submit" value="submit" />
            </div>
        </form>
    </div>
</body>
</html>
