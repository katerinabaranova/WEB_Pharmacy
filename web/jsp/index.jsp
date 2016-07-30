<%@ page language="java" contentType="text/html;charset=utf-8"  pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="resource.text" />
<ctg:navigation_menu role="${loggedRole}"/>
<jsp:include page="${pageContext.request.contextPath}/${include_top_menu}"/>

<form class="form-horizontal" action="../search" method="post">
    <fieldset>

        <legend><fmt:message key="search.legend"/></legend>
        <div class="form-group">
            <label class="col-md-4 control-label" for="medicineName"><fmt:message key="search.medicine.label"/></label>
            <div class="col-md-4">
                <input id="medicineName" pattern="[A-Za-z0-9]{4,25}" name="medicineName" placeholder="" class="form-control input-md" required="" type="text">
            </div>
        </div>


        <div class="form-group">
            <label class="col-md-4 control-label" for="singlebutton"></label>
            <div class="col-md-4">
                <button id="singlebutton" name="singlebutton" class="btn btn-primary"><fmt:message key="search.buttom"/> </button>
            </div>
        </div>
        <input type="hidden" name="command" value="search">
    </fieldset>
</form>


<%@include file="bottom.jsp" %>