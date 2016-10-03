package com.baranova.pharmacy.entity;


public class User extends Entity {

    private String login;
    private String password;
    private String name;
    private String surname;
    private String city;
    private String street;
    private int houseNumber;
    private int apartment;
    private String email;
    private String phoneNumber;
    private Role role=new Role();
    private double amount;

    public User(){
    }
    public User(long id, String login, String password, String name, String surname,
                String city, String address, int houseNumber, int apartment, String email, String phoneNumber, Role role) {
        super(id);
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.city = city;
        this.street = address;
        this.houseNumber = houseNumber;
        this.apartment=apartment;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }


    public long getUserId(){return super.getId();}
    public String getLogin() {return login;}
    public String getPassword() {return password;}
    public String getName() {return name;}
    public String getSurname() {return surname;}
    public String getCity() {return city;}
    public String getStreet() {return street;}
    public int getHouseNumber() {return houseNumber;}
    public int getApartment() {return apartment;}
    public String getEmail() {return email;}
    public String getPhoneNumber() {return phoneNumber;}
    public Role getRole() {return role;}
    public double getAmount(){return amount;}

    public void setUserId(long id){super.setId(id);}
    public void setLogin(String login) {this.login = login;}
    public void setPassword(String password) {this.password = password;}
    public void setName(String name) {this.name = name;}
    public void setSurname(String surname) {this.surname = surname;}
    public void setCity(String city) {this.city = city;}
    public void setStreet(String street) {this.street = street;}
    public void setHouseNumber(int houseNumber) {this.houseNumber = houseNumber;}
    public void setApartment(int apartment) {this.apartment = apartment;}
    public void setEmail(String email) {this.email = email;}
    public void setPhoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber;}
    public void setRole(Role role) {this.role = role;}
    public void setAmount(double amount) {this.amount = amount;}

    @Override
    public String toString() {
        return "User{" +
                "id=" + super.getId() +
                "login='" + login + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", city='" + city + '\'' +
                ", houseNumber=" + houseNumber +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", role='"+role.getRole()+'\'' +
                ", amount="+amount + "RUB"+
                '}';
    }

}
