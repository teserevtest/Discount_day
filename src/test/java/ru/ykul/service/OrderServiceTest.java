package ru.ykul.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.ykul.model.Order;
import ru.ykul.model.OrderReport;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class OrderServiceTest {
    final OrderService orderService = new OrderService();

    @BeforeEach
    void cleanUpList() {
        List<Order> orderList = Collections.emptyList();
        OrderReport orderReport = null;
    }

//    @Test
//    @DisplayName("0. empty list error")
//    void orderService_createOrderService_shouldReturn() {
//        String inFileName = "discount_day.txt";
//        FileOrderService fileOrderService = new FileOrderService();
//        OrderParser orderParser = getOrderParser(inFileName);
//        String[] stringOrders = fileOrderService.read(inFileName);
//        List<Order> orders = orderParser.getOrderList(stringOrders);
//        OrderReport orderReport = new OrderReport();
//
//
//        int discount = 50;
//        int bagWeight = 50;
//        int cost = 500;
//        int discountStep = 5;
////        when(orderServiceMock.createOrderReport(orders, discount, cost, discountStep, bagWeight)).thenReturn(null);
////        OrderReport orderReport1 = orderServiceMock.createOrderReport(orders, discount, cost, discountStep, bagWeight);
////        System.out.println(orderReport1);
////        String test = orderServiceMock.test();
////        System.out.println(test);
//        when(orderServiceMock.test()).thenReturn("test");
//    }


    @Test
    @org.junit.jupiter.api.Order(0)
    @DisplayName("0. single report")
    void OrderReport_createOrderReport_shouldReturnOrderReportWithSingleString() {
        OrderService orderService = new OrderService();
        List<Order> orderList = new ArrayList<>();
        String clientName = "Industrial";
        Double orderCost = 79200.0;
        String orderData = "2021-02-09T16:00:22";
        orderList.add(new Order(LocalDateTime.parse(orderData), clientName, orderCost.intValue()));


        double discount = 50;
        double costOfBag = 50;
        double costStep = 5;
        int bagWeight = 50;

        OrderReport orderReport = orderService.createOrderReport(orderList, discount, costOfBag, costStep, bagWeight);

        assertEquals(orderReport.toString(), (clientName + " - " + orderCost * ((100 - discount) / 100)) + "\n");
    }


    //1. **Проверка на пустой список заказов**:
//     - Входные данные: `orderList = []`, `discount = 0`, `costOfBag = 5`, `costStep = 10`, `bagWeight = 1`
//     - Ожидаемый результат: Пустой отчет или сообщение о том, что нет заказов.
    @Test
    @org.junit.jupiter.api.Order(1)
    @DisplayName("1. empty list")
    void orderService_createOrderService_shouldReturnErrorIfReportEmpty() {
        OrderService orderService = new OrderService();
        List<Order> orderList = Collections.emptyList();
        double discount = 50;
        double costOfBag = 50;
        double costStep = 5;
        int bagWeight = 50;
        assertThrows(IllegalArgumentException.class, () -> {
            orderService.createOrderReport(orderList, discount, costOfBag, costStep, bagWeight);
        });
    }

    @Test
    @org.junit.jupiter.api.Order(2)
    @DisplayName("2. normal report")
    void OrderReport_createOrderReport_shouldReturnOrderReportWithTwoClients() {
        List<Order> orderList = new ArrayList<>();
        String clientName = "Industrial";
        Double orderCost = 79200.0;
        String orderData = "2021-02-09T16:00:22";
        orderList.add(new Order(LocalDateTime.parse(orderData), clientName, orderCost.intValue()));
        String clientName1 = "Pyramid";
        Double orderCost1 = 18200.0;
        String orderData1 = "2021-02-09T16:01:22";
        orderList.add(new Order(LocalDateTime.parse(orderData1), clientName1, orderCost1.intValue()));


        double discount = 0;
        double costOfBag = 50;
        double costStep = 5;
        int bagWeight = 50;

        OrderReport orderReport = orderService.createOrderReport(orderList, discount, costOfBag, costStep, bagWeight);

        assertEquals(orderReport.toString(), ((clientName + " - " + orderCost * ((100 - discount) / 100)) + "\n") + ((clientName1 + " - " + orderCost1 * ((100 - discount) / 100)) + "\n"));
    }

    @Test
    @org.junit.jupiter.api.Order(3)
    @DisplayName("3. zero discount report")
    void OrderReport_createOrderReport_shouldReturnOrderReportWithZeroDiscount() {
        OrderService orderService = new OrderService();
        List<Order> orderList = new ArrayList<>();
        String clientName = "Industrial";
        Double orderCost = 79200.0;
        String orderData = "2021-02-09T16:00:22";
        orderList.add(new Order(LocalDateTime.parse(orderData), clientName, orderCost.intValue()));


        double discount = 0;
        double costOfBag = 50;
        double costStep = 5;
        int bagWeight = 50;

        OrderReport orderReport = orderService.createOrderReport(orderList, discount, costOfBag, costStep, bagWeight);

        assertEquals(orderReport.toString(), (clientName + " - " + orderCost * ((100 - discount) / 100)) + "\n");
    }

    @Test
    @org.junit.jupiter.api.Order(4)
    @DisplayName("4. zero costOfBag report")
    void OrderReport_createOrderReport_shouldReturnOrderReportWithZeroCostOfBag() {
        OrderService orderService = new OrderService();
        List<Order> orderList = new ArrayList<>();
        String clientName = "Industrial";
        Double orderCost = 79200.0;
        String orderData = "2021-02-09T16:00:22";
        orderList.add(new Order(LocalDateTime.parse(orderData), clientName, orderCost.intValue()));


        double discount = 50;
        double costOfBag = 0;
        double costStep = 5;
        int bagWeight = 50;

        OrderReport orderReport = orderService.createOrderReport(orderList, discount, costOfBag, costStep, bagWeight);

        assertEquals(orderReport.toString(), (clientName + " - " + orderCost * ((100 - discount) / 100)) + "\n");
    }


//7. **Проверка с отрицательной скидкой**:
    // - Входные данные: `orderList = [Order(amount=100)]`, `discount = -5`, `costOfBag = 5`, `costStep = 10`, `bagWeight = 1`
    // - Ожидаемый результат: Ошибка или обработка отрицательной скидки (например, игнорирование).

//8. **Проверка с большим весом сумки**:
    // - Входные данные: `orderList = [Order(amount=100)]`, `discount = 0`, `costOfBag = 5`, `costStep = 10`, `bagWeight = 100`
    // - Ожидаемый результат: Общая стоимость должна увеличиться в зависимости от веса сумки.

//9. **Проверка с нулевой стоимостью сумки**:
    // - Входные данные: `orderList = [Order(amount=100)]`, `discount = 0`, `costOfBag = 0`, `costStep = 10`, `bagWeight = 1`
    // - Ожидаемый результат: Стоимость заказа без добавления за сумку.

    // 1//0. **Проверка с изменением стоимости шага**:
    // - Входные данные: `orderList = [Order(amount=100)]`, `discount = 0`, `costOfBag = 5`, `costStep = 20`, `bagWeight = 1`
    // - Ожидаемый результат: Общая стоимость должна измениться в зависимости от изменения стоимости шага.

//    @Test
//    @DisplayName("0. empty sort")
//    public void orderService_sort_EmptyList() {
//        OrderService orderService = new OrderService();
//        List<Order> orderList = Collections.emptyList();
//        List<Order> sortedOrders = orderService.sort(orderList);
//        assert sortedOrders.isEmpty();
//    }
//
//    @Test
//    @DisplayName("0. single sort")
//    public void orderService_sort_SingleElementList() {
//        OrderService orderService = new OrderService();
//        Order order1 = new Order("A", "2023-01-01");
//        List<Order> orderList = Arrays.asList(order1);
//        List<Order> sortedOrders = orderService.sort(orderList);
//        assert sortedOrders.size() == 1;
//        assert sortedOrders.get(0).equals(order1);
//    }
//
//    @Test
//    @DisplayName("0. normal sort")
//    public void orderService_sort_MultipleElementsList() {
//        OrderService orderService = new OrderService();
//        Order order1 = new Order("A", "2023-01-01");
//        Order order2 = new Order("B", "2022-12-31");
//        List<Order> orderList = Arrays.asList(order1, order2);
//        List<Order> sortedOrders = orderService.sort(orderList);
//        assert sortedOrders.size() == 2;
//        assert sortedOrders.get(0).equals(order2) && sortedOrders.get(1).equals(order1);
//    }
//


//    @Test
//    @DisplayName("0. 0% discount")
//    void orderService_getPrice_NoDiscount() {
//        OrderService orderService = new OrderService();
//        double result = orderService.getPrice(10, 0, 50.0);
//        assertEquals(500.0, result, 0.001);
//    }
//
//    @Test
//    @DisplayName("0. 20% discount")
//    void orderService_getPrice_WithDiscount() {
//        OrderService orderService = new OrderService();
//        double result = orderService.getPrice(10, 20, 50.0);
//        assertEquals(400.0, result, 0.001);
//    }
//
//    @Test
//    @DisplayName("0. 100% discount")
//    void orderService_getPrice_FullDiscount() {
//        OrderService orderService = new OrderService();
//        double result = orderService.getPrice(10, 100, 50.0);
//        assertEquals(0.0, result, 0.001);
//    }
//
//    @Test
//    @DisplayName("0. negative discount")
//    void orderService_getPrice_NegativeDiscount() {
//        OrderService orderService = new OrderService();
//        assertThrows(IllegalArgumentException.class, () -> {
//            orderService.getPrice(10, -10, 50.0);
//        });
//    }
//
//    @Test
//    @DisplayName("0. no weight")
//    void orderService_getPrice_ZeroWeight() {
//        OrderService orderService = new OrderService();
//        double result = orderService.getPrice(0, 10, 50.0);
//        assertEquals(0.0, result, 0.001);
//    }
//
//    @Test
//    @DisplayName("0. negative weight")
//    void orderService_getPrice_NegativeWeight() {
//        OrderService orderService = new OrderService();
//        assertThrows(IllegalArgumentException.class, () -> {
//            orderService.getPrice(-10, 10, 50.0);
//        });
//    }


}