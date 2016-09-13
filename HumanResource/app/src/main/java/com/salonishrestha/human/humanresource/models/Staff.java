package com.salonishrestha.human.humanresource.models;

import java.io.Serializable;

/**
 * Created by Owner on 01/09/2016.
 */
public class Staff implements Serializable {
    private String name;
    private String address;
    private String designation;
    private  static  final long serialVersionUID=928938298392L;

    public Staff() {
    }

    public Staff(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }
}
