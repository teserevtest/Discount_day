package ru.ykul.service;

import ru.ykul.Main;
import ru.ykul.model.Order;
import ru.ykul.model.OrderReport;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;


public class FileOrderService {
    private static final String ORDER_PATH = Main.class.getClassLoader().getResource("orders").getPath().toString();

    public List<Order> read(String inFileName) {
        String lines[] = getFileAsString(inFileName).split("\\r?\\n");
        List<Order> orderArrayList = new ArrayList<>();
        for (String s : lines) {
            orderArrayList.add(parseStringToOrder(s));
        }
        return orderArrayList;
    }

    public void write(OrderReport orderMap, String outFileName) {
        try (PrintWriter writer = new PrintWriter(ORDER_PATH + "/" + outFileName)) {
            writer.write(orderMap.toString());
        } catch (IOException e) {
            throw new RuntimeException("Ошибка записи в файл");
        }
    }

    private Order parseStringToOrder(String string) {
        String[] parts = string.split("\\|");
        return new Order(LocalDateTime.parse(parts[0]), parts[1], Integer.parseInt(parts[2]));
    }

    private static String getFileAsString(String fileName) {
        try (Scanner scanner = new Scanner(new FileInputStream(ORDER_PATH + "/" + fileName)).useDelimiter("\\A")) {
            return scanner.hasNext() ? scanner.next() : "";
        } catch (IOException e) {
            throw new IllegalArgumentException("Input file not found! " + ORDER_PATH + "/" + fileName);
        }
    }
}