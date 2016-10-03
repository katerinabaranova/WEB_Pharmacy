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
<form class="form-horizontal" action="/registration" method="post">
    <fieldset>

        <legend><fmt:message key="registration.legend"/></legend>

        <div class="form-group">
            <label class="col-md-4 control-label" for="Login"><fmt:message key="registration.login"/> </label>
            <div class="col-md-4">
                <input id="login" name="Login" pattern="[A-Za-z0-9]{5,15}" placeholder="" class="form-control input-md" required="" type="text">
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-4 control-label" for="Password"><fmt:message key="registration.password"/></label>
            <div class="col-md-4">
                <input id="password" name="Password" pattern="[A-Za-z0-9]{5,15}" placeholder="" class="form-control input-md" required="" type="password">
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-4 control-label" for="ConfirmPassword"><fmt:message key="registration.password"/></label>
            <div class="col-md-4">
                <input id="confirmPassword" name="SubmitPassword"  pattern="[A-Za-z0-9]{5,15}" placeholder="" class="form-control input-md" required="" type="password">
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-4 control-label" for="Name"><fmt:message key="registration.name"/> </label>
            <div class="col-md-4">
                <input id="name" name="Name" pattern="[A-Za-zА-Яа-я]{2,20}" placeholder="" class="form-control input-md" required="" type="text">
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-4 control-label" for="Surname"><fmt:message key="registration.surname"/></label>
            <div class="col-md-4">
                <input id="surname" name="Surname" pattern="[A-Za-zА-Яа-я\\-]{2,20}" placeholder="" class="form-control input-md" type="text">
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-4 control-label" for="City"><fmt:message key="registration.city"/> </label>
            <div class="col-md-4">
                <input id="city" name="City" pattern="[A-Za-zА-Яа-я \\-]{2,20}" placeholder="" class="form-control input-md" required="" type="text">
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-4 control-label" for="Street"><fmt:message key="registration.street"/> </label>
            <div class="col-md-4">
                <input id="street" name="Street" pattern="[A-Za-zА-Яа-я \\-]{2,20}" placeholder="" class="form-control input-md" type="text">
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-4 control-label" for="Housenumber"><fmt:message key="registration.housenumber"/> </label>
            <div class="col-md-4">
                <input id="housenumber" name="Housenumber" pattern="[1-9][0-9]{0,4}" placeholder="" class="form-control input-md" type="text">
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-4 control-label" for="Apartment"><fmt:message key="registration.apartment"/> </label>
            <div class="col-md-4">
                <input id="apartment" name="Apartment" pattern="[1-9][0-9]{0,4}" placeholder="" class="form-control input-md" type="text">
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-4 control-label" for="Email"><fmt:message key="registration.email"/> </label>
            <div class="col-md-4">
                <input id="email" name="Email" pattern="({6,})@(\w+\.)([a-z]{2,4})" placeholder="" class="form-control input-md" required="" type="text">
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-4 control-label" for="Phonenumber"><fmt:message key="registration.phonenumber"/> </label>
            <div class="col-md-4">
                <input id="phonenumber" name="Phonenumber" placeholder="" class="form-control input-md" required="" type="text">
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-4 control-label" for="role-0"><fmt:message key="registration.role.legend"/></label>
            <div class="col-md-4">
                <label class="radio-inline" for="role-0">
                    <input name="role" id="role-0" value="buyer" checked="checked" type="radio">
                    <fmt:message key="registration.role.buyer"/>
                </label>
                <label class="radio-inline" for="role-1">
                    <input name="role" id="role-1" value="doctor" type="radio">
                    <fmt:message key="registration.role.doctor"/>
                </label>
            </div>
        </div>
        <input type="hidden" name="command" value="registration"/>

        <div class="form-group">
            <label class="col-md-4 control-label" for="singlebutton"></label>
            <div class="col-md-4">
                <button id="singlebutton" name="singlebutton" class="btn btn-primary"><fmt:message key="register.button"/> </button>
            </div>
        </div>
    </fieldset>
</form>

<jsp:include page="${pageContext.request.contextPath}/jsp/bottom/bottom.jsp"/>
