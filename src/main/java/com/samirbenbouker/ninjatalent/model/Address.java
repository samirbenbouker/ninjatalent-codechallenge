package com.samirbenbouker.ninjatalent.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "Address")
@Table(name = "address")
public class Address {

    @Id
    @SequenceGenerator(
            name = "address_id_sequence",
            sequenceName = "address_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "address_id_sequence"
    )
    @Column(name = "addressId")
    private Integer addressId;

    @Column(name = "street")
    private String street;

    @Column(name = "state")
    private String state;

    @Column(name = "city")
    private String city;

    @Column(name = "country")
    private String country;

    @Column(name = "zip")
    private String zip;

    @OneToMany(
            mappedBy="address",
            orphanRemoval = true,
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            fetch = FetchType.LAZY
    )
    private List<User> users = new ArrayList<>();

    public Address() { }

    public Address(String street, String state, String city, String country, String zip) {
        this.street = street;
        this.state = state;
        this.city = city;
        this.country = country;
        this.zip = zip;
    }

    public Address(Integer addressId, String street, String state, String city, String country, String zip) {
        this.addressId = addressId;
        this.street = street;
        this.state = state;
        this.city = city;
        this.country = country;
        this.zip = zip;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public void addUser(User user) {
        if (!this.users.contains(user)) {
            this.users.add(user);
            user.setAddress(this);
        }
    }

    public void removeUser(User user) {
        if (this.users.contains(user)) {
            this.users.remove(user);
            user.setAddress(null);
        }
    }
}
