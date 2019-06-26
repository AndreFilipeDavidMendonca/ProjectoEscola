package com.PortalNet.PortalNet;

import org.springframework.stereotype.Component;

@Component
public class Workers {

    private int numID;
    private String Name;
    private String email;
    private String position;
    private String password;
    private String gender;

    public Workers(int numID, String name, String email, String position, String password, String gender) {
        this.numID = numID;
        Name = name;
        this.email = email;
        this.position = position;
        this.password = password;
        this.gender = gender;
    }

    public int getNumID() {
        return numID;
    }

    public String getName() {
        return Name;
    }

    public String getEmail() {
        return email;
    }

    public String getPosition() {
        return position;
    }

    public String getPassword() {
        return password;
    }

    public String getGender() {
        return gender;
    }
}

