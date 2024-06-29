package ru.ykul.service;

import ru.ykul.objects.Order;

import java.util.*;

public class OrderService {
    private double discount;
    private double cost;
    private double costStep;

    public void setCostStep(double costStep) {      this.costStep = costStep;    }

    private void setDiscount(double discount) {
        this.discount = discount;
    }

    private void setCost(double cost) {
        //make cost per kilogram from cost of bag 50 kg
        this.cost = cost / 50;
    }

    public OrderService(double discount, double cost, double costStep) {
        setCost(cost);
        setDiscount(discount);
        setCostStep(costStep);
    }

    private ArrayList<Order> sort(ArrayList<Order> orderArrayList) {
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

    public Map processing(ArrayList<Order> orderArrayList) {
        Map<String, Double> orderMap = new LinkedHashMap<>();


        for (var order : sort(orderArrayList)) {
            orderMap.put(order.getClientName(), getPrice(order.getWeight()));
        }

        return orderMap;
    }

}
