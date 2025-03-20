package ru.ykul.service.orderparsers;

import ru.ykul.model.Order;

import java.util.List;

public interface OrderParser {
    List<Order> getOrderList(String[] stringOrders);
}
