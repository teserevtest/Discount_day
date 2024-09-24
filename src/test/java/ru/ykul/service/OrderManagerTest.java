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
    public void writeOrderReport_should_writeReportFile()  {


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

//        when(orderParserFactoryMock.getOrderParser(testInFileNamePipe)).thenReturn(new OrderParserByPipe());
        when(orderParserFactoryMock.getOrderParser(testInFileNameOct)).thenReturn(new OrderParserByOctothorpe());
        when(fileOrderServiceMock.read(testInFileNamePipe)).thenReturn(fileorderserviceReadRefund);
        when(orderParserMock.getOrderList(fileorderserviceReadRefund)).thenReturn(orderParserRefund);
        when(orderServiceMock.createOrderReport(orderParserRefund, discount, cost, discountStep, bagWeight)).thenReturn(orderReport);


//                doReturn(new String[]{"", ""}).when(fileOrderServiceMock).read(testInFileName)]


//        var qqqq = orderManager.writeOrderReport();


//        OrderParser orderParser = orderParserFactory.getOrderParser(testInFileName);
//        String[] stringOrders = fileOrderService.read(testInFileName);
//        List<Order> orders = orderParser.getOrderList(stringOrders);
//        OrderReport orderReport = orderService.createOrderReport(orders, discount, cost, discountStep, bagWeight);
//        fileOrderService.write(orderReport, testOutFileName);
        orderManagerMocked.writeOrderReport(testInFileNamePipe, testOutFileName, discount, cost, discountStep, bagWeight);
        verify(orderManagerMocked, atLeastOnce()).writeOrderReport(testInFileNamePipe, testOutFileName, discount, cost, discountStep, bagWeight);
        verify(orderParserMock, atLeastOnce()).getOrderList(fileorderserviceReadRefund);

//        // определяем поведение (результаты)
//        when(mcalc.subtract(15.0, 25.0)).thenReturn(10.0);
//        when(mcalc.subtract(35.0, 25.0)).thenReturn(-10.0);
//
//        // вызов метода subtract и проверка результата
//        assertEquals (calc.subtract(15.0, 25.0), 10, 0);
//        assertEquals (calc.subtract(15.0, 25.0), 10, 0);
//
//        assertEquals (calc.subtract(35.0, 25.0),-10, 0);
//
//        // проверка вызова методов
//        verify(mcalc, atLeastOnce()).subtract(35.0, 25.0);
//        verify(mcalc, atLeast   (2)).subtract(15.0, 25.0);
//
//        // проверка - был ли метод вызван 2 раза?
//        verify(mcalc, times(2)).subtract(15.0, 25.0);
//        // проверка - метод не был вызван ни разу
//        verify(mcalc, never()).divide(10.0,20.0);

        /* Если снять комментарий со следующей проверки, то
         * ожидается exception, поскольку метод "subtract"
         * с параметрами (35.0, 25.0) был вызван 1 раз
         */
        // verify(mcalc, atLeast (2)).subtract(35.0, 25.0);

        /* Если снять комментарий со следующей проверки, то
         * ожидается exception, поскольку метод "subtract"
         * с параметрами (15.0, 25.0) был вызван 2 раза, а
         * ожидался всего один вызов
         */
        // verify(mcalc, atMost (1)).subtract(15.0, 25.0);


    }
}
