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
import ru.ykul.service.orderparsers.OrderParserByOctothorpe;
import ru.ykul.service.orderparsers.OrderParserByPipe;
import ru.ykul.service.orderparsers.OrderParserFactory;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderManagerTest {
    @Mock
    private OrderService orderServiceMock;
    @Mock
    private OrderParser orderParserMock;
    @Mock
    private FileOrderService fileOrderServiceMock;
    @Mock
    private OrderParserFactory orderParserFactoryMock;

    @InjectMocks
    private OrderManager orderManagerMocked;

    @Test
    public void writeOrderReport_should_writeReportFile() throws IOException {


        String testInFileNamePipe = "test.txt";
        String testInFileNameOct = "test";
        String testOutFileName = "test";
        String[] fileorderserviceReadRefund = new String[]{"2021-02-09T16:00:22#Industrial#8800"};
        List<Order> orderParserRefund = Arrays.asList(new Order(LocalDateTime.parse("2021-02-09T16:00:22"), "Industrial", 8800));
        double discount = 50;
        double cost = 500;
        double discountStep = 5;
        int bagWeight = 50;

        OrderReport orderReport = new OrderReport();
        orderReport.putToMap("Industrial", 79200.0);


        when(orderServiceMock.createOrderReport(orderParserRefund, discount, cost, discountStep, bagWeight)).thenReturn(orderReport);
        when(orderParserMock.getOrderList(fileorderserviceReadRefund)).thenReturn(orderParserRefund);
        when(fileOrderServiceMock.read(testInFileNamePipe)).thenReturn(fileorderserviceReadRefund);
        when(orderParserFactoryMock.getOrderParser(testInFileNamePipe)).thenReturn(orderParserMock);


        orderManagerMocked.writeOrderReport(testInFileNamePipe, testOutFileName, discount, cost, discountStep, bagWeight);
        verify(orderParserFactoryMock, atLeastOnce()).getOrderParser(testInFileNamePipe);
        verify(orderParserMock, atLeastOnce()).getOrderList(fileorderserviceReadRefund);
        verify(fileOrderServiceMock,atLeastOnce()).read(testInFileNamePipe);
        verify(orderServiceMock,atLeastOnce()).createOrderReport(orderParserRefund, discount, cost, discountStep, bagWeight) ;
    }
}
