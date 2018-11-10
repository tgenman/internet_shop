<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>


<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="/">Shop</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse"
            data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
            aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">

            <li class="nav-item">
                <a class="nav-link" href="/">Home</a>
            </li>

            <li class="nav-item">
                <a class="nav-link" href="/product">Market</a>
            </li>

            <li class="nav-item">
                <a class="nav-link" href="/cart">Cart</a>
            </li>

            <%--<sec:authorize access="hasRole('ADMIN')">--%>
            <li class="nav-item">
                <a class="nav-link" href="/admin/product">Product List</a>
            </li>

            <li class="nav-item">
                <a class="nav-link" href="/admin/order">Order List</a>
            </li>

            <li class="nav-item">
                <a class="nav-link" href="/admin/user">User List</a>
            </li>
            <%--</sec:authorize>--%>
        </ul>

        <div class="navbar-text mr-3">
            <a class="nav-link" href="/user">
                <security:authorize access="isAuthenticated()">
                    <security:authentication property="principal.username" />
                </security:authorize>
                <security:authorize access="!isAuthenticated()">
                    Guest
                </security:authorize>
            </a>
        </div>

        <form:form method="GET" action="/login" class="form-inline">
            <button class="btn btn-primary mr-2 my-2 my-sm-0" type="submit">Login</button>
        </form:form>
        <form:form method="GET" action="/user/registration" class="form-inline">
            <button class="btn btn-primary  mr-2 my-2 my-sm-0" type="submit">Registration</button>
        </form:form>
        <form:form method="POST" action="/logout" class="form-inline">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <button class="btn btn-secondary my-2 my-sm-0" type="submit">Logout</button>
        </form:form>
    </div>
</nav>
