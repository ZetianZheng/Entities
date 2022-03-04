package com.udacity.EntityExec.data;

import org.hibernate.annotations.Nationalized;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Nationalized
    private String name;

    @Column(name = "address_full", length = 500)
    private String address;
    private String deliveryDate;
    private LocalDateTime deliveryTime;// includes both date and time - simpler than having two separate fields
    @Type(type = "yes_no")
    private Boolean completed;

    public Delivery(Long id, String name, String address, String deliveryDate, LocalDateTime deliveryTime, Boolean completed) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.deliveryDate = deliveryDate;
        this.deliveryTime = deliveryTime;
        this.completed = completed;
    }

    public Delivery() {
    }

    public Delivery(String name, String address, String deliveryDate, LocalDateTime deliveryTime, Boolean completed) {
        this.name = name;
        this.address = address;
        this.deliveryDate = deliveryDate;
        this.deliveryTime = deliveryTime;
        this.completed = completed;
    }

    /* getters and setters */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public LocalDateTime getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(LocalDateTime deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }
}
