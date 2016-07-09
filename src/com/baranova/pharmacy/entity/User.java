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



    public User(){

    }



}
