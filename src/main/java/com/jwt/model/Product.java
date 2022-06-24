package com.jwt.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="product1")
public class Product {

    @Id
    String  id;
    String name;
    String price;

    public Product(String _id, String name, String price) {
        this.id = _id;
        this.name = name;
        this.price = price;
    }

    public Product()
    {

    }

    public String get_id() {
        return id;
    }

    public void set_id(String _id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
