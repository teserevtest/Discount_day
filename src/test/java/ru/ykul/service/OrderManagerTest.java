package ru.ykul.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.ykul.manager.OrderManager;

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
        String inFileName;
        String outFileName;
        double discount;
        double cost;
        double discountStep;
        int bagWeight;

        doReturn(new String[]{ "", ""}).when(fileOrderService).read(inFileName);



        var qqqq= orderManager.writeOrderReport();


    }
}
