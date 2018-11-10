<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<c:set var="title" value="Product List"  scope="request"/>
<%@ include file="../../templates/header.jsp" %>

<h2>User List</h2>

<table class="table table-bordered table-sm">
    <thead>
    <tr>
        <th scope="col">art.</th>
        <th scope="col">username</th>
        <th scope="col">roles</th>
        <th scope="col">email</th>
        <th scope="col">active</th>
        <th scope="col">firstName</th>
        <th scope="col">lastName</th>
        <th scope="col">dateOfBirth</th>
        <th scope="col">Edit</th>
    </tr>
    </thead>
    <tbody>
        <c:forEach var="userDto" items="${userDtos}">
            <tr>
                <td scope="col">${userDto.id}</td>
                <td scope="col">${userDto.username}</td>
                <td scope="col">
                    <c:forEach var="role" items="${userDto.roles}">
                        ${role},
                    </c:forEach>
                </td>
                <td scope="col">${userDto.email}</td>
                <td scope="col">${userDto.active}</td>
                <td scope="col">${userDto.firstName}</td>
                <td scope="col">${userDto.lastName}</td>
                <td scope="col">${userDto.dateOfBirth}</td>
                <td scope="col">
                    <a class="btn btn-warning btn-sm" href="/admin/user/${userDto.id}" role="button">edit</a>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>



<%@ include file="../../templates/footer.jsp" %>



