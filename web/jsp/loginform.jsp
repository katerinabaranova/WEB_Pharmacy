<%@ page import="com.baranova.pharmacy.constant.ParameterName" %>
<%@ page import="com.baranova.pharmacy.type.PageName" %>
<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="resource.text" />
<%@ include file="top_menu_main.jsp" %>
<% request.getSession().setAttribute(ParameterName.LAST_PAGE.toString(), PageName.LOGGING_FORM);%>
<form class="form-horizontal" action="login" method="post">
    <fieldset>
        <legend><fmt:message key="login.legend"/> </legend>
        <div class="form-group">
            <label class="col-md-4 control-label" for="Login"><fmt:message key="login.label.username"/>:</label>
            <div class="col-md-4">
                <input id="Login" name="Login" type="text" pattern="[A-Za-Z]{5,15}" value="tomcat" placeholder="" class="form-control input-md" required="">
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-4 control-label" for="Password"><fmt:message key="login.label.password"/></label>
            <div class="col-md-4">
                <input id="Password" name="Password" pattern="[A-Za-z0-9]{4,15}" type="password" value="tomcat" placeholder="" class="form-control input-md" required="">
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-4 control-label" for="singlebutton"></label>
            <div class="col-md-4">
                <button id="singlebutton" name="singlebutton" class="btn btn-success"><fmt:message key="login.label.buttom"/> </button>
            </div>
        </div>
        <input type="hidden" name="command" value="authorization" />
    </fieldset>
</form>
<p><a href=# onclick="history.back(); return false;"><fmt:message key="buttom.back" /></a</p>
<%@include file="bottom.jsp" %>
