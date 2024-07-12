package ru.ykul.manager;

import ru.ykul.model.Order;
import ru.ykul.model.OrderReport;
import ru.ykul.service.FileOrderService;
import ru.ykul.service.OrderService;

import java.util.List;

public class OrderManager {
    private final OrderService orderService;
    private final FileOrderService fileOrderService;

    public OrderManager(OrderService orderService, FileOrderService fileOrderService) {
        this.fileOrderService = fileOrderService;
        this.orderService = orderService;
    }

    public void writeOrderReport(String inFileName, String outFileName, double discount, double cost, double discountStep, int bagWeight) {
        List<Order> orderlist = fileOrderService.read(inFileName);
        OrderReport orderReport = orderService.createOrderReport(orderlist, discount, cost, discountStep, bagWeight);
        fileOrderService.write(orderReport, outFileName);
    }
}
