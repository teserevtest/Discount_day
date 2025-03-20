package ru.ykul;

import ru.ykul.manager.OrderManager;
import ru.ykul.model.OrderReport;
import ru.ykul.service.FileOrderService;
import ru.ykul.service.OrderService;
import ru.ykul.service.orderparsers.OrderParserFactory;

import java.io.IOException;

public class Main {
    private static int discount = 50;
    private static int bagWeight = 50;
    private static int cost = 500;
    private static int discountStep = 5;
    private static String inFileName = "discount_day.txt";
    private static String outFileName = "orders.txt";

    public static void main(String[] args) throws IOException {
        FileOrderService fileOrderService = new FileOrderService();
        OrderService orderService = new OrderService();
        OrderParserFactory orderParserFactory = new OrderParserFactory();
        OrderManager orderManager = new OrderManager(orderService, fileOrderService, orderParserFactory);

        orderManager.writeOrderReport(inFileName, outFileName, discount, cost, discountStep, bagWeight);
    }
}