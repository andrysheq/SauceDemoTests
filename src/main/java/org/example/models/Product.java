package org.example.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Product {
    public static List<Product> expectedProductsList = new ArrayList<>();
    private String price;
    private String title;
    private String desc;
    private String buttonText;

    public Product(String price, String title, String desc) {
        this.price = price;
        this.title = title;
        this.desc = desc;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getButtonText() {
        return buttonText;
    }

    public void setButtonText(String buttonText) {
        this.buttonText = buttonText;
    }

    @Override
    public String toString(){
        return title + ", " + desc + ", " + price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return title.equals(product.getTitle()) &&
                price.equals(product.getPrice()) &&
                desc.equals(product.getDesc());
    }
}
