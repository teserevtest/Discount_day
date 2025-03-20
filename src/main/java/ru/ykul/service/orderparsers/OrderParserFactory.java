package ru.ykul.service.orderparsers;

public class OrderParserFactory {
    public OrderParser getOrderParser(String filename) {
        String end = filename.substring(filename.lastIndexOf("."));
        if (end == null) {
            return new OrderParserByOctothorpe();
        } else if (end.equals(".txt")) {
            return new OrderParserByPipe();
        } else {
            throw new IllegalArgumentException("Input file format not supported!");
        }
    }
}
