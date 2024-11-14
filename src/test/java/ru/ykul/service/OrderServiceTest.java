package ru.ykul.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.ykul.model.Order;
import ru.ykul.model.OrderReport;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {
    private  final  OrderService orderService = new OrderService();

    @Test
    @DisplayName("0. normal report")
    void OrderReport_createOrderReport_shouldReturnOrderReport() {
        List<Order> orderList = new ArrayList<>();
        OrderReport orderReport = new OrderReport();
        String clientName = "Industrial";
        String orderWeight = "79200.0";
        String orderData ="2021-02-09T16:00:22";
        orderList.add(new Order(LocalDateTime.parse(orderData),clientName, Integer.parseInt(orderWeight)));

        double discount = 50;
        double costOfBag = 50;
        double costStep = 5;
        int bagWeight = 50;
        assertEquals();
        orderService.createOrderReport(orderList, discount, costOfBag, costStep, bagWeight);

    }


//1. **Проверка на пустой список заказов**:
            // - Входные данные: `orderList = []`, `discount = 0`, `costOfBag = 5`, `costStep = 10`, `bagWeight = 1`
            // - Ожидаемый результат: Пустой отчет или сообщение о том, что нет заказов.
@Test
@DisplayName("1. empty list")
void OrderReport_createOrderReport_shouldReturnOrderReport() {
    List<Order> orderList = new ArrayList<>();
    double discount = 50;
    double costOfBag = 50;
    double costStep = 5;
    int bagWeight = 50;
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

            1//0. **Проверка с изменением стоимости шага**:
    // - Входные данные: `orderList = [Order(amount=100)]`, `discount = 0`, `costOfBag = 5`, `costStep = 20`, `bagWeight = 1`
    // - Ожидаемый результат: Общая стоимость должна измениться в зависимости от изменения стоимости шага.

}