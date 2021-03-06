<%@ page import="com.baranova.pharmacy.constant.ParameterName" %>
<%@ page import="com.baranova.pharmacy.type.PageName" %>
<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtags"%>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="resource.text" />
<ctg:navigation_menu role="${loggedRole}"/>
<jsp:include page="${pageContext.request.contextPath}/${include_top_menu}"/>
<% request.getSession().setAttribute(ParameterName.LAST_PAGE, PageName.LOGGING_FORM);%>

<form class="form-horizontal" action="${pageContext.request.contextPath}/login" method="post">
    <fieldset>
        <legend><fmt:message key="login.legend"/> </legend>
        <div class="form-group">
            <label class="col-md-4 control-label" for="login"><fmt:message key="login.label.username"/></label>
            <div class="col-md-4">
                <input id="login" name="login" type="text" pattern="[A-Za-z0-9]{5,15}" placeholder="" class="form-control input-md" required="">
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-4 control-label" for="password"><fmt:message key="login.label.password"/></label>
            <div class="col-md-4">
                <input id="password" name="password" pattern="[A-Za-z0-9]{5,15}" type="password" placeholder="" class="form-control input-md" required="">
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-4 control-label" for="singlebutton"></label>
            <div class="col-md-4">
                <button id="singlebutton" name="singlebutton" class="btn btn-success"><fmt:message key="login.label.button"/> </button>
            </div>
        </div>
        <input type="hidden" name="command" value="authorization" />
    </fieldset>
</form>
<jsp:include page="${pageContext.request.contextPath}/jsp/bottom/bottom.jsp"/>
