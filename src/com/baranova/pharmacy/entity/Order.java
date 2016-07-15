package com.baranova.pharmacy.entity;

public class Order extends Entity {

    private long fkUserID;
    private long fkMedicineID;
    private int quantity;
    private int totalAmount;
    private boolean paid;
    private boolean delivery;

    public Order(){
    }
    public Order(long id, long fkUserID, long fkMedicineID, int quantity, int totalAmount,
                 boolean paid, boolean delivery) {
        super(id);
        this.fkUserID = fkUserID;
        this.fkMedicineID = fkMedicineID;
        this.quantity = quantity;
        this.totalAmount = totalAmount;
        this.paid = paid;
        this.delivery = delivery;
    }

    public long getOrderId(){return super.getId();}
    public long getFkUserID() {return fkUserID;}
    public long getFkMedicineID() {return fkMedicineID;}
    public int getQuantity() {return quantity;}
    public int getTotalAmount() {return totalAmount;}
    public boolean isPaid() {return paid;}
    public boolean isDelivery() {return delivery;}

    public void setOrderId(long id){super.setId(id);}
    public void setFkUserID(long fkUserID) {this.fkUserID = fkUserID;}
    public void setFkMedicineID(long fkMedicineID) {this.fkMedicineID = fkMedicineID;}
    public void setQuantity(int quantity) {this.quantity = quantity;}
    public void setTotalAmount(int totalAmount) {this.totalAmount = totalAmount;}
    public void setPaid(boolean paid) {this.paid = paid;}
    public void setDelivery(boolean delivery) {this.delivery = delivery;}

    @Override
    public String toString() {
        return "Order{" +
                "fkUserID=" + fkUserID +
                ", fkMedicineID=" + fkMedicineID +
                ", quantity=" + quantity +
                ", totalAmount=" + totalAmount +
                ", paid=" + paid +
                ", delivery=" + delivery +
                '}';
    }
}
