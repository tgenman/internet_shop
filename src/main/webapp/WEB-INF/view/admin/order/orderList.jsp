<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<c:set var="title" value="Order List"  scope="request"/>
<%@ include file="../../templates/header.jsp" %>

<h2>Order List</h2>

<table class="table table-bordered table-sm">
    <thead>
    <tr>
        <th scope="col">art.</th>
        <th scope="col">username</th>
        <th scope="col">address</th>
        <th scope="col">dateOfOrder</th>
        <th scope="col">typeOfPayment</th>
        <th scope="col">typeOfDelivery</th>
        <th scope="col">statusOfPayment</th>
        <th scope="col">statusOfDelivery</th>
        <%--<th scope="col">listOfProducts</th>--%>
        <th scope="col">Edit</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="orderDto" items="${orderDtos}">
        <tr>
            <td scope="col">${orderDto.id}</td>
            <td scope="col">${orderDto.user.username}</td>
            <td scope="col">${orderDto.address}</td>
            <td scope="col">${orderDto.dateOfOrder}</td>
            <td scope="col">${orderDto.typeOfPayment}</td>
            <td scope="col">${orderDto.typeOfDelivery}</td>
            <td scope="col">${orderDto.statusOfPayment}</td>
            <td scope="col">${orderDto.typeOfDelivery}</td>
            <td scope="col">
                <a class="btn btn-warning btn-sm" href="/admin/order/${userDto.id}" role="button">edit</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>



<%@ include file="../../templates/footer.jsp" %>



