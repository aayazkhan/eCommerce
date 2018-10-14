
package com.heady.ecommerce.example.model;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class Variant implements Serializable {

    @Expose
    private String color;
    @Expose
    private Long id;
    @Expose
    private Long price;
    @Expose
    private Long size;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

}
