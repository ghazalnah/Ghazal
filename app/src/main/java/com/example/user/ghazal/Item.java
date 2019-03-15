package com.example.user.ghazal;

import java.io.Serializable;
//add comment
public class Item implements Serializable{
    private String name;
    private String category;
    private int expenses;
    private int image;
    private String key;

    public  Item(){
        super();
    }
    public Item(String name,String category, int expenses) {

        this.name = name;
        this.category = category;
        this.expenses = expenses;
    }

    public void setKey(String key) {
        this.key = key;
    }
    public String getKey(){
        return this.key;
    }
    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public int getExpenses() {
        return expenses;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", price=" + expenses +
                '}';
    }
}
