package com.baranova.pharmacy.entity;


public class Medicine extends Entity {

    private String medicineName;
    private int dosage;
    private int price;
    private String packageType;
    private int packageQuantity;
    private int storeQuantity;
    private boolean receipt;

    public Medicine(){
    }

    public Medicine(String medicineName, int dosage,  String packageType, int packageQuantity, int price, int storeQuantity,boolean receipt) {
        this.medicineName = medicineName;
        this.dosage = dosage;
        this.price = price;
        this.packageType = packageType;
        this.packageQuantity = packageQuantity;
        this.storeQuantity = storeQuantity;
        this.receipt=receipt;
    }
    public long getId(){return super.getId();}
    public String getMedicineName() {return medicineName;}
    public int getDosage() {return dosage;}
    public int getPrice() {return price;}
    public String getPackageType() {return packageType;}
    public int getPackageQuantity() {return packageQuantity;}
    public int getStoreQuantity() {return storeQuantity;}
    public boolean isReceipt() {return receipt;}

    public void setId(long id){super.setId(id);}
    public void setMedicineName(String medicineName) {this.medicineName = medicineName;}
    public void setDosage(int dosage) {this.dosage = dosage;}
    public void setPrice(int price) {this.price = price;}
    public void setPackageType(String packageType) {
        this.packageType = packageType;
    }
    public void setPackageQuantity(int packageQuantity) {
        this.packageQuantity = packageQuantity;
    }
    public void setStoreQuantity(int storeQuantity) {
        this.storeQuantity = storeQuantity;
    }
    public void setReceipt(boolean receipt) {this.receipt = receipt;}

    @Override
    public String toString() {
        return "Medicine{" +
                "medicineName='" + medicineName + '\'' +
                ", dosage=" + dosage +
                ", price=" + price +
                ", packageType='" + packageType + '\'' +
                ", packageQuantity=" + packageQuantity +
                ", storeQuantity=" + storeQuantity +
                ", receipt=" + receipt +
                '}';
    }
}
