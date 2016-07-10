package com.baranova.pharmacy.entity;

import java.io.Serializable;

/**
 * Created by Ekaterina on 7/9/16.
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
