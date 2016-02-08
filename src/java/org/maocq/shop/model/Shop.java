package org.maocq.shop.model;

import java.util.ArrayList;


public class Shop {

    private int id;
    private String name;
    private String location;
    
    private ArrayList<Product> products;
    
    public Shop(){}

    public Shop(int id, String name, String location) {
        this.id = id;
        this.name = name;
        this.location = location;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }
    
}
