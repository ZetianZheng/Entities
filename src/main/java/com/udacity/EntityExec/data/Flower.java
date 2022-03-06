package com.udacity.EntityExec.data;

import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * inheritance:
 * 子类继承Plant， share the attribute: price, name, id from father class: Plant
 */
@Entity
public class Flower extends Plant{
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;

//    @Nationalized // should use @Nationalized(some foreign language, UNICODE) instead of @Type=nstring
//    private String name;

//    @Column(precision = 12, scale = 4)
//    private BigDecimal price; // BigDecimal is the standard Java class for currency math

    /* getters and setters*/

//    public Flower(Long id, String name, String color, BigDecimal price) {
////        this.id = id;
//        this.name = name;
//        this.color = color;
//        this.price = price;
//    }

    private String color;

    public Flower(String color) {
        this.color = color;
    }

    public Flower() {
    }


    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

}
