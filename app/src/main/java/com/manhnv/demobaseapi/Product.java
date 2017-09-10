package com.manhnv.demobaseapi;

import com.google.gson.annotations.SerializedName;
import com.tiny.rush.BaseModel;

/********************************************************
 * File Name : Product.java
 * Author : ManhNV
 * Date : 2017-09-10
 * Description: 
 * Last-modified by : ManhNV
 * Last-modified : 2017-09-10
 ********************************************************/


public class Product extends BaseModel {
    @SerializedName("name")
    private String name;

    public Product(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
