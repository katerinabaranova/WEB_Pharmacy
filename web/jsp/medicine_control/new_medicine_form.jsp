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
<% request.getSession().setAttribute(ParameterName.LAST_PAGE.toString(), PageName.NEW_MEDICINE_FORM);%>
<form class="form-horizontal">
    <fieldset>
        <legend><fmt:message key="adding.new.medicine"/> </legend>
        <div class="form-group">
            <label class="col-md-4 control-label" for="medicineName"><fmt:message key="search.medicine.label"/></label>
            <div class="col-md-4">
                <input id="medicineName" name="medicineName" placeholder="" class="form-control input-md"  pattern="[A-Za-Z0-9\\-]{4,20}" required="" type="text">
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-4 control-label" for="medicineDosage"><fmt:message key="search.results.dosage"/> </label>
            <div class="col-md-4">
                <input id="medicineDosage" name="medicineDosage" placeholder="" class="form-control input-md" pattern="[0-9]{1-7}" required="" type="text">
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-4 control-label" for="medicinePackage"><fmt:message key="search.results.package"/> </label>
            <div class="col-md-4">
                <input id="medicinePackage" name="medicinePackage" placeholder="" class="form-control input-md" required="" type="text">
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-4 control-label" for="packQuantity"><fmt:message key="search.results.quantity"/> </label>
            <div class="col-md-2">
                <input id="packQuantity" name="packQuantity" placeholder="" class="form-control input-md" pattern="[0-9]{1-4}" required="" type="text">
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-4 control-label" for="price"><fmt:message key="search.results.price"/> </label>
            <div class="col-md-2">
                <input id="price" name="price" placeholder="" class="form-control input-md" pattern="[0-9]{1-7}"required="" type="text">
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-4 control-label" for="instoreQuantity"><fmt:message key="search.results.instore"/> </label>
            <div class="col-md-2">
                <input id="instoreQuantity" name="instoreQuantity" placeholder="" class="form-control input-md" pattern="[0-9]{1-4}" required="" type="text">
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-4 control-label" for="pecipe"><fmt:message key="need.recipe"/> </label>
            <div class="col-md-2">
                <select id="pecipe" name="pecipe" class="form-control">
                    <option value="1"><fmt:message key="label.yes"/> </option>
                    <option value="2"><fmt:message key="label.no"/></option>
                </select>
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-4 control-label" for="singlebutton"></label>
            <div class="col-md-4">
                <button id="singlebutton" name="singlebutton" class="btn btn-info"><fmt:message key="add.medicine.button"/> </button>
            </div>
        </div>
        <input type="hidden" name="command" value="new_medicine" />
    </fieldset>
</form>

    <br>
<p><a href=# onclick="history.back(); return false;"><fmt:message key="button.back" /></a>
    <jsp:include page="${pageContext.request.contextPath}/bottom/bottom.jsp"/>
