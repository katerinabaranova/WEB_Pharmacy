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
<% request.getSession().setAttribute(ParameterName.LAST_PAGE.toString(), PageName.REFILL_BALANCE_FORM);%>
<form class="form-horizontal" action="/refill_balance">
    <fieldset>
        <legend><fmt:message key="balance.refill.header"/> </legend>
        <div class="form-group">
            <label class="col-md-4 control-label" for="increaseAmount"><fmt:message key="balance.refill.amount"/> </label>
            <div class="col-md-2">
                <input id="increaseAmount" pattern="[1-9][0-9]?" name="increaseAmount" placeholder="" class="form-control input-md" required="" type="text">
                <span class="help-block"><fmt:message key="balance.refill.hint"/> </span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-4 control-label" for="singlebutton"></label>
            <div class="col-md-4">
                <button id="singlebutton" name="singlebutton" class="btn btn-inverse"><fmt:message key="submit.button"/> </button>
            </div>
        </div>
        <input type="hidden" name="command" value="balance_refill">
    </fieldset>
</form>


<jsp:include page="${pageContext.request.contextPath}/jsp/bottom/bottom.jsp"/>