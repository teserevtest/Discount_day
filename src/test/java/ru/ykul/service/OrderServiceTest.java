package ru.ykul.service;

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

    @Test
    @org.junit.jupiter.api.Order(0)
    @DisplayName("0. createOrderReport - single report")
    void OrderReport_createOrderReport_shouldReturnOrderReportWithSingleString() {
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
        assertEquals(((clientName + " - " + orderCost * ((100 - discount) / 100)) + "\n"), orderReport.toString());
    }

    @Test
    @org.junit.jupiter.api.Order(1)
    @DisplayName("1. createOrderReport - empty list")
    void orderService_createOrderService_shouldReturnErrorIfReportEmpty() {
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
    @DisplayName("2. createOrderReport - normal report")
    void OrderReport_createOrderReport_shouldReturnOrderReportWithThreeClients() {
        List<Order> orderList = new ArrayList<>();
        String clientName = "Industrial";
        Double orderCost = 79200.0;
        String orderData = "2021-02-09T16:00:22";
        orderList.add(new Order(LocalDateTime.parse(orderData), clientName, orderCost.intValue()));
        String clientName1 = "Pyramid";
        Double orderCost1 = 18200.0;
        String orderData1 = "2021-02-09T16:05:22";
        orderList.add(new Order(LocalDateTime.parse(orderData1), clientName1, orderCost1.intValue()));
        String clientName2 = "Fossil";
        Double orderCost2 = 36000.0;
        String orderData2 = "2021-02-09T16:10:22";
        orderList.add(new Order(LocalDateTime.parse(orderData2), clientName2, orderCost2.intValue()));
        String clientName3 = "Carryover";
        Double orderCost3 = 36000.0;
        String orderData3 = "2021-02-09T16:15:22";
        orderList.add(new Order(LocalDateTime.parse(orderData3), clientName3, orderCost3.intValue()));
        double discount = 5;
        double costOfBag = 50;
        double costStep = 5;
        int bagWeight = 50;
        OrderReport orderReport = orderService.createOrderReport(orderList, discount, costOfBag, costStep, bagWeight);
        assertEquals((((clientName + " - " + orderCost * ((100 - discount) / 100)) + "\n") +
                ((clientName1 + " - " + orderCost1 * ((100 - (discount - costStep)) / 100)) + "\n") +
                ((clientName2 + " - " + orderCost2 + "\n")) +
                ((clientName3 + " - " + orderCost3 + "\n"))), orderReport.toString());
    }

    @Test
    @org.junit.jupiter.api.Order(3)
    @DisplayName("3. createOrderReport - zero discount report")
    void OrderReport_createOrderReport_shouldReturnOrderReportWithZeroDiscount() {
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
    @DisplayName("4. createOrderReport - zero costOfBag report")
    void OrderReport_createOrderReport_shouldReturnOrderReportWithZeroCostOfBag() {
        List<Order> orderList = new ArrayList<>();
        String clientName = "Industrial";
        Double orderCost = 79200.0;
        String orderData = "2021-02-09T16:00:22";
        orderList.add(new Order(LocalDateTime.parse(orderData), clientName, orderCost.intValue()));
        double discount = 50;
        double costOfBag = 0;
        double costStep = 5;
        int bagWeight = 50;
        assertThrows(IllegalArgumentException.class, () -> {
            orderService.createOrderReport(orderList, discount, costOfBag, costStep, bagWeight);
        });
    }

    @Test
    @org.junit.jupiter.api.Order(5)
    @DisplayName("5. createOrderReport - negative costOfBag report")
    void OrderReport_createOrderReport_shouldReturnOrderReportWithNegativeCostOfBag() {
        List<Order> orderList = new ArrayList<>();
        String clientName = "Industrial";
        Double orderCost = 79200.0;
        String orderData = "2021-02-09T16:00:22";
        orderList.add(new Order(LocalDateTime.parse(orderData), clientName, orderCost.intValue()));
        double discount = 50;
        double costOfBag = -1;
        double costStep = 5;
        int bagWeight = 50;
        assertThrows(IllegalArgumentException.class, () -> {
            orderService.createOrderReport(orderList, discount, costOfBag, costStep, bagWeight);
        });
    }

    @Test
    @org.junit.jupiter.api.Order(6)
    @DisplayName("6. sort - empty sort")
    public void orderService_sort_EmptyList() {
        List<Order> orderList = Collections.emptyList();
        List<Order> sortedOrders = orderService.sort(orderList);
        assert sortedOrders.isEmpty();
    }

    @Test
    @org.junit.jupiter.api.Order(7)
    @DisplayName("7. sort - single sort")
    public void orderService_sort_SingleElementList() {
        List<Order> orderList = new ArrayList<>();
        String clientName = "Industrial";
        Double orderCost = 79200.0;
        String orderData = "2021-02-09T16:00:22";
        orderList.add(new Order(LocalDateTime.parse(orderData), clientName, orderCost.intValue()));
        List<Order> sortedOrders = orderService.sort(orderList);
        double discount = 0;
        assertEquals(1, sortedOrders.size());
        assertEquals(clientName, sortedOrders.get(0).getClientName());
    }

    @Test
    @org.junit.jupiter.api.Order(8)
    @DisplayName("8. sort - normal sort")
    public void orderService_sort_MultipleElementsList() {
        OrderService orderService = new OrderService();
        List<Order> orderList = new ArrayList<>();
        String clientName = "Industrial";
        Double orderCost = 79200.0;
        String orderData = "2021-02-09T16:00:22";
        orderList.add(new Order(LocalDateTime.parse(orderData), clientName, orderCost.intValue()));
        String clientName1 = "Pyramid";
        Double orderCost1 = 18200.0;
        String orderData1 = "2021-02-09T16:01:22";
        orderList.add(new Order(LocalDateTime.parse(orderData1), clientName1, orderCost1.intValue()));
        List<Order> sortedOrders = orderService.sort(orderList);
        assertEquals(2, sortedOrders.size());
        assertEquals(clientName, sortedOrders.get(0).getClientName());
        assertEquals(clientName1, sortedOrders.get(1).getClientName());
    }

    @Test
    @org.junit.jupiter.api.Order(9)
    @DisplayName("9. getPrice - 0% discount")
    void orderService_getPrice_NoDiscount() {
        OrderService orderService = new OrderService();
        double result = orderService.getPrice(10, 0, 50.0);
        assertEquals(500.0, result, 0.001);
    }

    @Test
    @org.junit.jupiter.api.Order(10)
    @DisplayName("10. getPrice - 20% discount")
    void orderService_getPrice_WithDiscount() {
        OrderService orderService = new OrderService();
        double result = orderService.getPrice(10, 20, 50.0);
        assertEquals(400.0, result, 0.001);
    }

    @Test
    @org.junit.jupiter.api.Order(11)
    @DisplayName("11. getPrice - 100% discount")
    void orderService_getPrice_FullDiscount() {
        OrderService orderService = new OrderService();
        double result = orderService.getPrice(10, 100, 50.0);
        assertEquals(0.0, result, 0.001);
    }

    @Test
    @org.junit.jupiter.api.Order(12)
    @DisplayName("12. getPrice - negative discount")
    void orderService_getPrice_NegativeDiscount() {
        OrderService orderService = new OrderService();
        assertThrows(IllegalArgumentException.class, () -> {
            orderService.getPrice(10, -10, 50.0);
        });
    }

    @Test
    @org.junit.jupiter.api.Order(13)
    @DisplayName("13. getPrice - no weight")
    void orderService_getPrice_ZeroWeight() {
        OrderService orderService = new OrderService();
        assertThrows(IllegalArgumentException.class, () -> {
            orderService.getPrice(0, 10, 50.0);
        });
    }

    @Test
    @org.junit.jupiter.api.Order(14)
    @DisplayName("14. getPrice - negative weight")
    void orderService_getPrice_NegativeWeight() {
        OrderService orderService = new OrderService();
        assertThrows(IllegalArgumentException.class, () -> {
            orderService.getPrice(-10, 10, 50.0);
        });
    }


}