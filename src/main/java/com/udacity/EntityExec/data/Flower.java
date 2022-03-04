package com.udacity.EntityExec.data;

import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "plant")
public class Flower {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Nationalized // should use @Nationalized instead of @Type=nstring
    private String name;
    private String color;
    @Column(precision = 12, scale = 4)
    private BigDecimal price; // BigDecimal is the standard Java class for currency math

    /* getters and setters*/

    public Flower(Long id, String name, String color, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.price = price;
    }

    public Flower(String name, String color, BigDecimal price) {
        this.name = name;
        this.color = color;
        this.price = price;
    }

    public Flower() {
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
