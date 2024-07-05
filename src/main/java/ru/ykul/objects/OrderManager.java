package ru.ykul.objects;

import ru.ykul.service.FileOrderService;
import ru.ykul.service.OrderService;

import java.util.ArrayList;

public class OrderManager {
    public OrderManager(OrderService orderService, FileOrderService fileOrderService, OrderReport orderReport) {
        this.fileOrderService = fileOrderService;
        this.orderService = orderService;
        this.orderReport = orderReport;
    }

    private OrderService orderService;
    private FileOrderService fileOrderService;
    private OrderReport orderReport;


    public void writeOrderReport() throws Exception {
        fileOrderService.write(
                orderService.processing(
                        (ArrayList<Order>) fileOrderService.read(), orderReport));
    }
}
