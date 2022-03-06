package com.udacity.EntityExec.data;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class Shrub extends Plant{
    private float height;
    private float width;

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }
}
