package com.baranova.pharmacy.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Order extends Entity {

    private static final Logger LOG= LogManager.getLogger();
    private User buyer;
    private Medicine medicine;
    private int quantity;
    private double totalAmount;
    private boolean paid;
    private boolean delivery;

    public Order(){
    }
    public Order(long id, User buyer, Medicine medicine, int quantity, int totalAmount,
                 boolean paid, boolean delivery) {
        super(id);
        this.buyer = buyer;
        this.medicine = medicine;
        this.quantity = quantity;
        this.totalAmount = totalAmount;
        this.paid = paid;
        this.delivery = delivery;
    }

    public long getOrderId(){return super.getId();}
    public User getBuyer() {return buyer;}
    public Medicine getMedicine() {return medicine;}
    public int getQuantity() {return quantity;}
    public double getTotalAmount() {return totalAmount;}
    public boolean isPaid() {return paid;}
    public boolean isDelivery() {return delivery;}

    public void setOrderId(long id){super.setId(id);}
    public void setBuyer(User buyer) {this.buyer = buyer;}
    public void setMedicine(Medicine medicine) {this.medicine = medicine;}
    public void setQuantity(int quantity) {this.quantity = quantity;}
    public void setTotalAmount(double totalAmount) {this.totalAmount = totalAmount;}
    public void setPaid(boolean paid) {this.paid = paid;}
    public void setDelivery(boolean delivery) {this.delivery = delivery;}

    @Override
    public String toString() {
        return "Order{" +
                "buyer=" + buyer +
                ", medicine=" + medicine.getMedicineName() +
                "("+medicine.getDosage()+"mg)"+
                ", quantity=" + quantity +
                ", totalAmount=" + totalAmount +
                ", paid=" + paid +
                ", delivery=" + delivery +
                '}';
    }
}
