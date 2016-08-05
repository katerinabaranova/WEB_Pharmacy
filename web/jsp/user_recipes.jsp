<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="resource.text" />
<ctg:navigation_menu role="${loggedRole}"/>
<jsp:include page="${pageContext.request.contextPath}/${include_top_menu}"/>
<h1><fmt:message key="user.recipes.header"/> </h1>
<div class="row">
    <div class="col-md-1"><fmt:message key="user.recipes.id"/> </div>
    <div class="col-md-1"><fmt:message key="user.recipes.doctor"/></div>
    <div class="col-md-1"><fmt:message key="user.recipes.medicine"/></div>
    <div class="col-md-2"><fmt:message key="user.recipes.quantity"/></div>
    <div class="col-md-1"><fmt:message key="user.recipes.expired"/></div>
</div>
<c:forEach items="${recipeList}" var="order">
    <div class="row">
        <div class=col-md-1>${recipe.id}</div>
        <div class=col-md-1>${recipe.doctorID}</div>
        <div class=col-md-1>${order.medicineID}</div>
        <div class=col-md-2>${order.medicineQuantity}</div>
        <div class=col-md-1>${order.expired}</div>
    </div>
    <br>
</c:forEach>


<p><a href=# onclick="history.back(); return false;"><fmt:message key="button.back" /></a>

<%@ include file="bottom/bottom.jsp"%>