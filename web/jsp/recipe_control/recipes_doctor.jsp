<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="resource.text" />
<ctg:navigation_menu role="${loggedRole}"/>
<jsp:include page="${pageContext.request.contextPath}/${include_top_menu}"/>
<h2><fmt:message key="all.medicines.header"/> </h2>
<div class="row">
    <div class="col-md-2"><fmt:message key="recipe.patient.surname"/> </div>
    <div class="col-md-1"><fmt:message key="recipe.medicine.name"/> </div>
    <div class="col-md-1"><fmt:message key="recipe.medicine.name"/></div>
    <div class="col-md-1"><fmt:message key="recipe.medicine.dosage"/></div>
    <div class="col-md-1"><fmt:message key="recipe.medicine.quantity"/></div>
    <div class="col-md-1"><fmt:message key="search.results.price"/></div>
    <div class="col-md-1"><fmt:message key="search.results.instore"/></div>
    <div class="col-md-1"><fmt:message key="search.results.recipe"/></div>
</div>
<c:forEach items="${all_medicines_list}" var="medicine">
    <div class="row">
        <div class=col-md-1>${medicine.id}</div>
        <div class=col-md-1>${medicine.medicineName}</div>
        <div class=col-md-1>${medicine.dosage}</div>
        <div class=col-md-1>${medicine.packageType}</div>
        <div class=col-md-1>${medicine.packageQuantity}</div>
        <div class=col-md-1>${medicine.price}</div>
        <div class=col-md-1>${medicine.storeQuantity}</div>
        <div class=col-md-1>${medicine.recipe}</div>


        <form action="/proceed_update_medicine" method="post">
            <div class="form-group">
                <div class="col-md-2">
                    <input type="hidden" name="command" value="proceed_update_medicine" />
                    <input type="hidden" name="medicine" value="${medicine.id}">
                    <button type="submit" class="btn btn-warning"><fmt:message key="edit.button"/> </button>
                </div>
            </div>
        </form>

        <form action="/delete_medicine" method="post">
            <div class="form-group">
                <div class="col-md-2">
                    <input type="hidden" name="command" value="delete_medicine" />
                    <input type="hidden" name="medicine" value="${medicine.id}">
                    <button type="submit" class="btn btn-danger"><fmt:message key="delete.button"/> </button>
                </div>
            </div>
        </form>
    </div>
    <br>
</c:forEach>
<p><a href=# onclick="history.back(); return false;"><fmt:message key="button.back" /></a>
    <jsp:include page="${pageContext.request.contextPath}/jsp/bottom/bottom.jsp"/>
