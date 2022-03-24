package com.udacity.EntityExec.data;

import com.fasterxml.jackson.annotation.JsonView;
import com.udacity.EntityExec.DTO.Views;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Inheritance is a way to share data and associations across multiple related classes.
 * the shared attributes of name, price, and id
 * // Uses InheritanceType.JOINED to store shared fields in 'plant' and unique fields
 * // in subclass tables
 * 父类 ：Plant
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "plant")
public class Plant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonView(Views.Public.class)
    @Nationalized // should use @Nationalized(some foreign language, UNICODE) instead of @Type=nstring
    private String name;

    @JsonView(Views.Public.class)
    @Column(precision = 12, scale = 4)
    private BigDecimal price; // BigDecimal is the standard Java class for currency math

    //many plants can belong to one delivery
    //map the join column in the plant table
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    public Plant(Long id, String name, BigDecimal price, Delivery delivery) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.delivery = delivery;
    }

    public Plant(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public Plant(String name, BigDecimal price, Delivery delivery) {
        this.name = name;
        this.price = price;
        this.delivery = delivery;
    }

    public Plant() {
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }
}
