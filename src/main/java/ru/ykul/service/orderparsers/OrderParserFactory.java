package ru.ykul.service.orderparsers;

public class OrderParserFactory {
    public static OrderParser getOrderParser(String filename) {
        if (filename.contains(".")) {
            if (filename.endsWith(".txt")) {
                return new OrderParserByPipe();
            } else {
                throw new IllegalArgumentException("Input file format not supported!");
            }
        } else {
            return new OrderParserByOctothorpe();
        }
    }
}
