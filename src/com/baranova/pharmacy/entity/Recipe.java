package com.baranova.pharmacy.entity;

import com.baranova.pharmacy.dao.UserDAO;
import com.baranova.pharmacy.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Recipe extends Entity{
    private static final Logger LOG= LogManager.getLogger();

    private long patientID;
    private long doctorID;
    private long medicineID;
    private int medicineQuantity;
    private boolean expired;

    public Recipe(){
    }

    public Recipe(long id, long patientID, long doctorID, long medicineID, int medicineQuantity, boolean expired) {
        super(id);
        this.patientID = patientID;
        this.doctorID = doctorID;
        this.medicineID = medicineID;
        this.medicineQuantity=medicineQuantity;
        this.expired = expired;
    }
    public long getPatientID() {return patientID;}
    public long getDoctorID() {return doctorID;}
    public long getMedicineID() {return medicineID;}
    public boolean isExpired() {return expired;}
    public int getMedicineQuantity() {return medicineQuantity;}

    public void setPatientID(long patientID) {this.patientID = patientID;}
    public void setDoctorID(long doctorID) {this.doctorID = doctorID;}
    public void setMedicineID(long medicineID) {this.medicineID = medicineID;}
    public void setExpired(boolean expired) {this.expired = expired;}
    public void setMedicineQuantity(int medicineQuantity) {this.medicineQuantity = medicineQuantity;}

    @Override
    public String toString() {
        String recipe="";
        try {

            recipe = "Recipe{" +

                    "patientID=" + new UserDAO().findEntityById(patientID).getSurname() +
                    ", doctorID=" + doctorID +
                    ", medicineID=" + medicineID +
                    ", medicineQuantity=" + medicineQuantity +
                    ", expired=" + expired +
                    '}';
        } catch (DAOException e){
            LOG.error("sd");
        }
        return recipe;
    }

}
