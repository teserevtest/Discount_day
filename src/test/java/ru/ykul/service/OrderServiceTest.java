package ru.ykul.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.ykul.model.Order;
import ru.ykul.model.OrderReport;
import ru.ykul.service.orderparsers.OrderParser;
import ru.ykul.service.orderparsers.OrderParserByOctothorpe;
import ru.ykul.service.orderparsers.OrderParserByPipe;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {
    private final OrderService orderService = new OrderService();
    @Mock
    private OrderService orderServiceMock;


    private OrderParser getOrderParser(String filename) {
        String end = filename.substring(filename.lastIndexOf("."));
        if (end == null) {
            return new OrderParserByOctothorpe();
        } else if (end.equals(".txt")) {
            return new OrderParserByPipe();
        } else {
            throw new IllegalArgumentException("Input file format not supported!");
        }
    }

    @Mock
    private List<Order> orderList;

    @Mock
    private OrderReport orderReport;

    @InjectMocks
    private OrderService orderService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateOrderReport() {
        // Arrange
        double discount = 10.0;
        double costOfBag = 20.0;
        double costStep = 2.0;
        int bagWeight = 5;
        Order order1 = new Order(1, "Client1", 3);
        Order order2 = new Order(2, "Client2", 4);
        when(orderList.get(0)).thenReturn(order1);
        when(orderList.get(1)).thenReturn(order2);
        when(orderService.sort(orderList)).thenReturn(orderList);
        double cost = costOfBag / bagWeight;
        double expectedPriceForClient1 = orderService.getPrice(order1.getWeight(), discount, cost);
        double expectedPriceForClient2 = orderService.getPrice(order2.getWeight(), discount, cost);
        when(orderService.getPrice(order1.getWeight(), discount, cost)).thenReturn(expectedPriceForClient1);
        when(orderService.getPrice(order2.getWeight(), discount, cost)).thenReturn(expectedPriceForClient2);

        // Act
        OrderReport result = orderService.createOrderReport(orderList, discount, costOfBag, costStep, bagWeight);

        // Assert
        assertEquals(2, result.getMap().size());
        assertTrue(result.getMap().containsKey("Client1"));
        assertTrue(result.getMap().containsKey("Client2"));
        assertEquals(expectedPriceForClient1, result.getMap().get("Client1"));
        assertEquals(expectedPriceForClient2, result.getMap().get("Client2"));
    }





    @Test
    @DisplayName("0. empty list error")
    void orderService_createOrderService_shouldReturn() {
        String inFileName = "discount_day.txt";
        FileOrderService fileOrderService = new FileOrderService();
        OrderParser orderParser = getOrderParser(inFileName);
        String[] stringOrders = fileOrderService.read(inFileName);
        List<Order> orders = orderParser.getOrderList(stringOrders);
        OrderReport orderReport = new OrderReport();


        int discount = 50;
        int bagWeight = 50;
        int cost = 500;
        int discountStep = 5;
//        when(orderServiceMock.createOrderReport(orders, discount, cost, discountStep, bagWeight)).thenReturn(null);
//        OrderReport orderReport1 = orderServiceMock.createOrderReport(orders, discount, cost, discountStep, bagWeight);
//        System.out.println(orderReport1);
//        String test = orderServiceMock.test();
//        System.out.println(test);
        when(orderServiceMock.test()).thenReturn("test");
    }


//    @Test
//    @DisplayName("0. normal report")
//    void OrderReport_createOrderReport_shouldReturnOrderReport() {
//        List<Order> orderList = new ArrayList<>();
//        OrderReport orderReport = new OrderReport();
//        String clientName = "Industrial";
//        Double orderWeight = 79200.0;
//        String orderData ="2021-02-09T16:00:22";
//        orderList.add(new Order(LocalDateTime.parse(orderData),clientName, orderWeight.intValue()));
//
//
//        double discount = 50;
//        double costOfBag = 50;
//        double costStep = 5;
//        int bagWeight = 50;
//
//        orderService.createOrderReport(orderList, discount, costOfBag, costStep, bagWeight);
//        assertEquals();
//    }


//1. **Проверка на пустой список заказов**:
//     - Входные данные: `orderList = []`, `discount = 0`, `costOfBag = 5`, `costStep = 10`, `bagWeight = 1`
//     - Ожидаемый результат: Пустой отчет или сообщение о том, что нет заказов.
@Test
@DisplayName("1. empty list")
void orderService_createOrderService_shouldReturnErrorIfReportEmpty() {
    List<Order> orderList = Collections.emptyList();
    double discount = 50;
    double costOfBag = 50;
    double costStep = 5;
    int bagWeight = 50;
    OrderReport orderReport = orderService.createOrderReport(orderList,discount,costOfBag,costStep,bagWeight);
}

//2. **Проверка с одним заказом без скидки**:
    // - Входные данные: `orderList = [Order(amount=100)]`, `discount = 0`, `costOfBag = 5`, `costStep = 10`, `bagWeight = 1`
    // - Ожидаемый результат: Общая стоимость, включая стоимость сумки.

//3. **Проверка с одним заказом со скидкой**:
    // - Входные данные: `orderList = [Order(amount=100)]`, `discount = 10`, `costOfBag = 5`, `costStep = 10`, `bagWeight = 1`
    // - Ожидаемый результат: Стоимость заказа с учетом скидки и стоимости сумки.

//4. **Проверка нескольких заказов без скидки**:
    // - Входные данные: `orderList = [Order(amount=100), Order(amount=200)]`, `discount = 0`, `costOfBag = 5`, `costStep = 10`, `bagWeight = 1`
    // - Ожидаемый результат: Суммарная стоимость всех заказов с добавлением стоимости сумки.

//5. **Проверка нескольких заказов со скидкой**:
    // - Входные данные: `orderList = [Order(amount=100), Order(amount=200)]`, `discount = 10`, `costOfBag = 5`, `costStep = 10`, `bagWeight = 1`
    // - Ожидаемый результат: Суммарная стоимость всех заказов с учетом скидки и стоимости сумки.

//6. **Проверка с нулевым значением скидки**:
    // - Входные данные: `orderList = [Order(amount=100)]`, `discount = 0`, `costOfBag = 5`, `costStep = 10`, `bagWeight = 1`
    // - Ожидаемый результат: Стоимость заказа должна быть равна сумме без изменений.

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

    @Test
    @DisplayName("0. empty sort")
    public void orderService_sort_EmptyList() {
        OrderService orderService = new OrderService();
        List<Order> orderList = Collections.emptyList();
        List<Order> sortedOrders = orderService.sort(orderList);
        assert sortedOrders.isEmpty();
    }

    @Test
    @DisplayName("0. single sort")
    public void orderService_sort_SingleElementList() {
        OrderService orderService = new OrderService();
        Order order1 = new Order("A", "2023-01-01");
        List<Order> orderList = Arrays.asList(order1);
        List<Order> sortedOrders = orderService.sort(orderList);
        assert sortedOrders.size() == 1;
        assert sortedOrders.get(0).equals(order1);
    }

    @Test
    @DisplayName("0. normal sort")
    public void orderService_sort_MultipleElementsList() {
        OrderService orderService = new OrderService();
        Order order1 = new Order("A", "2023-01-01");
        Order order2 = new Order("B", "2022-12-31");
        List<Order> orderList = Arrays.asList(order1, order2);
        List<Order> sortedOrders = orderService.sort(orderList);
        assert sortedOrders.size() == 2;
        assert sortedOrders.get(0).equals(order2) && sortedOrders.get(1).equals(order1);
    }

    @Test
    @DisplayName("0. 0% discount")
    void orderService_getPrice_NoDiscount() {
        OrderService orderService = new OrderService();
        double result = orderService.getPrice(10, 0, 50.0);
        assertEquals(500.0, result, 0.001);
    }

    @Test
    @DisplayName("0. 20% discount")
    void orderService_getPrice_WithDiscount() {
        OrderService orderService = new OrderService();
        double result = orderService.getPrice(10, 20, 50.0);
        assertEquals(400.0, result, 0.001);
    }

    @Test
    @DisplayName("0. 100% discount")
    void orderService_getPrice_FullDiscount() {
        OrderService orderService = new OrderService();
        double result = orderService.getPrice(10, 100, 50.0);
        assertEquals(0.0, result, 0.001);
    }

    @Test
    @DisplayName("0. negative discount")
    void orderService_getPrice_NegativeDiscount() {
        OrderService orderService = new OrderService();
        assertThrows(IllegalArgumentException.class, () -> {
            orderService.getPrice(10, -10, 50.0);
        });
    }

    @Test
    @DisplayName("0. no weight")
    void orderService_getPrice_ZeroWeight() {
        OrderService orderService = new OrderService();
        double result = orderService.getPrice(0, 10, 50.0);
        assertEquals(0.0, result, 0.001);
    }

    @Test
    @DisplayName("0. negative weight")
    void orderService_getPrice_NegativeWeight() {
        OrderService orderService = new OrderService();
        assertThrows(IllegalArgumentException.class, () -> {
            orderService.getPrice(-10, 10, 50.0);
        });
    }


}