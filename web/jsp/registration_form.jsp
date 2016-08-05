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
<% request.getSession().setAttribute(ParameterName.LAST_PAGE.toString(), PageName.REGISTRATION_FORM);%>
<form class="form-horizontal" action="../registration" method="post">
    <fieldset>

        <legend><fmt:message key="registration.legend"/></legend>

        <div class="form-group">
            <label class="col-md-4 control-label" for="Login"><fmt:message key="registration.login"/> </label>
            <div class="col-md-4">
                <input id="Login" name="Login" placeholder="" class="form-control input-md" required="" type="text">
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-4 control-label" for="Password"><fmt:message key="registration.password"/></label>
            <div class="col-md-4">
                <input id="Password" name="Password" placeholder="" class="form-control input-md" required="" type="password">
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-4 control-label" for="Name"><fmt:message key="registration.name"/> </label>
            <div class="col-md-4">
                <input id="Name" name="Name" placeholder="" class="form-control input-md" required="" type="text">

            </div>
        </div>

        <div class="form-group">
            <label class="col-md-4 control-label" for="Surname"><fmt:message key="registration.surname"/></label>
            <div class="col-md-4">
                <input id="Surname" name="Surname" placeholder="" class="form-control input-md" type="text">

            </div>
        </div>

        <div class="form-group">
            <label class="col-md-4 control-label" for="City"><fmt:message key="registration.city"/> </label>
            <div class="col-md-4">
                <input id="City" name="City" placeholder="" class="form-control input-md" required="" type="text">
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-4 control-label" for="Street"><fmt:message key="registration.street"/> </label>
            <div class="col-md-4">
                <input id="Street" name="Street" placeholder="" class="form-control input-md" type="text">
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-4 control-label" for="Housenumber"><fmt:message key="registration.housenumber"/> </label>
            <div class="col-md-4">
                <input id="Housenumber" name="Housenumber" placeholder="" class="form-control input-md" type="text">

            </div>
        </div>

        <div class="form-group">
            <label class="col-md-4 control-label" for="Apartment"><fmt:message key="registration.apartment"/> </label>
            <div class="col-md-4">
                <input id="Apartment" name="Apartment" placeholder="" class="form-control input-md" type="text">

            </div>
        </div>

        <div class="form-group">
            <label class="col-md-4 control-label" for="Email"><fmt:message key="registration.email"/> </label>
            <div class="col-md-4">
                <input id="Email" name="Email" placeholder="" class="form-control input-md" required="" type="text">

            </div>
        </div>

        <div class="form-group">
            <label class="col-md-4 control-label" for="Phonenumber"><fmt:message key="registration.phonenumber"/> </label>
            <div class="col-md-4">
                <input id="Phonenumber" name="Phonenumber" placeholder="" class="form-control input-md" required="" type="text">

            </div>
        </div>

        <div class="form-group">
            <label class="col-md-4 control-label" for="role-0"><fmt:message key="registration.role.legend"/></label>
            <div class="col-md-4">
                <label class="radio-inline" for="role-0">
                    <input name="Role" id="role-0" value="buyer" checked="checked" type="radio">
                    <fmt:message key="registration.role.buyer"/>
                </label>
                <label class="radio-inline" for="role-1">
                    <input name="Role" id="role-1" value="doctor" type="radio">
                    <fmt:message key="registration.role.doctor"/>
                </label>
            </div>
        </div>
        <input type="hidden" name="command" value="registration" />

        <div class="form-group">
            <label class="col-md-4 control-label" for="singlebutton"></label>
            <div class="col-md-4">
                <button id="singlebutton" name="singlebutton" class="btn btn-primary"><fmt:message key="register.button"/> </button>
            </div>
        </div>

    </fieldset>
</form>

<p><a href=# onclick="history.back(); return false;"><fmt:message key="button.back" /></a>
<%@include file="bottom/bottom.jsp" %>
