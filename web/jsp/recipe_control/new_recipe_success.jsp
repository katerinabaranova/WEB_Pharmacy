<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<c:set var="recipe" value="${recipe}"/>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="resource.text" />
<ctg:navigation_menu role="${loggedRole}"/>
<jsp:include page="${pageContext.request.contextPath}/${include_top_menu}"/>
<h3><fmt:message key="new.recipe.success"/></h3>
<p><fmt:message key="recipe.patient.surname"/>: ${recipe.patient.surname}</p>
<p><fmt:message key="recipe.patient.name"/>: ${recipe.patient.name}</p>
<p><fmt:message key="recipe.medicine.name"/>: ${recipe.medicine.medicineName}</p>
<p><fmt:message key="recipe.medicine.dosage"/>: ${recipe.medicine.dosage}</p>
<p><fmt:message key="recipe.medicine.quantity"/>: ${recipe.medicineQuantity}</p>
<jsp:include page="${pageContext.request.contextPath}/jsp/bottom/bottom.jsp"/>
