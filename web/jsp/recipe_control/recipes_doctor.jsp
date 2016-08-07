<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="resource.text" />
<ctg:navigation_menu role="${loggedRole}"/>
<jsp:include page="${pageContext.request.contextPath}/${include_top_menu}"/>
<h2><fmt:message key="all.medicines.header"/> </h2>
<div class="row">
    <div class="col-md-1"><fmt:message key="recipe.id"/> </div>
    <div class="col-md-1"><fmt:message key="recipe.patient.id"/> </div>
    <div class="col-md-1"><fmt:message key="recipe.medicine.name"/></div>
    <div class="col-md-1"><fmt:message key="recipe.medicine.quantity"/></div>
    <div class="col-md-1"><fmt:message key="recipe.expired"/></div>
</div>
<c:forEach items="${recipeList}" var="recipe">
    <div class="row">
        <div class=col-md-1>${recipe.id}</div>
        <div class=col-md-1>${recipe.patientID}</div>
        <div class=col-md-1>${recipe.medicineID}</div>
        <div class=col-md-1>${recipe.medicineQuantity}</div>
        <div class=col-md-1>${recipe.expired}</div>
    </div>
    <br>
</c:forEach>
<p><a href=# onclick="history.back(); return false;"><fmt:message key="button.back" /></a>
    <jsp:include page="${pageContext.request.contextPath}/jsp/bottom/bottom.jsp"/>
