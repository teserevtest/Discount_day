package ru.ykul.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.ykul.manager.OrderManager;
import ru.ykul.model.Order;
import ru.ykul.model.OrderReport;
import ru.ykul.service.orderparsers.OrderParser;
import ru.ykul.service.orderparsers.OrderParserFactory;

import java.util.List;

import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class OrderManagerTest {
    @Mock
    private OrderService orderService;
    @Mock
    private FileOrderService fileOrderService;
    @InjectMocks
    private OrderManager orderManager;

    @Test
    void writeOrderReport_should_writeReportFile() {
        String inFileName = "discount_day.txt";
        String outFileName = "orders.txt";
        double discount = 50;
        double cost = 500;
        double discountStep = 5;
        int bagWeight = 50;


        doReturn(new String[]{"", ""}).when(fileOrderService).read(inFileName);


        var qqqq = orderManager.writeOrderReport();


        OrderParser orderParser = OrderParserFactory.getOrderParser(inFileName);
        String[] stringOrders = fileOrderService.read(inFileName);
        List<Order> orders = orderParser.getOrderList(stringOrders);
        OrderReport orderReport = orderService.createOrderReport(orders, discount, cost, discountStep, bagWeight);
        fileOrderService.write(orderReport, outFileName);


    }
}
