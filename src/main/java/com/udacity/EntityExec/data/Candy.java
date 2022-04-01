package com.udacity.EntityExec.data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "candy")
public class Candy {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    private String name;

    @Column(precision = 12, scale = 4)
    private BigDecimal price;

    @ManyToMany
    private List<Delivery> deliveries;

    // setter and getter
    public Candy(Long id, String name, BigDecimal price, List<Delivery> deliveries) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.deliveries = deliveries;
    }


    public Candy() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<Delivery> getDeliveries() {
        return deliveries;
    }

    public void setDeliveries(List<Delivery> deliveries) {
        this.deliveries = deliveries;
    }
}
