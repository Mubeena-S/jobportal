package com.example.jobportal.service;

class Item {
    private String name;
    private int quantity;
    private double price;

    public Item(String name, int quantity, double price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public double getTotalPrice() {
        return quantity * price;
    }

    public String toString() {
        return name + " | Qty: " + quantity + " | Price: $" + price + " | Total: $" + getTotalPrice();
    }
}
