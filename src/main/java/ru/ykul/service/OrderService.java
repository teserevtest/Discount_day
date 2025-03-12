package ru.ykul.service;

import ru.ykul.model.Order;
import ru.ykul.model.OrderReport;

import java.util.*;

public class OrderService {
    public OrderReport createOrderReport(List<Order> orderList, double discount, double costOfBag, double costStep, int bagWeight) {
        if (orderList.isEmpty()) {
            throw new IllegalArgumentException("Пустой лист заказов");
        }
        if (costOfBag <= 0) {
            throw new IllegalArgumentException("Стоимость единицы в заказе (мешка) быть натуральным числом больше 0");
        }
        if (bagWeight <= 0) {
            throw new IllegalArgumentException("Нельзя продать  единицу заказа весом менее 1 кг");
        }
        double cost = costOfBag / bagWeight;
        OrderReport orderReport = new OrderReport();
        for (Order order : sort(orderList)) {
            orderReport.putToMap(order.getClientName(), getPrice(order.getWeight(), discount, cost));
            if (discount >= costStep) {
                discount -= costStep;
            }
        }
        return orderReport;
    }

    List<Order> sort(List<Order> orderList) {
        Collections.sort(orderList, Comparator.comparing(Order::getDate));
        return orderList;
    }

    double getPrice(int orderWeight, double discount, double cost) {
        if (orderWeight <= 0) {
            throw new IllegalArgumentException("Вес заказа должен быть натуральным числом больше 0");
        }
        if (discount < 0 || discount > 100) {
            throw new IllegalArgumentException("Скидка должна быть от 0 до 100%");
        }

        return orderWeight * (cost * ((100 - discount) / 100.0));
    }
}


