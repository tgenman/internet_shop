<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<c:set var="title" value="New Order"  scope="request"/>
<%@ include file="../templates/header.jsp" %>

<h2>Creation Order</h2>
<h4>Your order:</h4>

<table class="table table-bordered table-sm">
    <thead>
    <tr>
        <th scope="col">art.</th>
        <th scope="col">Title</th>
        <th scope="col">Price</th>
        <th scope="col">Weight</th>
        <th scope="col">Volume</th>
        <th scope="col">Quantity</th>
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
                <td scope="col">${product.value}</td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<form:form action="/order/new" method="post" modelAttribute="orderDto">
    <h4>Choose your address:</h4>
    <div class="form-group row">
        <div class="col-sm-10">
            <ul>
                <form:radiobuttons element="li" path="address" items="${addresses}"/>
            </ul>
        </div>
    </div>


    <h4>Choose type of payment:</h4>
    <div class="form-group row">
        <div class="col-sm-3">
            <ul>
                <form:radiobuttons element="li" path="typeOfPayment" items="${TypeOfPaymentL}"  />
            </ul>
        </div>
    </div>

    <h4>Choose type of delivery:</h4>
    <div class="form-group row">
        <div class="col-sm-3">
            <ul>
                <form:radiobuttons element="li" path="typeOfDelivery" items="${TypeOfDeliveryL}"  />
            </ul>
        </div>
    </div>

    <input type="hidden" value="${user.id}" name="userId">
    <input type="hidden" value="${_csrf.token}" name="_csrf">

    <div class="form-group row">
        <div class="col-sm-3">
            <button type="submit" class="btn btn-success btn-lg">Save</button>
        </div>
    </div>
</form:form>



<%--<div class="form-group row">--%>
    <%--<form:radiobuttons path="address" items="${addresses}"  />--%>
<%--</div>--%>



<%@ include file="../templates/footer.jsp" %>