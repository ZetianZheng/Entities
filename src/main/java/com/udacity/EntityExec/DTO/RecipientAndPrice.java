package com.udacity.EntityExec.DTO;

import javax.persistence.Entity;
import java.math.BigDecimal;

public class RecipientAndPrice {
    String name;
    BigDecimal price;

    //You'll probably need a constructor like this so CriteriaBuilder can create
    public RecipientAndPrice(String recipientName, BigDecimal price) {
        this.name = recipientName;
        this.price = price;
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
}
