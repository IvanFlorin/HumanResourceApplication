package com.salonishrestha.human.humanresource.models;

/**
 * Created by Owner on 07/09/2016.
 */
public class User {

    private String name;
    private String uid;
    private String user_type;

    public User(){

    }

    public User(String name, String user_type) {
        this.name = name;
        this.user_type = user_type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }
}
