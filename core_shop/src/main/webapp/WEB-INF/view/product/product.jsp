<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<c:set var="title" value="Product Info"  scope="request"/>
<%@ include file="../templates/header.jsp" %>

<h5>Product Info</h5>

<div class="card-columns">
    <div class="card my-3">
        <img class="card-img-top" src=".../100px180/?text=Image cap">
        <div class="card-body">
            <h5 class="card-title">${productDto.title}</h5>
            <p class="card-text">${productDto.category}</p>
        </div>
        <ul class="list-group list-group-flush">
            <li class="list-group-item">Price:    ${productDto.price}</li>
            <li class="list-group-item">Weight:    ${productDto.weight}</li>
            <li class="list-group-item">Volume:    ${productDto.volume}</li>
            <li class="list-group-item">Quantity:    ${productDto.quantity}</li>
        </ul>
        <div class="card-body">
            <a class="btn btn-danger " href="/cart/${productDto.id}" role="button">Add to Cart</a>
        </div>
    </div>
</div>

<%@ include file="../templates/footer.jsp" %>