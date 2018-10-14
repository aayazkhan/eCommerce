
package com.heady.ecommerce.example.model;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class Tax implements Serializable {

    @Expose
    private String name;
    @Expose
    private Double value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

}
