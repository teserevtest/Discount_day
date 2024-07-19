package ru.ykul.manager;

import ru.ykul.model.Order;
import ru.ykul.model.OrderReport;
import ru.ykul.service.FileOrderService;
import ru.ykul.service.orderparsers.OrderParserFactory;
import ru.ykul.service.OrderService;
import ru.ykul.service.orderparsers.OrderParser;

import java.util.List;

public class OrderManager {
    private final OrderService orderService;
    private final FileOrderService fileOrderService;

    public OrderManager(OrderService orderService, FileOrderService fileOrderService) {
        this.fileOrderService = fileOrderService;
        this.orderService = orderService;
    }

    public void writeOrderReport(String inFileName, String outFileName, double discount, double cost, double discountStep, int bagWeight) {
        String[] stringOrders = fileOrderService.read(inFileName);
        OrderParser orderParser = OrderParserFactory.getOrderParser(inFileName);
        List<Order> orders = orderParser.getOrderList(stringOrders);
        OrderReport orderReport = orderService.createOrderReport(orders, discount, cost, discountStep, bagWeight);
        fileOrderService.write(orderReport, outFileName);
    }
}
