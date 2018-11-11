<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<c:set var="title" value="Market"  scope="request"/>
<%@ include file="../templates/header.jsp" %>

<h2>Market</h2>

<div class="card-columns">
    <c:forEach var="category" items="${productDtos}">
        <c:forEach var="productDto" items="${category.value}">
            <div class="card my-3">
                <img class="card-img-top" src=".../100px180/?text=Image cap">
                <div class="card-body">
                    <h5 class="card-title">${productDto.title}</h5>
                    <p class="card-text">${categoryDto.key}</p>
                </div>
                <ul class="list-group list-group-flush">
                    <li class="list-group-item">Price:    ${productDto.price}</li>
                </ul>
                <div class="card-body">
                    <a class="btn btn-info " href="/product/${productDto.id}" role="button">More...</a>

                    <form action="/cart/${productDto.id}}" method="post">
                        <input type="hidden" id="csrfToken" value="${_csrf.token}"/>
                        <input type="hidden" id="csrfHeader" value="${_csrf.headerName}"/>
                        <input type="submit" value="Add to Cart">
                    </form>
                </div>
            </div>
        </c:forEach>
    </c:forEach>
</div>

<%@ include file="../templates/footer.jsp" %>