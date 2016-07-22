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
    <link href="../css/bootstrap.min.css" rel="stylesheet" />
    <script src="../js/bootstrap.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
</head>

<body>
<div class="container">
      <div class="navbar navbar-default" role="navigation">
        <div class="container-fluid">
          <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
              <span class="sr-only">Toggle navigation</span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
            </button>
              <ul class="nav navbar-nav navbar-left">
                  <li>
                      <form action="../controller" method="post">
                      <input type="hidden" name="language" value="en" />
                      <input type="hidden" name="command" value="change_language" />
                          <button type="submit" class="btn-link">EN</button>
                  </form>
                  </li>
                  <li><form action="../controller" method="post">
                      <input type="hidden" name="language" value="ru" />
                      <input type="hidden" name="command" value="change_language" />
                      <button type="submit" class="btn-link">RU</button>
                  </form>
                  </li>
              </ul>
          </div>
          <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
              <li><a href="jsp/index.jsp"><fmt:message key="menu.main"/> </a></li>
              <li><a href="jsp/registration_form.jsp"><fmt:message key="menu.signup" /></a></li>
              <li><a href="jsp/loginform.jsp"><fmt:message key="menu.signin"/> </a></li>
            </ul>
          </div>
        </div>
      </div>
  </div>


