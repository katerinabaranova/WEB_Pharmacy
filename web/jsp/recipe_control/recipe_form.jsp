<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="resource.text" />
<ctg:navigation_menu role="${loggedRole}"/>
<jsp:include page="${pageContext.request.contextPath}/${include_top_menu}"/>

<form class="form-horizontal">
    <fieldset>
        <legend><fmt:message key="new.recipe.legend" /></legend>
        <div class="form-group">
            <label class="col-md-4 control-label" for="patientSurname"><fmt:message key="recipe.patient.surname"/></label>
            <div class="col-md-4">
                <input id="patientSurname" name="patientSurname" placeholder="" class="form-control input-md" required="" type="text">
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-4 control-label" for="patientName"><fmt:message key="recipe.patient.name"/> </label>
            <div class="col-md-4">
                <input id="patientName" name="patientName" placeholder="" class="form-control input-md" required="" type="text">
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-4 control-label" for="medicineName"><fmt:message key="recipe.medicine.name"/></label>
            <div class="col-md-4">
                <input id="medicineName" name="medicineName" placeholder="" class="form-control input-md" required="" type="text">
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-4 control-label" for="medicineDosage"><fmt:message key="recipe.medicine.dosage"/> </label>
            <div class="col-md-4">
                <input id="medicineDosage" name="medicineDosage" placeholder="" class="form-control input-md" required="" type="text">
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-4 control-label" for="medicineQuantity"><fmt:message key="recipe.medicine.quantity"/></label>
            <div class="col-md-4">
                <input id="medicineQuantity" name="medicineQuantity" placeholder="" class="form-control input-md" required="" type="text">
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-4 control-label" for="singlebutton"></label>
            <div class="col-md-4">
                <button id="singlebutton" name="singlebutton" class="btn btn-success"><fmt:message key="add.recipe.button"/> </button>
            </div>
        </div>
        <input type="hidden" name="command" value="new_recipe">
    </fieldset>
</form>

<jsp:include page="${pageContext.request.contextPath}/jsp/bottom/bottom.jsp"/>
