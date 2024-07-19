package ru.ykul.service;

import org.junit.jupiter.api.*;
import ru.ykul.Main;
import ru.ykul.model.OrderReport;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class FileOrderServiceTest {

    String ORDER_PATH = Main.class.getClassLoader().getResource("orders").getPath().toString();
    FileOrderService fileOrderService = new FileOrderService();

    @Test
    @Order(1)
    @DisplayName("0. read txt")
    void FileOrderService_shouldReadTxtFile() {
        String fileName = "discount_day.txt";
        String[] fileText = new String[1];
        fileText[0] = "2021-02-09T16:00:22|Industrial|8800";


        try (PrintWriter writer = new PrintWriter(ORDER_PATH + "/" + fileName)) {
            writer.write(fileText[0]);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка записи в файл");
        }

        Assertions.assertEquals(fileText[0], fileOrderService.read(fileName)[0]);
    }

    @Test
    @Order(2)
    @DisplayName("1. read file")
    void FileOrderService_shouldReadFileWithoutExtension() {
        String fileName = "discount_day";
        String[] fileText = new String[1];
        fileText[0] = "2021-02-09T16:00:22#Industrial#8800";


        try (PrintWriter writer = new PrintWriter(ORDER_PATH + "/" + fileName)) {
            writer.write(fileText[0]);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка записи в файл");
        }

        Assertions.assertEquals(fileText[0], fileOrderService.read(fileName)[0]);
    }

    @Test
    @Order(3)
    @DisplayName("2. write file")
    void FileOrderService_shouldWriteOrderToFile() {
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
}