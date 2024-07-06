package ru.ykul.service;

import ru.ykul.model.Order;
import ru.ykul.model.OrderReport;

import java.util.*;

public class OrderService {

    private double discount;
    private double cost;
    private double costStep;

    public OrderService(double discount, double cost, double costStep, int bagWeight) {
        this.cost = cost / bagWeight;
        this.discount = discount;
        this.costStep = costStep;
    }

    public OrderReport processing(ArrayList<Order> orderArrayList, OrderReport orderReport) {

        for (var order : sort(orderArrayList)) {
            orderReport.putToMap(order.getClientName(), getPrice(order.getWeight()));
        }
        return orderReport;
    }

    private List<Order> sort(ArrayList<Order> orderArrayList) {
        Collections.sort(orderArrayList, Comparator.comparing(Order::getDate));
        return orderArrayList;
    }

    private double getPrice(int orderWeight) {
        double result = orderWeight * (cost * ((100 - discount) / 100.0));
        if (discount >= costStep) {
            discount -= costStep;
        }
        return result;
    }
}
