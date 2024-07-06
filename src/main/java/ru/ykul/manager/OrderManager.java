package ru.ykul.manager;

import ru.ykul.model.Order;
import ru.ykul.model.OrderReport;
import ru.ykul.service.FileOrderService;
import ru.ykul.service.OrderService;

import java.util.ArrayList;
import java.util.List;

public class OrderManager {


    private final OrderService orderService;
    private final FileOrderService fileOrderService;
    private OrderReport orderReport;

    public OrderManager(OrderService orderService, FileOrderService fileOrderService, OrderReport orderReport) {
        this.fileOrderService = fileOrderService;
        this.orderService = orderService;
        this.orderReport = orderReport;
    }

    public void writeOrderReport(String inFileName, String outFileName) throws Exception {

        List<Order> orderlist = fileOrderService.read(inFileName);
        orderReport = orderService.processing((ArrayList<Order>) orderlist, orderReport);
        fileOrderService.write(orderReport,outFileName);
    }
}
