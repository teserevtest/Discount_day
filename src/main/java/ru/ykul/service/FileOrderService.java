package ru.ykul.service;

import ru.ykul.objects.Order;
import ru.ykul.objects.OrderReport;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.*;

import static java.nio.charset.StandardCharsets.*;


public class FileOrderService {
    public FileOrderService(String inFileName, String outFileName) {
        this.inFileName = inFileName;
        this.outFileName = outFileName;
    }

    private String inFileName;
    private String outFileName;

    private final String ORDER_PATH = this.getClass().getClassLoader().getResource("orders").getPath();


    public List<Order> read() throws Exception {


        String lines[] = convertInputStreamToString(getFileFromResourceAsStream(this.inFileName)).split("\\r?\\n");


        List<Order> orderArrayList = new ArrayList<Order>();
        for (String s : lines) {
            orderArrayList.add(parseStringToOrder(s));
        }

        return orderArrayList;
    }


    public void write(OrderReport orderMap) throws Exception {


        try (PrintWriter writer = new PrintWriter(new File(ORDER_PATH) + "/" + this.outFileName)) {
            orderMap.getOrderReportMap().forEach((key, value) -> writer.println(key + " - " + value));

        } catch (IOException e) {
            throw new Exception("Ошибка записи в файл");
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
        } else {
            return inputStream;
        }

    }

    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line).append("\n");
        }
        bufferedReader.close();
        return stringBuilder.toString();
    }


}
