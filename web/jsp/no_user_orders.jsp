<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="resource.text" />
<ctg:navigation_menu role="${loggedRole}"/>
<jsp:include page="${pageContext.request.contextPath}/${include_page}"/>
<h2><fmt:message key="search.no.results"/> </h2>
<h1><fmt:message key="search.try.again"/> </h1>
<br>
<br>
<br>
<br>
<br>
<jsp:include page="${pageContext.request.contextPath}/jsp/bottom/bottom.jsp"/>
