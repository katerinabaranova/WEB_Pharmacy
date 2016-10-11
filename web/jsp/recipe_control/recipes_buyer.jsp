<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="resource.text" />
<ctg:navigation_menu role="${loggedRole}"/>
<jsp:include page="${pageContext.request.contextPath}/${include_top_menu}"/>
<h2><fmt:message key="doctor.recipe.header"/> </h2>
<div class="row">
    <div class="col-md-1"><fmt:message key="recipe.id"/> </div>
    <div class="col-md-1"><fmt:message key="recipe.date"/> </div>
    <div class="col-md-2"><fmt:message key="user.recipes.doctor"/> </div>
    <div class="col-md-1"><fmt:message key="recipe.medicine.name"/></div>
    <div class="col-md-1"><fmt:message key="recipe.medicine.dosage"/></div>
    <div class="col-md-1"><fmt:message key="recipe.medicine.quantity"/></div>
    <div class="col-md-1"><fmt:message key="recipe.expired"/></div>
</div>
<c:forEach items="${recipeList}" var="recipe">
    <div class="row">
        <div class="col-md-1">${recipe.id}</div>
        <div class="col-md-1">${recipe.date}</div>
        <div class="col-md-2">${recipe.doctor.surname}</div>
        <div class="col-md-1">${recipe.medicine.medicineName}</div>
        <div class="col-md-1">${recipe.medicine.dosage}</div>
        <div class="col-md-1">${recipe.medicineQuantity}</div>
        <div class="col-md-1">${recipe.expired}</div>
        <form action="${pageContext.request.contextPath}/renew_recipe_request" method="post">
            <div class="form-group">
                <div class="col-md-2">
                    <input type="hidden" name="command" value="renew_recipe_request" />
                    <input type="hidden" name="recipeId" value="${recipe.id}">
                    <button type="submit" class="btn btn-warning"><fmt:message key="renew.request.button"/> </button>
                </div>
            </div>
        </form>
    </div>
    <br>
</c:forEach>
<jsp:include page="${pageContext.request.contextPath}/jsp/bottom/bottom.jsp"/>
