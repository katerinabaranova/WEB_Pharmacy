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
<form class="form-horizontal" action="${pageContext.request.contextPath}/registration" method="post">
    <fieldset>

        <legend><fmt:message key="registration.legend"/></legend>

        <div class="form-group">
            <label class="col-md-4 control-label" for="login"><fmt:message key="registration.login"/> </label>
            <div class="col-md-4">
                <input id="login" name="login" pattern="[A-Za-z0-9]{5,15}" placeholder="" class="form-control input-md" required="" type="text">
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-4 control-label" for="password"><fmt:message key="registration.password"/></label>
            <div class="col-md-4">
                <input id="password" name="password" pattern="[A-Za-z0-9]{5,15}" placeholder="" class="form-control input-md" required="" type="password">
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-4 control-label" for="confirmPassword"><fmt:message key="registration.password"/></label>
            <div class="col-md-4">
                <input id="confirmPassword" name="confirmPassword"  pattern="[A-Za-z0-9]{5,15}" placeholder="" class="form-control input-md" required="" type="password">
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-4 control-label" for="name"><fmt:message key="registration.name"/> </label>
            <div class="col-md-4">
                <input id="name" name="name" pattern="[A-Za-zА-Яа-я]{2,20}" placeholder="" class="form-control input-md" required="" type="text">
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-4 control-label" for="surname"><fmt:message key="registration.surname"/></label>
            <div class="col-md-4">
                <input id="surname" name="surname" pattern="[A-Za-zА-Яа-я\\-]{2,20}" placeholder="" class="form-control input-md" type="text">
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-4 control-label" for="city"><fmt:message key="registration.city"/> </label>
            <div class="col-md-4">
                <input id="city" name="city" pattern="[A-Za-zА-Яа-я \\-]{2,20}" placeholder="" class="form-control input-md" required="" type="text">
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-4 control-label" for="street"><fmt:message key="registration.street"/> </label>
            <div class="col-md-4">
                <input id="street" name="street" pattern="[A-Za-zА-Яа-я \\-]{2,20}" placeholder="" class="form-control input-md" type="text">
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-4 control-label" for="housenumber"><fmt:message key="registration.housenumber"/> </label>
            <div class="col-md-4">
                <input id="housenumber" name="housenumber" pattern="[1-9]{1}[0-9]{0,4}" placeholder="" class="form-control input-md" type="text">
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-4 control-label" for="apartment"><fmt:message key="registration.apartment"/> </label>
            <div class="col-md-4">
                <input id="apartment" name="apartment" pattern="[1-9]{1}[0-9]{0,4}" placeholder="" class="form-control input-md" type="text">
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-4 control-label" for="email"><fmt:message key="registration.email"/> </label>
            <div class="col-md-4">
                <input id="email" name="email" pattern="([\w-\.]+)@(\w+\.)([a-z]{2,4})" placeholder="" class="form-control input-md" required="" type="text">
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-4 control-label" for="phonenumber"><fmt:message key="registration.phonenumber"/> </label>
            <div class="col-md-4">
                <input id="phonenumber" name="phonenumber" placeholder="Example 8-029-345-23-45" pattern="^(\+375\-|8\-0)(17|29|44|25|33)\-\d{3}\-\d{2}\-\d{2}$" placeholder="" class="form-control input-md" required="" type="text">
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
