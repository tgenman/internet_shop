<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
    <div> <a href="/">Home page</a> </div>

    <div><h2>Registration form</h2></div>

    <div><h3>${message}</h3></div>

    <div>
        <form method="post">
            <div class="form-group">
                <input type="text" class="form-control" name="name" placeholder="First Name" />
            </div>
            <div class="form-group">
                <input type="text" class="form-control" name="familyName" placeholder="Family Name" />
            </div>
            <div class="form-group">
                <input type="text" class="form-control" name="email" placeholder="email" />
            </div>
            <div class="form-group">
                <input type="text" class="form-control" name="password" placeholder="password" />
            </div>
            <div class="form-group">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </div>

            <%--<div class="form-group">--%>
                <%--<input type="text" class="form-control" name="passwordAgain" placeholder="password again" />--%>
            <%--</div>--%>
            <div class="form-group">
                <button type="submit">Register</button>
                <%--<input type="submit" value="submit" />--%>
            </div>
        </form>
    </div>

</body>
</html>
