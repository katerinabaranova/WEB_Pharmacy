<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="resource.text" />
<ctg:navigation_menu role="${loggedRole}"/>
<jsp:include page="${pageContext.request.contextPath}/${include_top_menu}"/>
<h2><fmt:message key="search.results.header"/> </h2>
<div class="row">
    <div class="col-md-2"><fmt:message key="search.results.name"/> </div>
    <div class="col-md-1"><fmt:message key="search.results.dosage"/></div>
    <div class="col-md-1"><fmt:message key="search.results.package"/></div>
    <div class="col-md-2"><fmt:message key="search.results.quantity"/></div>
    <div class="col-md-1"><fmt:message key="search.results.price"/></div>
    <div class="col-md-1"><fmt:message key="search.results.instore"/></div>
    <div class="col-md-2"><fmt:message key="search.results.recipe"/></div>
</div>
<c:forEach items="${medicineList}" var="medicine">
    <div class="row">
        <div class=col-md-2>${medicine.medicineName}</div>
        <div class=col-md-1>${medicine.dosage}</div>
        <div class=col-md-1>${medicine.packageType}</div>
        <div class=col-md-2>${medicine.packageQuantity}</div>
        <div class=col-md-1>${medicine.price}</div>
        <div class=col-md-1>${medicine.storeQuantity}</div>
        <div class=col-md-2>${medicine.recipe}</div>
        <form action="/prepare_order" method="post">
            <input type="hidden" name="command" value="prepare_order" />
            <input type="hidden" name="medicine" value="${medicine.id}">
            <button type="submit" class="btn btn-success"><fmt:message key="search.results.button"/> </button>
        </form>
    </div>
    <br>
</c:forEach>
<jsp:include page="${pageContext.request.contextPath}/jsp/bottom/bottom.jsp"/>
