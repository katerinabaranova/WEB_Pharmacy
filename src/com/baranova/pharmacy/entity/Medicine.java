package com.baranova.pharmacy.entity;


public class Medicine extends Entity {

    private String medicineName;
    private int dosage;
    private int price;
    private String packageType;
    private int packageQuantity;
    private int storeQuantity;

    public Medicine(){
    }

    public String getMedicineName() {return medicineName;}
    public void setMedicineName(String medicineName) {this.medicineName = medicineName;}
    public int getDosage() {return dosage;}
    public void setDosage(int dosage) {this.dosage = dosage;}
    public int getPrice() {return price;}
    public void setPrice(int price) {this.price = price;
    }

    public String getPackageType() {
        return packageType;
    }

    public void setPackageType(String packageType) {
        this.packageType = packageType;
    }

    public int getPackageQuantity() {
        return packageQuantity;
    }

    public void setPackageQuantity(int packageQuantity) {
        this.packageQuantity = packageQuantity;
    }

    public int getStoreQuantity() {
        return storeQuantity;
    }

    public void setStoreQuantity(int storeQuantity) {
        this.storeQuantity = storeQuantity;
    }

    public Medicine(long id, String medicineName, int dosage, int price, String packageType, int packageQuantity, int storeQuantity) {
        super(id);
        this.medicineName = medicineName;
        this.dosage = dosage;
        this.price = price;
        this.packageType = packageType;
        this.packageQuantity = packageQuantity;
        this.storeQuantity = storeQuantity;
    }



}
