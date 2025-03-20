package ru.ykul.manager;

import ru.ykul.model.Order;
import ru.ykul.model.OrderReport;
import ru.ykul.service.FileOrderService;
import ru.ykul.service.orderparsers.OrderParserFactory;
import ru.ykul.service.OrderService;
import ru.ykul.service.orderparsers.OrderParser;

import java.io.IOException;
import java.util.List;

public class OrderManager {
    private final OrderService orderService;
    private final FileOrderService fileOrderService;
    private  final OrderParserFactory orderParserFactory;

    public OrderManager(OrderService orderService, FileOrderService fileOrderService,OrderParserFactory orderParserFactory) {
        this.fileOrderService = fileOrderService;
        this.orderService = orderService;
        this.orderParserFactory = orderParserFactory;
    }

    public void writeOrderReport(String inFileName, String outFileName, double discount, double cost, double discountStep, int bagWeight) throws IOException {
        OrderParser orderParser = orderParserFactory.getOrderParser(inFileName);
        String[] stringOrders = fileOrderService.read(inFileName);
        List<Order> orders = orderParser.getOrderList(stringOrders);
        OrderReport orderReport = orderService.createOrderReport(orders, discount, cost, discountStep, bagWeight);
        fileOrderService.write(orderReport, outFileName);
    }
}
