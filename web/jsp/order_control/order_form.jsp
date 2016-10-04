<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="resource.text" />
<ctg:navigation_menu role="${loggedRole}"/>
<jsp:include page="${pageContext.request.contextPath}/${include_top_menu}"/>
<form class="form-horizontal" action="/new_order">
    <fieldset>
        <legend><fmt:message key="new.order.legend"/> </legend>

        <div class="form-group">
            <label class="col-md-4 control-label" for="medicineName"><fmt:message key="search.medicine.label"/> </label>
            <div class="col-md-4">
                <input id="medicineName" name="medicineName" value="${medicine_for_order.medicineName}"  pattern="[A-Za-Z0-9\\-]{4,20}" class="form-control input-md" required="" type="text">
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-4 control-label" for="medicineDosage"><fmt:message key="search.results.dosage"/> </label>
            <div class="col-md-4">
                <input id="medicineDosage" name="medicineDosage" value="${medicine_for_order.dosage}"  pattern="[0-9]{1-7}" class="form-control input-md" required="" type="text">
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-4 control-label" for="price"><fmt:message key="search.results.price"/> </label>
            <div class="col-md-4">
                <input id="price" name="price" value="${medicine_for_order.price}" pattern="[0-9]{1-7}" class="form-control input-md" required="" type="text">
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-4 control-label" for="medicineQuantity"><fmt:message key="recipe.medicine.quantity"/> </label>
            <div class="col-md-4">
                <input id="medicineQuantity" name="medicineQuantity" placeholder="" pattern="[0-9]{1-2}" class="form-control input-md" required="" type="text">
                <span class="help-block"><fmt:message key="order.quantity.help"/> </span>
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-4 control-label" for="delivery"><fmt:message key="user.orders.delivery"/> </label>
            <div class="col-md-2">
                <select id="delivery" name="delivery" class="form-control" >
                    <option value="true" ><fmt:message key="label.yes"/> </option>
                    <option value="false"><fmt:message key="label.no"/></option>
                </select>
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-4 control-label" for="singlebutton"></label>
            <div class="col-md-4">
                <button id="singlebutton" name="singlebutton" class="btn btn-success"><fmt:message key="place.order.button"/> </button>
            </div>
        </div>
        <input type="hidden"  name="command" value="new_order" >
        <input  type="hidden" name="medicine" value="${medicine_for_order.id}">
    </fieldset>
</form>
<jsp:include page="${pageContext.request.contextPath}/jsp/bottom/bottom.jsp"/>
