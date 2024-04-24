package ru.ykul;

public class Main {
    public static void main(String[] args) {
        OrderService orderService = new OrderService();
        FileOrderService fileOrderService = new FileOrderService();

        fileOrderService.writeOrdersToFile(orderService.calculateCosts(fileOrderService.getOrdersListFromFile()));
    }
}