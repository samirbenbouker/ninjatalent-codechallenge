package com.samirbenbouker.ninjatalent.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity(name = "Users")
// we are going to put the mail as a constraint so that it cannot be duplicated
@Table(
        name = "users",
        uniqueConstraints = {
                @UniqueConstraint(name = "user_email_unique", columnNames = "email")
        }
)
public class User {

    @Id
    @SequenceGenerator(
            name = "user_id_sequence",
            sequenceName = "user_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_id_sequence"
    )
    @Column(name = "userId")
    private Integer userId;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "birthDate")
    private LocalDateTime birthDate;

    @ManyToOne
    @JoinColumn(
            name="addressId",
            referencedColumnName = "addressId",
            foreignKey = @ForeignKey(
                    name = "user_address_fk"
            )
    )
    private Address address;

    public User() { }

    public User(String name, String email, LocalDateTime birthDate, Address address) {
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
        this.address = address;
    }

    public User(Integer userId, String name, String email, LocalDateTime birthDate, Address address) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
        this.address = address;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDateTime birthDate) {
        this.birthDate = birthDate;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    // We create this function to control that when a user is created the mandatory fields are not null
    // I understand that putting in the column tag that the column is "nullable" would not let us create the user,
    //  but I did it just to control the error
    public boolean anyNull() {
        if(userId == null) {
            return false;
        }

        if (name == null) {
            return false;
        }

        if (email == null) {
            return false;
        }

        if (birthDate == null) {
            return false;
        }

        if (address == null || address.getAddressId() == null) {
            return false;
        }

        return true;
    }
}
