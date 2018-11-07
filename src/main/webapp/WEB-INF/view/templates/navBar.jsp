<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="/">Shop</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/registration">Registration </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/login">Login </a>
            </li>
            <security:authorize access="hasRole('USER')">
                <li class="nav-item">
                    <a class="nav-link" href="/user">User List </a>
                </li>
            </security:authorize>
            <li class="nav-item">
                <%@ include file="logout_form.jsp" %>
            </li>
        </ul>
    </div>
</nav>