package ru.ykul.service.orderparsers;

public class OrderParserFactory {
    public static OrderParser getOrderParser(String filename) {
        if (filename.endsWith(".txt")) {
            return new OrderParserByPipe();
        } else {
            return new OrderParserByOctothorpe();
        }
    }
}
