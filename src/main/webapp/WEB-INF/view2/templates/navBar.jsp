<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

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
                <a class="nav-link" href="/product/all">Products</a>
            </li>

            <li class="nav-item">
                <a class="nav-link" href="/cart">Cart</a>
            </li>

            <li class="nav-item">
                <a class="nav-link" href="/store/inventory">Inventory</a>
            </li>

            <li class="nav-item">
                <a class="nav-link" href="/store/order/all">Orders</a>
            </li>

            <li class="nav-item">
                <a class="nav-link" href="/user/all">User List</a>
            </li>
        </ul>

        <div class="navbar-text mr-3">Username</div>

        <form:form method="GET" action="/login" class="form-inline">
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Login</button>
        </form:form>
        <form:form method="GET" action="/user/registration" class="form-inline">
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Registration</button>
        </form:form>
        <form:form method="POST" action="/logout" class="form-inline">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Logout</button>
        </form:form>
    </div>
</nav>
