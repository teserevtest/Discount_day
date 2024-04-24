package ru.ykul;

import java.util.*;

public class OrderService {
    public int discount = 50;
    public int cost = 500;
    public int bagWeight = 50;

    public ArrayList<Order> calculateCosts(ArrayList<Order> orderArrayList) {
        Collections.sort(orderArrayList, Comparator.comparing(Order::getOrderDate));

        for (Order order : orderArrayList) {

            order.setOrderCount((order.getOrderWeight() / bagWeight) * cost * ((100 - discount) / 100.0));

            if (discount >= 5) {
                discount -= 5;
            }
        }

        return orderArrayList;
    }

}
