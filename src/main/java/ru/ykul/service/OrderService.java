package ru.ykul.service;

import ru.ykul.objects.Order;

import java.util.*;

public class OrderService {
    private double discount;
    private double cost;


    public double getDiscount() {
        return discount;
    }

    private void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getCost() {
        return cost;
    }

    private void setCost(double cost) {
        //make cost per kilogram from cost of bag 50 kg
        this.cost = cost/50;
    }

    public OrderService(double discount, double cost) {
        setCost(cost);setDiscount(discount);
    }

    private ArrayList<Order> sort(ArrayList<Order> orderArrayList) {
        Collections.sort(orderArrayList, Comparator.comparing(Order::getDate));

        return orderArrayList;

    }



    private double getPrice(int orderWeight) {
        double result = orderWeight * (cost * ((100 - discount) / 100.0));
        if (discount >= 5) {
            discount -= 5;
        }
        return result;

    }

    public Map processing(ArrayList<Order> orderArrayList) {
        Map<Order,Double> orderMap = new LinkedHashMap<>();


        for (var order:sort(orderArrayList)){
            orderMap.put(order,getPrice(order.getWeight()));
        }

        return orderMap;
    }

}
