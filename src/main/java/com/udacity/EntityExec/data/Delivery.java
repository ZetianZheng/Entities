package com.udacity.EntityExec.data;

import org.hibernate.annotations.Nationalized;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

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

    // make sure to specify mappedBy. Lazy fetch optional,
    // but often a good idea for collection attributes
    //
    // if it is removed, it will also remove any Plants associated with it at the same time:
    // added CascadeType.REMOVE to automatically clear any associated plants when removed
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "delivery", cascade = CascadeType.REMOVE)
    private List<Plant> plants;

    /**
     * do we need constructor for not primary-key-class entity?
     * @param id
     * @param name
     * @param address
     * @param deliveryDate
     * @param deliveryTime
     * @param completed
     */
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

    public List<Plant> getPlants() {
        return plants;
    }

    public void setPlants(List<Plant> plants) {
        this.plants = plants;
    }

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
