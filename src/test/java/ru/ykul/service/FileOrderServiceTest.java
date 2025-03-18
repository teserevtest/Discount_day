package ru.ykul.service;

import org.junit.jupiter.api.*;
import ru.ykul.Main;
import ru.ykul.model.OrderReport;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertThrows;


class FileOrderServiceTest {

    private final static String ORDER_PATH = Main.class.getClassLoader().getResource("orders").getPath().toString();
    private final FileOrderService fileOrderService = new FileOrderService();

    @AfterEach
    void clean_test_output() {
        String fileName = "test_output.txt";
        try {
            Files.deleteIfExists(
                    Paths.get(ORDER_PATH.substring(1) + "/" + fileName));
        } catch (NoSuchFileException e) {
            System.out.println(
                    "No such file/directory exists");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("0. read txt")
    void fileOrderService_read_shouldReadTxtFile_ifFileIsCorrect() throws IOException {
        String fileName = "input.txt";
        String[] fileText = new String[1];
        fileText[0] = "2021-02-09T16:00:22|Industrial|8800";
        Assertions.assertEquals(fileText[0], fileOrderService.read(fileName)[0]);
    }

    @Test
    @DisplayName("1. read file")
    void fileOrderService_read_shouldReadFileWithoutExtension_ifFileIsCorrect() throws IOException {
        String fileName = "input";
        String[] fileText = new String[1];
        fileText[0] = "2021-02-09T16:00:22#Industrial#8800";
        Assertions.assertEquals(fileText[0], fileOrderService.read(fileName)[0]);
    }

    @Test
    @DisplayName("2. read empty")
    void fileOrderService_read_shouldReadTxtFile_ifFileEmpty() throws IOException {
        String fileName = "input_empty";
        assertThrows(IllegalArgumentException.class, () -> {
            fileOrderService.read(fileName);
        });
    }

    @Test
    @DisplayName("3. read null file")
    void fileOrderService_read_shouldThrowException_ifFileNameIsNull() {
        String fileName = null;
        assertThrows(IllegalArgumentException.class, () -> {
            fileOrderService.read(fileName);
        });
    }

    @Test
    @DisplayName("4. read missing file")
    void fileOrderService_read_shouldThrowException_ifFileNameIsMissing() {
        String fileName = "input_missing";
        assertThrows(NoSuchFileException.class, () -> {
            fileOrderService.read(fileName);
        }, "Input file format not supported!");
    }

    @Test
    @DisplayName("5. write file")
    void fileOrderService_write_shouldWriteOrderToFile() {
        String fileName = "test_output.txt";
        String key = "Industrial";
        Double value = 79200.0;
        OrderReport orderReport = new OrderReport();
        orderReport.putToMap(key, value);
        fileOrderService.write(orderReport, fileName);
        try (Scanner scanner = new Scanner(new FileInputStream(ORDER_PATH + "/" + fileName)).useDelimiter("\\A")) {
            fileName = scanner.hasNext() ? scanner.next() : "";
        } catch (IOException e) {
            throw new IllegalArgumentException("Input file not found! " + ORDER_PATH + "/" + fileName);
        }
        Assertions.assertEquals(fileName, key + " - " + value + "\n");
    }

    @Test
    @DisplayName("6. write  null report")
    void fileOrderService_write_shouldThrowException_ifOrderReportIsNull() {
        String fileName = "orders.txt";
        OrderReport orderReport = null;
        assertThrows(IllegalArgumentException.class, () -> {
            fileOrderService.write(orderReport, fileName);
        });
    }

    @Test
    @DisplayName("7. write null file")
    void fileOrderService_write_shouldThrowException_ifFileNameIsNull() {
        String fileName = null;
        String key = "Industrial";
        Double value = 79200.0;
        OrderReport orderReport = new OrderReport();
        orderReport.putToMap(key, value);
        assertThrows(IllegalArgumentException.class, () -> {
            fileOrderService.write(orderReport, fileName);
        }, "Output file name is null!");
    }

    @Test
    @DisplayName("8. write empty file name")
    void fileOrderService_write_shouldThrowException_ifFileNameIsEmpty() {
        String fileName = "";
        OrderReport orderReport = new OrderReport();
        assertThrows(IllegalArgumentException.class, () -> {
            fileOrderService.write(orderReport, fileName);
        }, "Output file name is empty!");
    }
}