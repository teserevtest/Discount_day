package ru.ykul.objects;

import ru.ykul.service.FileOrderService;
import ru.ykul.service.OrderService;

public class OrderManager {




    String inputFile = "discount_day.txt";

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getBagWeight() {
        return bagWeight;
    }
    public void setBagWeight(int bagWeight) {
        this.bagWeight = bagWeight;
    }



    OrderService orderService = new OrderService();

//    FileOrderService fileOrderService = new FileOrderService(orderService.toString());

//        fileOrderService.writeOrdersToFile(orderService.calculateCosts(fileOrderService.getOrdersListFromFile(inputFile)));
}
