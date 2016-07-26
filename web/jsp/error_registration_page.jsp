<%@ page import="com.baranova.pharmacy.constant.ParameterName" %>
<%@ page import="com.baranova.pharmacy.type.PageName" %>
<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="resource.text" />
<%@ include file="top_menu_main.jsp" %>
<% request.getSession().setAttribute(ParameterName.LAST_PAGE.toString(), PageName.REGISTRATION_ERROR);%>
<br>
<br>
<h2><fmt:message key="error.page.header" /></h2>
<hr>
<br>
<h3><fmt:message key="error.registration.header"/></h3>
<p><a href=# onclick="history.back(); return false;">
    <fmt:message key="buttom.back" /></a>
<%@include file="bottom.jsp"%>