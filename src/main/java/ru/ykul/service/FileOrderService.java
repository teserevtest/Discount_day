package ru.ykul.service;

import ru.ykul.objects.Order;

import java.io.*;
import java.net.URL;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class FileOrderService {
    private String inputFile;
    private String outputFile;

    public FileOrderService(String inputFile,String outputFile) {
        this.inputFile=inputFile;
        this.outputFile=outputFile;
    }

   public List<Order> read() throws Exception {

       URL res = FileOrderService.class.getResource(inputFile);



        ArrayList<Order> orderArrayList = new ArrayList<Order>();
        try (BufferedReader br = new BufferedReader(new FileReader(res.toString()))) {
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

    public void write(Map orderMap) throws Exception {

        URL res = FileOrderService.class.getResource(this.outputFile);


        try (FileOutputStream writer = new FileOutputStream(res.toString());
             ObjectOutputStream out = new ObjectOutputStream(writer);) {
            out.writeObject(orderMap);
            out.close();
            writer.close();
            System.out.printf("Список заказов записан  в " +res.toString());

        } catch (IOException e) {
            System.out.println("Ошибка записи в файл");
            throw new Exception();
        }
    }

}
