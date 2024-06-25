package ru.ykul.service;

import ru.ykul.objects.Order;

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
    private String inputFile;
    private String outputFile;

    public FileOrderService(String inputFile, String outputFile) {
        this.inputFile = inputFile;
        this.outputFile = outputFile;
    }

    public List<Order> read() throws Exception {

        List<Order> orderArrayList = new ArrayList<Order>();

        try (BufferedReader br = new BufferedReader(new FileReader(FileOrderService.class.getResource(inputFile).getFile()))) {
            String line;
            while ((line = br.readLine()) != null) {
                orderArrayList.add(parseStringToOrder(line));
            }
        } catch (IOException e) {
            throw new Exception();
        }
        return orderArrayList;
    }

    private Order parseStringToOrder(String string) {
        String[] parts = string.split("\\|");
        return new Order(LocalDateTime.parse(parts[0]), parts[1], Integer.parseInt(parts[2]));
    }

    public void write(Map orderMap) throws Exception {

        URL res = FileOrderService.class.getResource(outputFile);
        System.out.println(res);


        try (PrintWriter writer = new PrintWriter(res.toString())) {
            orderMap.forEach((key, value) -> writer.println(key.getClass().getDeclaredField(clientName) + " : " + value));


            Set set = orderMap.entrySet();
            Iterator i = set.iterator();
            while(i.hasNext()) {
                Map.Entry me = (Map.Entry)i.next();

                writer.println(me.getKey());
                System.out.print(me.getKey() + ": ");
                System.out.println(me.getValue());
            }


            writer.println("Hello World");

        } catch (IOException e) {
            throw new Exception("Ошибка записи в файл");
        }
    }

}
