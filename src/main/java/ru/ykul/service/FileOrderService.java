package ru.ykul.service;

import ru.ykul.Main;
import ru.ykul.model.OrderReport;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Stream;


public class FileOrderService {
    private static final String ORDER_PATH = Objects.requireNonNull(Main.class.getClassLoader().getResource("orders")).getPath().toString().replaceFirst("^.", "");

    public String[] read(String inFileName) throws IOException {
        if (inFileName == null || inFileName.isEmpty()) {
            throw new IllegalArgumentException("Incorrect value");
        }
        File file = new File(ORDER_PATH +"/" +inFileName);
        if (file.exists() && file.length() == 0) {
            throw new IllegalArgumentException("Empty file!");
        }
        {
            try (Stream<String> lines = Files.lines(Path.of(ORDER_PATH +"/" +inFileName))) {
                return lines.toArray(String[]::new);
            }
        }
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
}