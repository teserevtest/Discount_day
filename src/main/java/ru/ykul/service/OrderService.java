package ru.ykul.service;

import ru.ykul.model.Order;
import ru.ykul.model.OrderReport;

import java.util.*;

public class OrderService {
    public OrderReport processing(List<Order> orderArrayList, double discount, double cost, double costStep, int bagWeight) {
        cost = cost / bagWeight;
        OrderReport orderReport = new OrderReport();
        for (var order : sort((ArrayList<Order>) orderArrayList)) {
            orderReport.putToMap(order.getClientName(), getPrice(order.getWeight(), discount, cost));
            if (discount >= costStep) {
                discount -= costStep;
            }
        }
        return orderReport;
    }

    private List<Order> sort(ArrayList<Order> orderArrayList) {
        Collections.sort(orderArrayList, Comparator.comparing(Order::getDate));
        return orderArrayList;
    }

    private double getPrice(int orderWeight, double discount, double cost) {
        return orderWeight * (cost * ((100 - discount) / 100.0));
    }
}
