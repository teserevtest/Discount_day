package ru.ykul;

import ru.ykul.objects.OrderManager;

public class Main {

    private static int discount = 50;
    private static int cost = 500;
    private static int discountStep = 5;
    private static String inFileName = "/discount_day.txt";
    private static String outFileName = "/orders.txt";


    public static void main(String[] args) throws Exception {
       OrderManager orderManager = new OrderManager(discount, cost, discountStep, inFileName, outFileName);
       orderManager.getOrdersCosts();
    }


}