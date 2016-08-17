package com.baranova.pharmacy.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;

public class Recipe extends Entity{
    private static final Logger LOG= LogManager.getLogger();

    private Date date=new Date();
    private User patient;
    private User doctor;
    private Medicine medicine;
    private int medicineQuantity;
    private boolean expired;
    private boolean renewRequest;

    public Recipe(){
    }

    public Recipe(long id, User patient, User doctor, Medicine medicine, int medicineQuantity, boolean expired,boolean renewRequest) {
        super(id);
        this.patient = patient;
        this.doctor = doctor;
        this.medicine = medicine;
        this.medicineQuantity=medicineQuantity;
        this.expired = expired;
        this.renewRequest=renewRequest;
    }
    public User getPatient() {return patient;}
    public User getDoctor() {return doctor;}
    public Medicine getMedicine() {return medicine;}
    public boolean isExpired() {return expired;}
    public int getMedicineQuantity() {return medicineQuantity;}
    public boolean isRenewRequest() {return renewRequest;}
    public Date getDate() {return date;}

    public void setPatient(User patient) {this.patient = patient;}
    public void setDoctor(User doctor) {this.doctor = doctor;}
    public void setMedicine(Medicine medicine) {this.medicine = medicine;}
    public void setExpired(boolean expired) {this.expired = expired;}
    public void setMedicineQuantity(int medicineQuantity) {this.medicineQuantity = medicineQuantity;}
    public void setRenewRequest(boolean renewRequest) {this.renewRequest = renewRequest;}
    public void setDate(Date date) {this.date = date;}

    @Override
    public String toString() {
        return  "Recipe{" +
                "date="+date.toString()+
                ", patient's surname =\'" + patient.getSurname() +"\'"+
                ", patient's name =\'" + patient.getName() +"\'"+
                ", doctor's surname=\'" + doctor.getSurname() +"\'"+
                ", medicine name=\'" + medicine.getMedicineName() +"\'"+
                ", medicineQuantity=" + medicineQuantity +
                ", expired=" + expired +
                '}';
    }

}
