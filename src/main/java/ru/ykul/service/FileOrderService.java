package ru.ykul.service;

import ru.ykul.objects.Order;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class FileOrderService {
    private String inputFile;

    public FileOrderService(String inputFile) {
        this.inputFile=inputFile;
    }

   public List<Order> read() throws Exception {

        ArrayList<Order> orderArrayList = new ArrayList<Order>();
        try (BufferedReader br = new BufferedReader(new FileReader(this.inputFile))) {
            String line;
            while((line =br.readLine())!=null)

            {
                orderArrayList.add(parseStringToOrder(line));
            }
        } catch(IOException e) {
            throw new Exception();
        }
        return orderArrayList;
    }

    private Order parseStringToOrder(String string) {
        String[] parts = string.split("\\|");
        return new Order(LocalDateTime.parse(parts[0]), parts[1], Integer.parseInt(parts[2]));
    }

    void write(List<Order> orderList, String outputFile, String WORK_DIR) throws Exception {
        try (FileWriter writer = new FileWriter(WORK_DIR + outputFile)) {
            for (Order x : orderList) {
                writer.write(x.getClientName() + " - " + String.valueOf(x.getWeight()) + System.lineSeparator());//TODO тут точно криво - нет рассчёта суммы пока.
            }
        } catch (IOException e) {
            System.out.println("Ошибка записи в файл");
            throw new Exception();
        }
    }

}
