<%@ page language="java" contentType="text/html;charset=utf-8"  pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="resource.text" />
<!DOCTYPE html>
<html lang="${language}">
<head>
  <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
  <meta http-equiv="Cache-Control" content="no-cache">
  <title><fmt:message key="title.main"/> </title>
  <link href="/css/bootstrap.css" rel="stylesheet" />
  <script src="/js/bootstrap.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
</head>

<body>
<div class="supercontainer mainbg">
  <div class="maincontainer">
    <ul class="nav navbar-nav navbar-left">
      <li>
        <form action="/controller" method="post">
          <input type="hidden" name="language" value="en" />
          <input type="hidden" name="command" value="change_language" />
          <button type="submit" class="btn-link"><fmt:message key="language.en"/> </button>
        </form>
      </li>
      <li><form action="/controller" method="post">
        <input type="hidden" name="language" value="ru"/>
        <input type="hidden" name="command" value="change_language" />
        <button type="submit" class="btn-link"><fmt:message key="language.ru"/> </button>
      </form>
      </li>
    </ul>
    <br />
    <br />
    <div>
      <div class="navbar navbar-default" role="navigation">
        <div class="container-fluid">
          <ul class="nav navbar-nav navbar-left">
            <li><fmt:message key="pharmacist.menu.welcome"/> ${loggedUser}</li>
          </ul>
          <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
              <li><a href="${pageContext.request.contextPath}/controller?command=show_medicines"><fmt:message key="pharmacist.menu.medicine"/> </a> </li>
              <li><a href="${pageContext.request.contextPath}/jsp/medicine_control/new_medicine_form.jsp"><fmt:message key="pharmacist.menu.new.medicine"/></a></li>
              <li><a href="${pageContext.request.contextPath}/jsp/user_control/sign_out.jsp"><fmt:message key="menu.sign.out"/></a></li>
            </ul>
          </div>
        </div>
      </div>
    </div>


