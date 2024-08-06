package ru.ykul.service;

import ru.ykul.Main;
import ru.ykul.model.Order;
import ru.ykul.model.OrderReport;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

public class FileOrderService {
    private static final String ORDER_PATH = Main.class.getClassLoader().getResource("orders").getPath().toString();

    public String[] read(String inFileName) {
        if (inFileName == null || inFileName == "") {
            throw new IllegalArgumentException("Incorrect value");
        }
        String lines[] = getFileAsString(inFileName).split("\\r?\\n");
        return lines;
    }

    public void write(OrderReport orderReport, String outFileName) {
        if (orderReport == null || outFileName == null || outFileName == "") {
            throw new IllegalArgumentException("Incorrect value");
        }
        try (PrintWriter writer = new PrintWriter(ORDER_PATH + "/" + outFileName)) {
            writer.write(orderReport.toString());
        } catch (IOException e) {
            throw new IllegalArgumentException("Ошибка записи в файл");
        }
    }

    private static String getFileAsString(String fileName) {
        try (Scanner scanner = new Scanner(new FileInputStream(ORDER_PATH + "/" + fileName)).useDelimiter("\\A")) {
            return scanner.hasNext() ? scanner.next() : "";
        } catch (IOException e) {
            throw new IllegalArgumentException("Input file not found! " + ORDER_PATH + "/" + fileName);
        }
    }
}