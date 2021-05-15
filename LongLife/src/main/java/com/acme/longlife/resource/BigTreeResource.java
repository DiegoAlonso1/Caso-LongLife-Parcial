package com.acme.longlife.resource;

import java.util.Calendar;
import java.util.Date;

public class BigTreeResource {
    private Long id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String gender;
    private Calendar bornAt;

    public Long getId() {
        return id;
    }

    public BigTreeResource setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public BigTreeResource setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public BigTreeResource setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public BigTreeResource setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public BigTreeResource setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public BigTreeResource setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public Calendar getBornAt() {
        return bornAt;
    }

    public BigTreeResource setBornAt(Calendar bornAt) {
        this.bornAt = bornAt;
        return this;
    }
}
