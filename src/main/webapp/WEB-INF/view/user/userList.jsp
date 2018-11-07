<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<c:set var="title" value="List of Users"  scope="session"/>
<c:set var="title2" value="List of Users"  scope="session"/>
<c:import url="../templates/header.jsp" />


List of users

<table>
    <thead>
        <tr>
            <th>Name</th>
            <th>Role</th>
            <th></th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="userDto" items="${userDtos}">
            <tr>
                <td>${userDto.username}</td>
                <td>
                    <c:forEach var="role" items="${userDto.roles}">${role}, </c:forEach>
                </td>
                <td><a href="/user/${userDto.id}">edit</a></td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<c:import url="../templates/footer.jsp" />

