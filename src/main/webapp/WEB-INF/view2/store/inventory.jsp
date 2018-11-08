<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<c:set var="title" value="Inventory"  scope="request"/>
<%@ include file="../templates/header.jsp" %>

<h2>Inventory</h2>

<a class="btn btn-primary" href="/product/new" role="button">Create new product</a>

<table class="table table-bordered">
    <thead>
        <tr>
            <th scope="col">art.</th>
            <th scope="col">Title</th>
            <th scope="col">Price</th>
            <th scope="col">Weight</th>
            <th scope="col">Volume</th>
            <th scope="col">Volume</th>
            <th scope="col">Quantity</th>
        </tr>
    </thead>
    <tbody>
    <c:forEach var="category" items="${productDtos}">
        <tr>
            <td colspan="7" class="table-info">${category.key}</td>
        </tr>

        <c:forEach var="product" items="${category.value}">
            <tr>
                <td scope="col">${product.id}</td>
                <td scope="col">${product.title}</td>
                <td scope="col">${product.price}</td>
                <td scope="col">${product.weight}</td>
                <td scope="col">${product.volume}</td>
                <td scope="col">${product.quantity}</td>
                <td scope="col"><a href="/product/${product.id}/edit">edit</a></td>
            </tr>
        </c:forEach>
    </c:forEach>
    </tbody>
</table>



<%@ include file="../templates/footer.jsp" %>