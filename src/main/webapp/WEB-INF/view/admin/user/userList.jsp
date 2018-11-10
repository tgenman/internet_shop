<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<c:set var="title" value="Product List"  scope="request"/>
<%@ include file="../../templates/header.jsp" %>

<h2>User List</h2>

<a class="btn btn-success" href="/product/new" role="button">Create new product</a>

<table class="table table-bordered table-sm">
    <thead>
    <tr>
        <th scope="col">art.</th>
        <th scope="col">Title</th>
        <th scope="col">Price</th>
        <th scope="col">Weight</th>
        <th scope="col">Volume</th>
        <th scope="col">Quantity</th>
        <th scope="col">Edit</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="category" items="${productDtos}">
        <tr>
            <td colspan="7" class="table-info">${category.key}</td>
        </tr>

        <c:forEach var="productDto" items="${category.value}">
            <tr>
                <td scope="col">${productDto.id}</td>
                <td scope="col">${productDto.title}</td>
                <td scope="col">${productDto.price}</td>
                <td scope="col">${productDto.weight}</td>
                <td scope="col">${productDto.volume}</td>
                <td scope="col">${productDto.quantity}</td>
                <td scope="col">
                    <a class="btn btn-warning btn-sm" href="admin/product/${productDto.id}" role="button">edit</a>
                </td>
            </tr>
        </c:forEach>
    </c:forEach>
    </tbody>
</table>



<%@ include file="../../templates/footer.jsp" %>




        <c:forEach var="userDto" items="${userDtos}">
            <tr>
                <td>${userDto.username}</td>
                <td>
                    <c:forEach var="role" items="${userDto.roles}">${role}, </c:forEach>
                </td>
                <td><a href="/user/${userDto.id}">edit</a></td>
            </tr>
        </c:forEach>


