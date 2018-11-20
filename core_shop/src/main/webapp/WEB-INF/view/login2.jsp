<!doctype html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring"   uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn"       uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form"     uri="http://www.springframework.org/tags/form" %>

<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title>Login</title>

    <!-- Bootstrap core CSS -->
    <link href="https://drive.google.com/file/d/1M3YyRT-L3MBhOOj9tRAkGVIs1hxbiCA5/view?usp=sharing" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="https://drive.google.com/open?id=1dZeB7Otx0n1XjJfnfyfS277eIq504oiO" rel="stylesheet">
</head>

<body class="text-center">

<form action="/login" class="form-signin">
    <img class="mb-4" src="https://drive.google.com/file/d/1O1OYCSWqe3UUsYa9bwEn8fSOZfSbaSBW/view?usp=sharing" alt="" width="72" height="72">
    <h1 class="h3 mb-3 font-weight-normal">Please sign in</h1>
    <label for="inputEmail" class="sr-only">Email address</label>
    <input name="username" type="text" id="inputEmail" class="form-control" placeholder="Email address" required autofocus>
    <label for="inputPassword" class="sr-only">Password</label>
    <input name="password" type="password" id="inputPassword" class="form-control" placeholder="Password" required>
    <div class="checkbox mb-3">
        <label>
            <input type="checkbox" value="remember-me"> Remember me
        </label>
    </div>
    <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
    <p class="mt-5 mb-3 text-muted">&copy; 2017-2018</p>
</form>
<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<%--<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>--%>
<%--<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>--%>
<%--<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>--%>
</body>
</html>

<%--<form action="/login" method="post">--%>
<%--<div class="form-group row">--%>
<%--<label class="col-sm-2 col-form-label">Email</label>--%>
<%--<div class="col-sm-3">--%>
<%--<input type="text" class="form-control" name="username"/>--%>
<%--</div>--%>
<%--</div>--%>

<%--<div class="form-group row">--%>
<%--<label class="col-sm-2 col-form-label">Password</label>--%>
<%--<div class="col-sm-3">--%>
<%--<input type="text" class="form-control" name="password"/>--%>
<%--</div>--%>
<%--</div>--%>

<%--<div class="form-group">--%>
<%--<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>--%>
<%--</div>--%>

<%--<div class="form-group row">--%>
<%--<div class="col-sm-3">--%>
<%--<button type="submit" class="btn btn-primary">Login</button>--%>
<%--</div>--%>
<%--</div>--%>
<%--</form>--%>