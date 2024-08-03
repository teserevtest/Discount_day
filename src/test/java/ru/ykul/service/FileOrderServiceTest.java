package ru.ykul.service;

import org.junit.jupiter.api.*;
import ru.ykul.Main;
import ru.ykul.model.OrderReport;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


class FileOrderServiceTest {

    private final static String ORDER_PATH = Main.class.getClassLoader().getResource("orders").getPath().toString();
    private final FileOrderService fileOrderService = new FileOrderService();

    @Test
    @DisplayName("0. read txt")
    void fileOrderService_read_shouldReadTxtFile_ifFileIsCorrect() {
        String fileName = "input.txt";
        String[] fileText = new String[1];
        fileText[0] = "2021-02-09T16:00:22|Industrial|8800";
        Assertions.assertEquals(fileText[0], fileOrderService.read(fileName)[0]);
    }

    @Test
    @DisplayName("1. read file")
    void fileOrderService_read_shouldReadFileWithoutExtension_ifFileIsCorrect() {
        String fileName = "input";
        String[] fileText = new String[1];
        fileText[0] = "2021-02-09T16:00:22#Industrial#8800";
        Assertions.assertEquals(fileText[0], fileOrderService.read(fileName)[0]);
    }


    @Test
    @DisplayName("2. read empty")
    void fileOrderService_read_shouldReadTxtFile_ifFileEmpty() {
        String fileName = "input_empty";
        Assertions.assertEquals("", fileOrderService.read(fileName)[0]);
    }

    @Test
    @DisplayName("3. read null file")
    void fileOrderService_read_shouldThrowException_ifFileNameIsNull() {
        String fileName = null;
        assertThrows(IllegalArgumentException.class, () -> {
            fileOrderService.read(fileName);
        });
    }

    @Test
    @DisplayName("4. read missing file")
    void fileOrderService_read_shouldThrowException_ifFileNameIsMissing() {
        String fileName = "input_missing";
        assertThrows(IllegalArgumentException.class, () -> {
            fileOrderService.read(fileName);
        }, "Input file format not supported!");
    }

    @Test
    @DisplayName("5. write file")
    void fileOrderService_write_shouldWriteOrderToFile() {
        String fileName = "fileOrderService_write_shouldWriteOrderToFile.txt";
        String key = "Industrial";
        Double value = 79200.0;
        OrderReport orderReport = new OrderReport();
        orderReport.putToMap(key, value);
        fileOrderService.write(orderReport, fileName);
        try (Scanner scanner = new Scanner(new FileInputStream(ORDER_PATH + "/" + fileName)).useDelimiter("\\A")) {
            fileName = scanner.hasNext() ? scanner.next() : "";
        } catch (IOException e) {
            throw new IllegalArgumentException("Input file not found! " + ORDER_PATH + "/" + fileName);
        }
        Assertions.assertEquals(fileName, key + " - " + value + "\n");
    }

    @Test
    @DisplayName("6. write  null report")
    void fileOrderService_write_shouldThrowException_ifOrderReportIsNull() {
        String fileName = "orders.txt";
        OrderReport orderReport = null;
        assertThrows(IllegalArgumentException.class, () -> {
            fileOrderService.write(orderReport, fileName);
        });
    }

    @Test
    @DisplayName("7. write null file")
    void fileOrderService_write_shouldThrowException_ifFileNameIsNull() {
        String fileName = null;
        String key = "Industrial";
        Double value = 79200.0;
        OrderReport orderReport = new OrderReport();
        orderReport.putToMap(key, value);
        assertThrows(RuntimeException.class, () -> {
            fileOrderService.write(orderReport, fileName);
        }, "Output file name is null!");
    }

    @Test
    @DisplayName("8. write empty report")
    void fileOrderService_write_shouldThrowException_ifOrderReportIsEmpty() {
        String fileName = "fileOrderService_write_shouldThrowException_ifOrderReportIsEmpty.txt";

        OrderReport orderReport1 = new OrderReport();
        System.out.println(orderReport1);

        assertThrows(IllegalArgumentException.class, () -> {
            fileOrderService.write(orderReport1, fileName);
        }, "OrderReport is empty!");
    }


    @Test
    @DisplayName("9. write empty file name")
    void fileOrderService_write_shouldThrowException_ifFileNameIsEmpty() {
        String fileName = "";
        OrderReport orderReport = new OrderReport();
        assertThrows(RuntimeException.class, () -> {
            fileOrderService.write(orderReport, fileName);
        }, "Output file name is empty!");
    }

    @AfterEach
    public void wipeData()
    {
        OrderReport orderReport = new OrderReport();
        orderReport = null;

    }
}