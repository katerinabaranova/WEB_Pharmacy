package com.baranova.pharmacy.entity;

/**
 * Created by Ekaterina on 7/9/16.
 */
public class User extends Entity {

    private String login;
    private String password;
    private String name;
    private String surname;
    private String city;
    private String adress;
    private String houseNumber;
    private String email;



    private String phoneNumber;
    private int role;

    public User(){
    }
    public User(long id, String login, String password, String name, String surname,
                String city, String adress, String houseNumber, String email, String phoneNumber, int role) {
        super(id);
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.city = city;
        this.adress = adress;
        this.houseNumber = houseNumber;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }


    public long getUserID(){return super.getId();}
    public String getLogin() {return login;}
    public String getPassword() {return password;}
    public String getName() {return name;}
    public String getSurname() {return surname;}
    public String getCity() {return city;}
    public String getAdress() {return adress;}
    public String getHouseNumber() {return houseNumber;}
    public String getEmail() {return email;}
    public String getPhoneNumber() {return phoneNumber;}
    public int getRole() {return role;}

    public void setUserID(long id){super.setId(id);}
    public void setLogin(String login) {this.login = login;}
    public void setPassword(String password) {this.password = password;}
    public void setName(String name) {this.name = name;}
    public void setSurname(String surname) {this.surname = surname;}
    public void setCity(String city) {this.city = city;}
    public void setAdress(String adress) {this.adress = adress;}
    public void setHouseNumber(String houseNumber) {this.houseNumber = houseNumber;}
    public void setEmail(String email) {this.email = email;}
    public void setPhoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber;}
    public void setRole(int role) {this.role = role;}

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}
