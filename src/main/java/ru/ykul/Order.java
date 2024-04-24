package ru.ykul;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

public class Order implements Comparable<Order> {
    LocalDateTime orderData;
    String counteragent;
    int orderWeight;
    double orderCount;

    public Order(LocalDateTime orderData, String counteragent, int orderWeight) {
        this.orderData = orderData;
        this.counteragent = counteragent;
        this.orderWeight = orderWeight;
    }

    public int getOrderWeight() {
        return orderWeight;
    }

    public double getOrderCount() {
        return orderCount;
    }

    public String getCounteragent() {
        return counteragent;
    }

    public LocalDateTime getOrderDate() {
        return orderData;
    }

    public void setOrderCount(double orderCount) {
        this.orderCount = orderCount;
    }


    //compare by order date
    @Override
    public int compareTo(Order o) {
        return this.getOrderDate().compareTo(o.getOrderDate());
    }

}
