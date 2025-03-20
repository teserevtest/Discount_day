package ru.ykul.model;

import java.time.LocalDateTime;

public class Order {
    private LocalDateTime date;
    private String clientName;
    private int weight;

    public Order(LocalDateTime orderData, String clientName, int orderWeight) {
        this.date = orderData;
        this.clientName = clientName;
        this.weight = orderWeight;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getClientName() {
        return clientName;
    }

    public int getWeight() {
        return weight;
    }
}
