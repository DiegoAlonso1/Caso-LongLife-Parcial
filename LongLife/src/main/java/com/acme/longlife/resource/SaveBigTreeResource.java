package com.acme.longlife.resource;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Calendar;

public class SaveBigTreeResource {
    @NotNull
    @Column(unique = true)
    @Size(max = 60)
    private String username;

    @NotNull
    @Size(max = 100)
    private String email;

    @NotNull
    @Size(max = 60)
    private String firstName;

    @NotNull
    @Size(max = 60)
    private String lastName;

    @NotNull
    @Size(max = 30)
    private String gender;

    @NotNull
    @Temporal(TemporalType.DATE)
    private Calendar bornAt;

    public String getUsername() {
        return username;
    }

    public SaveBigTreeResource setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public SaveBigTreeResource setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public SaveBigTreeResource setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public SaveBigTreeResource setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public SaveBigTreeResource setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public Calendar getBornAt() {
        return bornAt;
    }

    public SaveBigTreeResource setBornAt(Calendar bornAt) {
        this.bornAt = bornAt;
        return this;
    }
}
