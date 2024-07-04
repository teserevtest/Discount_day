package ru.ykul.objects;

import ru.ykul.service.FileOrderService;
import ru.ykul.service.OrderService;

import java.util.ArrayList;

public class OrderManager {


    private static int discount;
    private static int cost;
    private static int discountStep;
    private static String inFileName;
    private static String outFileName;

    public OrderManager(int discount, int cost, int discountStep, String inFileName, String outFileName) {
        setDiscount(discount);
        setCost(cost);
        setCostStep(discountStep);
        setInFileName(inFileName);
        setOutFileName(outFileName);
    }

    public void writeOrderReport() throws Exception {
        FileOrderService fileOrderService = new FileOrderService();
        OrderService orderService = new OrderService(discount, cost, discountStep);

        fileOrderService.write(
                orderService.processing(
                        (ArrayList<Order>) fileOrderService.read(inFileName)),
                outFileName);
    }


    private void setDiscount(int discount) {
        this.discount = discount;
    }

    private void setCost(int cost) {
        this.cost = cost;
    }

    private void setCostStep(int discountStep) {
        this.discountStep = discountStep;
    }

    private void setInFileName(String inFileName) {
        this.inFileName = inFileName;
    }

    private void setOutFileName(String outFileName) {
        this.outFileName = outFileName;
    }


}
