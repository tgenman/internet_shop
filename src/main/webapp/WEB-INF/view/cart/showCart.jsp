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
                        <input type="submit" class="btn btn-warning btn-sm" value="Remove">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>
<a class="btn btn-success" href="/order/new" role="button">Create Order</a>


<%@ include file="../templates/footer.jsp" %>