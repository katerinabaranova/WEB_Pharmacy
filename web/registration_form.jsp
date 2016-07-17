<%@ page language="java" contentType="text/html;charset=utf-8"  pageEncoding="UTF-8" %>
<%@ include file="top-menu-main.jsp" %>

<form class="form-horizontal">
    <fieldset>

        <!-- Form Name -->
        <legend>Registration</legend>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="Login">Login</label>
            <div class="col-md-4">
                <input id="Login" name="Login" placeholder="" class="form-control input-md" required="" type="text">

            </div>
        </div>

        <!-- Password input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="passwordinput">Password</label>
            <div class="col-md-4">
                <input id="passwordinput" name="passwordinput" placeholder="" class="form-control input-md" required="" type="password">

            </div>
        </div>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="name">Name</label>
            <div class="col-md-4">
                <input id="name" name="name" placeholder="" class="form-control input-md" required="" type="text">

            </div>
        </div>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="surname">Surname</label>
            <div class="col-md-4">
                <input id="surname" name="surname" placeholder="" class="form-control input-md" type="text">

            </div>
        </div>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="city">City</label>
            <div class="col-md-4">
                <input id="city" name="city" placeholder="" class="form-control input-md" required="" type="text">

            </div>
        </div>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="street">Street</label>
            <div class="col-md-4">
                <input id="street" name="street" placeholder="" class="form-control input-md" type="text">

            </div>
        </div>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="housenumber">House number</label>
            <div class="col-md-4">
                <input id="housenumber" name="housenumber" placeholder="" class="form-control input-md" type="text">

            </div>
        </div>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="apartment">Apartment</label>
            <div class="col-md-4">
                <input id="apartment" name="apartment" placeholder="" class="form-control input-md" type="text">

            </div>
        </div>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="email">E-mail</label>
            <div class="col-md-4">
                <input id="email" name="email" placeholder="" class="form-control input-md" required="" type="text">

            </div>
        </div>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="phonenumber">Phonenumber</label>
            <div class="col-md-4">
                <input id="phonenumber" name="phonenumber" placeholder="" class="form-control input-md" required="" type="text">

            </div>
        </div>

        <!-- Multiple Radios (inline) -->
        <div class="form-group">
            <label class="col-md-4 control-label" for="roleName">Register as</label>
            <div class="col-md-4">
                <label class="radio-inline" for="role-0">
                    <input name="role" id="role-0" value="Buyer" checked="checked" type="radio">
                    Buyer
                </label>
                <label class="radio-inline" for="role-1">
                    <input name="role" id="role-1" value="Doctor" type="radio">
                    Doctor
                </label>
            </div>
        </div>

        <!-- Button -->
        <div class="form-group">
            <label class="col-md-4 control-label" for="singlebutton"></label>
            <div class="col-md-4">
                <button id="singlebutton" name="singlebutton" class="btn btn-primary">Register</button>
            </div>
        </div>

    </fieldset>
</form>


<%@include file="bottom.jsp" %>
