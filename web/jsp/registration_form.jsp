<%@ page language="java" contentType="text/html;charset=utf-8"  pageEncoding="UTF-8" %>
<%@ include file="top-menu-main.jsp" %>

<form class="form-horizontal" action="../registration" method="post">
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
            <label class="col-md-4 control-label" for="Password">Password</label>
            <div class="col-md-4">
                <input id="Password" name="Password" placeholder="" class="form-control input-md" required="" type="password">

            </div>
        </div>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="Name">Name</label>
            <div class="col-md-4">
                <input id="Name" name="Name" placeholder="" class="form-control input-md" required="" type="text">

            </div>
        </div>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="Surname">Surname</label>
            <div class="col-md-4">
                <input id="Surname" name="Surname" placeholder="" class="form-control input-md" type="text">

            </div>
        </div>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="City">City</label>
            <div class="col-md-4">
                <input id="City" name="City" placeholder="" class="form-control input-md" required="" type="text">

            </div>
        </div>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="Street">Street</label>
            <div class="col-md-4">
                <input id="Street" name="Street" placeholder="" class="form-control input-md" type="text">

            </div>
        </div>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="Housenumber">House number</label>
            <div class="col-md-4">
                <input id="Housenumber" name="Housenumber" placeholder="" class="form-control input-md" type="text">

            </div>
        </div>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="Apartment">Apartment</label>
            <div class="col-md-4">
                <input id="Apartment" name="Apartment" placeholder="" class="form-control input-md" type="text">

            </div>
        </div>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="Email">E-mail</label>
            <div class="col-md-4">
                <input id="Email" name="Email" placeholder="" class="form-control input-md" required="" type="text">

            </div>
        </div>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="Phonenumber">Phonenumber</label>
            <div class="col-md-4">
                <input id="Phonenumber" name="Phonenumber" placeholder="" class="form-control input-md" required="" type="text">

            </div>
        </div>

        <!-- Multiple Radios (inline) -->
        <div class="form-group">
            <label class="col-md-4 control-label" for="role-0">Register as</label>
            <div class="col-md-4">
                <label class="radio-inline" for="role-0">
                    <input name="Role" id="role-0" value="buyer" checked="checked" type="radio">
                    Buyer
                </label>
                <label class="radio-inline" for="role-1">
                    <input name="Role" id="role-1" value="doctor" type="radio">
                    Doctor
                </label>
            </div>
        </div>
        <input type="hidden" name="command" value="registration" />

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
