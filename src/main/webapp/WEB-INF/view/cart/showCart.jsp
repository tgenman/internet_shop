<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"     uri="http://www.springframework.org/tags/form" %>


<c:set var="title" value="Cart"  scope="request"/>
<%@ include file="../templates/header.jsp" %>

<h2>Cart</h2>

<table class="table table-bordered table-sm">
    <thead>
    <tr>
        <th scope="col">art.</th>
        <th scope="col">Title</th>
        <th scope="col">Price</th>
        <th scope="col">Weight</th>
        <th scope="col">Volume</th>
        <th scope="col">Quantity</th>
        <th scope="col">Remove</th>
    </tr>
    </thead>
    <tbody>
        <c:forEach var="product" items="${cart}">
            <tr>
                <td scope="col">${product.key.id}</td>
                <td scope="col">${product.key.title}</td>
                <td scope="col">${product.key.price}</td>
                <td scope="col">${product.key.weight}</td>
                <td scope="col">${product.key.volume}</td>
                <td scope="col">${product.value}
                </td>
                <td scope="col">
                    <form action="/cart/delete/${product.key.id}" method="post">
                        <input type="hidden" id="csrfToken" value="${_csrf.token}"/>
                        <input type="hidden" id="csrfHeader" value="${_csrf.headerName}"/>
                        <input type="submit" value="Remove">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>






<%--<div class="card-columns">--%>
    <%--<c:forEach var="product" items="${products}">--%>
        <%--<c:forEach var="amount" items="${product.value}">--%>
            <%--<div class="card my-3">--%>
                <%--<img class="card-img-top" src=".../100px180/?text=Image cap">--%>
                <%--<div class="card-body">--%>
                    <%--<h5 class="card-title">${product.key.title}</h5>--%>
                    <%--<p class="card-text">${product.key.category}</p>--%>
                <%--</div>--%>
                <%--<ul class="list-group list-group-flush">--%>
                    <%--<li class="list-group-item">Price:    ${productDto.price}</li>--%>
                <%--</ul>--%>
                <%--<div class="card-body">--%>
                    <%--<a class="btn btn-info " href="/product/${productDto.id}" role="button">More...</a>--%>
                    <%--<a class="btn btn-danger " href="/cart/${productDto.id}" role="button">Add to Cart</a>--%>
                <%--</div>--%>
            <%--</div>--%>
        <%--</c:forEach>--%>
    <%--</c:forEach>--%>
<%--</div>--%>

<%@ include file="../templates/footer.jsp" %>