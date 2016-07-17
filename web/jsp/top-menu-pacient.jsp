<%@ page language="java" contentType="text/html;charset=utf-8"  pageEncoding="UTF-8" %>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="resources.text" />
<!DOCTYPE html>
<html lang="${language}">
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <meta http-equiv="Cache-Control" content="no-cache">
    <title>Health care  - medicine </title>
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
            <a class="navbar-brand" href="/baranova">Main</a>
          </div>
          <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
              <li><a href="orderform.jsp">Medicine search</a></li>
              <li><a href="users_orders.jsp">Check my orders</a></li>
              <li><a href="cancelreservations.jsp">Check my reciepts</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
              <li><a href="userform.jsp">Sign-up</a></li>
              <li><a href="signin.jsp">Sign-in</a></li>
              <li><a href="signout.jsp">Sign-out</a></li>
              <li><a href="/manager/html/list">Tomcat</a></li>
            </ul>
          </div>
        </div>
      </div>