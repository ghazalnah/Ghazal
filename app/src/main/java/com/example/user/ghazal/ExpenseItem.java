package com.example.user.ghazal;

public class ExpenseItem {
    private String name;
    private double price;
    private String category;

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "ExpenseItem{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                '}';
    }
}
