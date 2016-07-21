
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form class="form-horizontal">
    <fieldset>

        <!-- Form Name -->
        <legend>New recipe</legend>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="PatientSurname">Patient's surname</label>
            <div class="col-md-4">
                <input id="PatientSurname" name="PatientSurname" placeholder="" class="form-control input-md" required="" type="text">

            </div>
        </div>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="PatientName">Patient's name</label>
            <div class="col-md-4">
                <input id="PatientName" name="PatientName" placeholder="" class="form-control input-md" required="" type="text">

            </div>
        </div>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="MedicineName">Medicine name</label>
            <div class="col-md-4">
                <input id="MedicineName" name="MedicineName" placeholder="" class="form-control input-md" required="" type="text">

            </div>
        </div>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="MedicineDosage">Medicine dosage</label>
            <div class="col-md-4">
                <input id="MedicineDosage" name="MedicineDosage" placeholder="" class="form-control input-md" required="" type="text">

            </div>
        </div>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="MedicineQuantity">Medicine quantity</label>
            <div class="col-md-4">
                <input id="MedicineQuantity" name="MedicineQuantity" placeholder="" class="form-control input-md" required="" type="text">

            </div>
        </div>

        <!-- Button -->
        <div class="form-group">
            <label class="col-md-4 control-label" for="singlebutton"></label>
            <div class="col-md-4">
                <button id="singlebutton" name="singlebutton" class="btn btn-success">Create</button>
            </div>
        </div>

    </fieldset>
</form>

</body>
</html>
