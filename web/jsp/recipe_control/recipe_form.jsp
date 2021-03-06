<%@ page import="com.baranova.pharmacy.constant.ParameterName" %>
<%@ page import="com.baranova.pharmacy.type.PageName" %>
<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="resource.text" />
<ctg:navigation_menu role="${loggedRole}"/>
<jsp:include page="${pageContext.request.contextPath}/${include_top_menu}"/>
<% request.getSession().setAttribute(ParameterName.LAST_PAGE.toString(), PageName.NEW_RECIPE_FORM);%>

<form class="form-horizontal" action="${pageContext.request.contextPath}/new_recipe">
    <fieldset>
        <legend><fmt:message key="new.recipe.legend" /></legend>
        <div class="form-group">
            <label class="col-md-4 control-label" for="patientSurname"><fmt:message key="recipe.patient.surname"/></label>
            <div class="col-md-4">
                <input id="patientSurname" name="patientSurname" pattern="[A-Za-zА-Яа-я]{2,20}" placeholder="" class="form-control input-md" required="" type="text">
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-4 control-label" for="patientName"><fmt:message key="recipe.patient.name"/> </label>
            <div class="col-md-4">
                <input id="patientName" name="patientName" pattern="[A-Za-zА-Яа-я]{2,20}" placeholder="" class="form-control input-md" required="" type="text">
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-4 control-label" for="medicineName"><fmt:message key="recipe.medicine.name"/></label>
            <div class="col-md-4">
                <input id="medicineName" name="medicineName" placeholder="" pattern="[A-Za-z0-9 -]{4-20}" class="form-control input-md" required="" type="text">
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-4 control-label" for="medicineDosage"><fmt:message key="recipe.medicine.dosage"/> </label>
            <div class="col-md-4">
                <input id="medicineDosage" name="medicineDosage" pattern="[1-9][0-9]{0,6}" placeholder="" class="form-control input-md" required="" type="text">
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-4 control-label" for="medicineQuantity"><fmt:message key="recipe.medicine.quantity"/></label>
            <div class="col-md-4">
                <input id="medicineQuantity" name="medicineQuantity" pattern="[1-9]{1}" placeholder="" class="form-control input-md" required="" type="text">
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-4 control-label" for="singlebutton"></label>
            <div class="col-md-4">
                <button id="singlebutton" name="singlebutton" class="btn btn-success"><fmt:message key="add.recipe.button"/> </button>
            </div>
        </div>
        <input type="hidden" name="command" value="new_recipe">
        <input type="hidden" name="doctorId" value="${loggedId}">
    </fieldset>
</form>

<jsp:include page="${pageContext.request.contextPath}/jsp/bottom/bottom.jsp"/>
