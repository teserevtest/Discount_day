package ru.ykul.service.orderparsers;

import ru.ykul.model.Order;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class OrderParserByPipe implements OrderParser {

    public List<Order> getOrderList(String[] stringOrders) {
        return Arrays.stream(stringOrders).map(this::parseStringToOrder).collect(Collectors.toList());
    }

    private Order parseStringToOrder(String string) {
        String[] parts = string.split("\\|");
        return new Order(LocalDateTime.parse(parts[0]), parts[1], Integer.parseInt(parts[2]));
    }
}
