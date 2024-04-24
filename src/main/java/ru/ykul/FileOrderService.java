package ru.ykul;

import java.io.*;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.stream.Stream;

public class FileOrderService {
    String workingDirectory = "E:\\ReposMike\\ykul\\Discount_day\\src\\main\\resources\\";
    String inputFile = "discount_day.txt";
    String outputFile = "discount_day_orders.txt";


    public ArrayList<Order> getOrdersListFromFile() {


        ArrayList<Order> orderArrayList = new ArrayList<Order>();

        try {

            File file = new File(workingDirectory + inputFile);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                orderArrayList.add(parseStringToOrder(line));
            }
            br.close();
            fr.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return orderArrayList;
    }

    public Order parseStringToOrder(String string) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

        String[] parts = string.split("\\|");

        Order order = new Order(LocalDateTime.parse(parts[0], formatter), parts[1], Integer.parseInt(parts[2]));
        return order;
    }

    public void writeOrdersToFile(ArrayList<Order> orderList) {

        try {
            FileWriter writer = new FileWriter(workingDirectory + outputFile);

            for (Order x : orderList) {
                writer.write(x.getCounteragent() + " - " + String.valueOf(x.getOrderCount()) + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Ошибка записи в файл");
            e.printStackTrace();
        }
    }

}
