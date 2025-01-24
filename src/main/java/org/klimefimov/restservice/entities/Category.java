package org.klimefimov.restservice.entities;

import org.klimefimov.restservice.util.CategoryType;

import java.util.List;

public class Category {
    private int id;
    private String name;
    private CategoryType type;
//    private List<Product> products; // (ManyToMany)

//    public Category() {
//        this.products = null;  // Fix
//    }

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

    public CategoryType getType() {
        return type;
    }

    public void setType(CategoryType type) {
        this.type = type;
    }

//    public List<Product> getProducts() {
//        return products;
//    }

//    public void setProducts(List<Product> products) {
//        this.products = products;
//    }


    @Override
    public String toString() {
        return super.toString();
    }
}
