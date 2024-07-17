package ru.ykul.service.orderparsers;

import ru.ykul.model.Order;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderParserByPipe implements OrderParser {

    public List<Order> getOrderList(String[] stringOrders) {
        List<Order> orders = new ArrayList<>();
        for (String s : stringOrders) {
            orders.add(parseStringToOrder(s));
        }
        return orders;
    }

    private Order parseStringToOrder(String string) {
        String[] parts = string.split("\\|");
        return new Order(LocalDateTime.parse(parts[0]), parts[1], Integer.parseInt(parts[2]));
    }
}
