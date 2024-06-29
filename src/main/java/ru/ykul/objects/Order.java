package ru.ykul.objects;

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

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
