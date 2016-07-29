<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="resource.text" />
<ctg:navigation_menu role="${loggedRole}"/>
<jsp:include page="${pageContext.request.contextPath}/${include_page}"/>
<h1>Orders</h1>
<div class="row">
    <div class="col-md-1"><fmt:message key="user.orders.id"/> </div>
    <div class="col-md-1"><fmt:message key="user.orders.medicine"/></div>
    <div class="col-md-1"><fmt:message key="user.orders.quantity"/></div>
    <div class="col-md-2"><fmt:message key="user.orders.amount"/></div>
    <div class="col-md-1"><fmt:message key="user.orders.delivery"/></div>
    <div class="col-md-1"><fmt:message key="user.orders.paid"/></div>
</div>
<c:forEach items="${orderList}" var="order">
    <div class="row">
        <div class=col-md-1>${order.id}</div>
        <div class=col-md-1>${order.fkMedicineID}</div>
        <div class=col-md-1>${order.quantity}</div>
        <div class=col-md-2>${order.totalAmount}</div>
        <div class=col-md-1>${order.paid}</div>
        <div class=col-md-1>${order.delivery}</div>
    </div>
    <br>
</c:forEach>

<p><a href=# onclick="history.back(); return false;"><fmt:message key="buttom.back" /></a>

<%@ include file="bottom.jsp"%>