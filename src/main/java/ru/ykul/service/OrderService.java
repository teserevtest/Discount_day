package ru.ykul.service;

import ru.ykul.objects.Order;

import java.util.*;

public class OrderService {







    public ArrayList<Order> calculateCosts(ArrayList<Order> orderArrayList) {
        Collections.sort(orderArrayList, Comparator.comparing(Order::getDate));

        return orderArrayList;
    }
    private double formCount(double discount,double costOfKilogram, int orderWeight){
        double result =orderWeight * costOfKilogram * ((100 - discount) / 100.0);
        if (discount >= 5) {
            discount -= 5;
        }
        return result;

    }

}
