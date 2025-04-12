package com.hotel.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class) // Enable JPA auditing
public class Hotels {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String hotelName;
    private String address;
    private String contactDetails;
    private String email;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdDate;

    public Hotels() {
        super();
    }

    public Hotels(int id, String hotelName, String address, String contactDetails, String email, LocalDateTime createdDate) {
        super();
        this.id = id;
        this.hotelName = hotelName;
        this.address = address;
        this.contactDetails = contactDetails;
        this.email = email;
        this.createdDate = createdDate;
    }

    // Getters and setters for all fields (including createdDate)

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(String contactDetails) {
        this.contactDetails = contactDetails;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }
}