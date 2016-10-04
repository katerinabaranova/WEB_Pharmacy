<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="resource.text" />
<ctg:navigation_menu role="${loggedRole}"/>
<jsp:include page="${pageContext.request.contextPath}/${include_top_menu}"/>
<h1><fmt:message key="user.orders.header"/> </h1>
<div class="row">
    <div class="col-md-1"><fmt:message key="user.orders.id"/> </div>
    <div class="col-md-1"><fmt:message key="user.orders.medicine"/></div>
    <div class="col-md-1"><fmt:message key="user.orders.quantity"/></div>
    <div class="col-md-2"><fmt:message key="user.orders.amount"/></div>
    <div class="col-md-1"><fmt:message key="user.orders.delivery"/></div>
</div>
<c:forEach items="${orderList}" var="order">
    <div class="row">
        <div class=col-md-1>${order.id}</div>
        <div class=col-md-1>${order.medicine.medicineName}(${order.medicine.dosage})</div>
        <div class=col-md-1>${order.quantity}</div>
        <div class=col-md-2>${order.totalAmount}</div>
        <div class=col-md-1>${order.delivery}</div>
    </div>
    <br>
</c:forEach>
<jsp:include page="${pageContext.request.contextPath}/jsp/bottom/bottom.jsp"/>
