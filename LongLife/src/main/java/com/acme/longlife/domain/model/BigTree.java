package com.acme.longlife.domain.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "big_trees")
public class BigTree implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true)
    @Size(max = 60)
    private String username;

    @Column(unique = true, nullable = false, length = 100)
    private String email;

    @Column(name = "first_name", updatable = false, nullable = false, length = 60)
    private String firstName;

    @Column(name = "last_name", updatable = false, nullable = false, length = 60)
    private String lastName;

    @NotNull
    @Size(max = 30)
    @Column(updatable = false)
    private String gender;

    @Column(updatable = false, nullable = false)
    @Temporal(TemporalType.DATE)
    private Calendar bornAt;



    public Long getId() {
        return id;
    }

    public BigTree setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public BigTree setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public BigTree setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public BigTree setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public BigTree setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public BigTree setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public Calendar getBornAt() {
        return bornAt;
    }

    public BigTree setBornAt(Calendar bornAt) {
        this.bornAt = bornAt;
        return this;
    }
}
