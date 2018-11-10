<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>



<c:set var="title" value="User Editor"  scope="request"/>
<c:import url="../../templates/header.jsp" />



<form action="/user" method="post">
    <div class="form-group">
        <input type="text" name="username" value="${userDto.username}">
    </div>
    <div class="form-group">
        <input type="text" name="name" value="${userDto.name}">
    </div>
    <div class="form-group">
        <input type="text" name="familyName" value="${userDto.familyName}">
    </div>




        <%--<input type="checkbox" firstName="${role}" ${user.roles?seq_contains(role)?string("checked", "")}>${role}--%>

    <c:forEach var="role" items="${roles}">
        <div>
            <label>
                <input type="checkbox" name="${role}"
                <c:if test="${fn:contains(userDto.roles, role)}">
                    "checked"
                </c:if>
                <c:if test="${not fn:contains(userDto.roles, role)}">
                    ""
                </c:if>>
                    ${role}
            </label>
        </div>

    </c:forEach>



    <div class="form-group">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </div>
    <div class="form-group">
        <button type="submit">Save</button>
    </div>
</form>


<%--<select id="roles" firstName="roles">--%>
    <%--<#list roles as role>--%>
    <%--<option value="${role}" selected="selected">${role}</option>--%>
<%--</#list>--%>
<%--</select>﻿--%>


<c:import url="../../templates/footer.jsp" />