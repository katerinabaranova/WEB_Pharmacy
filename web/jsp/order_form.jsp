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
<jsp:include page="${pageContext.request.contextPath}/${include_page}"/>
<form class="form-horizontal">
    <fieldset>
${medicine_for_order}
        <!-- Form Name -->
        <legend>Form Name</legend>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="medicineName">Medicine name</label>
            <div class="col-md-4">
                <input id="medicineName" name="medicineName" placeholder="" class="form-control input-md" required="" type="text">

            </div>
        </div>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="medicineQuantity">Quantity</label>
            <div class="col-md-4">
                <input id="medicineQuantity" name="medicineQuantity" placeholder="" class="form-control input-md" required="" type="text">
                <span class="help-block">write neccessary quantity of pack</span>
            </div>
        </div>

        <!-- Multiple Radios -->
        <div class="form-group">
            <label class="col-md-4 control-label" for="deliveryRadios">Delivery</label>
            <div class="col-md-4">
                <div class="radio">
                    <label for="deliveryRadios-0">
                        <input name="deliveryRadios" id="deliveryRadios-0" value="1" checked="checked" type="radio">
                        Yes
                    </label>
                </div>
                <div class="radio">
                    <label for="deliveryRadios-1">
                        <input name="deliveryRadios" id="deliveryRadios-1" value="2" type="radio">
                        No
                    </label>
                </div>
            </div>
        </div>

        <!-- Button -->
        <div class="form-group">
            <label class="col-md-4 control-label" for="singlebutton"></label>
            <div class="col-md-4">
                <button id="singlebutton" name="singlebutton" class="btn btn-success">Place order</button>
            </div>
        </div>

    </fieldset>
</form>

<p><a href=# onclick="history.back(); return false;"><fmt:message key="button.back" /></a>

    <%@ include file="bottom/bottom.jsp"%>
