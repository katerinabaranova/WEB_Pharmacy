package com.baranova.pharmacy.entity;


public class Role extends Entity{

    private String role;

    public Role(){}
    public Role(long id, String role) {
        super(id);
        this.role = role;
    }

    public long getRoleId(){return super.getId();}
    public String getRole() {return role;}

    public void setRoleId(long id){super.setId(id);}
    public void setRole(String role) {this.role = role;}

    @Override
    public String toString() {
        return "Role{" +
                "id="+super.getId()+
                ", role='" + role + '\'' +
                '}';
    }

}
