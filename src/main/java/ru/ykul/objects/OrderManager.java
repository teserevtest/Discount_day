package ru.ykul.objects;

import ru.ykul.service.FileOrderService;
import ru.ykul.service.OrderService;

import java.util.ArrayList;

public class OrderManager {
    private final Options options;
    public OrderManager(Options options) throws Exception {

        this.options = options;


        FileOrderService fileOrderService = new FileOrderService(options.getInFileName(),options.getOutFileName());
        OrderService orderService = new OrderService(options.getDiscount(),options.getCost());

        fileOrderService.write(orderService.processing((ArrayList<Order>) fileOrderService.read()));




    }
}
