<%@ page language="java" contentType="text/html;charset=utf-8"  pageEncoding="UTF-8" %>
<%@ include file="top-menu-main.jsp" %>

<form class="form-horizontal" action="search" method="post">
    <fieldset>
        <legend>Search for necessary medicine</legend>

        <div class="form-group">
            <label class="col-md-4 control-label" for="medicineName">Medicine name</label>
            <div class="col-md-4">
                <input id="medicineName" pattern="[A-Za-z0-9]{4,25}" name="medicineName" placeholder="" class="form-control input-md" required="" type="text">
            </div>
        </div>


        <div class="form-group">
            <label class="col-md-4 control-label" for="singlebutton"></label>
            <div class="col-md-4">
                <button id="singlebutton" name="singlebutton" class="btn btn-primary">Search</button>
            </div>
        </div>

    </fieldset>
</form>


<%@include file="bottom.jsp" %>