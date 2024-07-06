package ru.ykul.service;

import ru.ykul.model.Order;
import ru.ykul.model.OrderReport;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;


public class FileOrderService {
    private final String ORDER_PATH = this.getClass().getClassLoader().getResource("orders").getPath();

    public List<Order> read(String inFileName) {

        String lines[] = new String[0];

        lines = convertInputStreamToString(getFileFromResourceAsStream(inFileName)).split("\\r?\\n");

        List<Order> orderArrayList = new ArrayList<>();
        for (String s : lines) {
            orderArrayList.add(parseStringToOrder(s));
        }
        return orderArrayList;
    }


    public void write(OrderReport orderMap, String outFileName) {

        try (PrintWriter writer = new PrintWriter(new File(ORDER_PATH) + "/" + outFileName)) {
            writer.write(orderMap.toString());

        } catch (IOException e) {
            throw new RuntimeException("Ошибка записи в файл");
        }
    }

    private Order parseStringToOrder(String string) {
        String[] parts = string.split("\\|");
        return new Order(LocalDateTime.parse(parts[0]), parts[1], Integer.parseInt(parts[2]));
    }

    private InputStream getFileFromResourceAsStream(String fileName) {

        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        if (inputStream == null) {
            throw new IllegalArgumentException("Input file not found! " + fileName);
        }
        return inputStream;

    }

    private static String convertInputStreamToString(InputStream inputStream) {
        Scanner scanner = new Scanner(inputStream).useDelimiter("\\A");

        return scanner.hasNext() ? scanner.next() : "";
    }

}