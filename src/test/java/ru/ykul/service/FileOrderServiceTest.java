package ru.ykul.service;

import org.junit.jupiter.api.*;
import ru.ykul.Main;
import ru.ykul.model.OrderReport;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertTrue;


class FileOrderServiceTest {

    private final static String ORDER_PATH = Main.class.getClassLoader().getResource("orders").getPath().toString();
    FileOrderService fileOrderService = new FileOrderService();

    @Test
    @DisplayName("0. read txt")
    void fileOrderService_shouldReadTxtFile_ifFileIsCorrect() {
        String fileName = "input.txt";
        String[] fileText = new String[1];
        fileText[0] = "2021-02-09T16:00:22|Industrial|8800";
        Assertions.assertEquals(fileText[0], fileOrderService.read(fileName)[0]);
    }

    @Test
    @DisplayName("1. read file")
    void fileOrderService_shouldReadFileWithoutExtension_ifFileIsCorrect() {
        String fileName = "input";
        String[] fileText = new String[1];
        fileText[0] = "2021-02-09T16:00:22#Industrial#8800";
        Assertions.assertEquals(fileText[0], fileOrderService.read(fileName)[0]);
    }

    @Test
    @DisplayName("2. write file")
    void fileOrderService_shouldWriteOrderToFile() {
        String fileName = "orders.txt";
        String fileText;
        String key = "Industrial";
        Double value = 79200.0;
        OrderReport orderReport = new OrderReport();
        orderReport.putToMap(key, value);
        fileOrderService.write(orderReport, fileName);
        try (Scanner scanner = new Scanner(new FileInputStream(ORDER_PATH + "/" + fileName)).useDelimiter("\\A")) {
            fileText = scanner.hasNext() ? scanner.next() : "";
        } catch (IOException e) {
            throw new IllegalArgumentException("Input file not found! " + ORDER_PATH + "/" + fileName);
        }
        Assertions.assertEquals(fileText, key + " - " + value + "\n");
    }

    @Test
    @DisplayName("3. read empty")
    void fileOrderService_shouldReadTxtFile_ifFileEmpty() {
        String fileName = "input_empty";
        Assertions.assertEquals("", fileOrderService.read(fileName)[0]);
    }

    @Test
    @DisplayName("4. read null file")
    void fileOrderService_shouldThrowException_ifFileNameIsNull() {
        String fileName = null;
        boolean exceptionThrown = false;
        try {
            fileOrderService.read(fileName);
        } catch (IllegalArgumentException e) {
            exceptionThrown = true;
        }
        assertTrue(exceptionThrown);
    }

    @Test
    @DisplayName("5. read missing file")
    void fileOrderService_shouldThrowException_ifFileNameIsMissing() {
        String fileName = "input_missing";
        boolean exceptionThrown = false;
        try {
            fileOrderService.read(fileName);
        } catch (IllegalArgumentException e) {
            exceptionThrown = true;
        }
        assertTrue(exceptionThrown);
    }
}