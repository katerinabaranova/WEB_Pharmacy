package com.baranova.pharmacy.entity;

import java.io.Serializable;

/**
 * Entity abstract class.
 */
public abstract class Entity implements Serializable,Cloneable{

    private long id;

    public Entity(){
    }

    public Entity(long id){
        this.id=id;
    }

    public long getId() {return id;}
    public void setId(long id) {this.id = id;}
}
