package com.baranova.pharmacy.entity;

public class Recipe extends Entity{

    private long patientID;
    private long doctorID;
    private long medicineID;
    private boolean expired;

    public Recipe(){
    }

    public Recipe(long id, long patientID, long doctorID, long medicineID, boolean expired) {
        super(id);
        this.patientID = patientID;
        this.doctorID = doctorID;
        this.medicineID = medicineID;
        this.expired = expired;
    }
    public long getPatientID() {return patientID;}
    public long getDoctorID() {return doctorID;}
    public long getMedicineID() {return medicineID;}
    public boolean isExpired() {return expired;}

    public void setPatientID(long patientID) {this.patientID = patientID;}
    public void setDoctorID(long doctorID) {this.doctorID = doctorID;}
    public void setMedicineID(long medicineID) {this.medicineID = medicineID;}
    public void setExpired(boolean expired) {this.expired = expired;}
}
