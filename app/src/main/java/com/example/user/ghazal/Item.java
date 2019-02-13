package com.example.user.ghazal;

import java.io.Serializable;
//add comment
public class Item implements Serializable{
    private String name;
    private String category;
    private int expenses;



    public Item(String name,String category, int expenses) {

        this.name = name;
        this.category = category;
        this.expenses = expenses;
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

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPrice(int price) {
        this.expenses = expenses;
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
