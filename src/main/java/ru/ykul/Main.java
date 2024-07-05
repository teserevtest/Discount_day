package ru.ykul;

import ru.ykul.objects.OrderManager;
import ru.ykul.objects.OrderReport;
import ru.ykul.service.FileOrderService;
import ru.ykul.service.OrderService;

public class Main {

    private static int discount = 50;
    private static int bagWeight = 50;
    private static int cost = 500;
    private static int discountStep = 5;
    private static String inFileName = "orders/discount_day.txt";
    private static String outFileName = "orders.txt";


    public static void main(String[] args) throws Exception {
        FileOrderService fileOrderService = new FileOrderService(inFileName, outFileName);
        OrderService orderService = new OrderService(discount, cost, discountStep, bagWeight);
        OrderReport orderReport = new OrderReport();
        OrderManager orderManager = new OrderManager(orderService, fileOrderService, orderReport);

        orderManager.writeOrderReport();
    }


}