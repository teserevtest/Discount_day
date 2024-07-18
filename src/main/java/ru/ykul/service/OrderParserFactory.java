package ru.ykul.service;

import ru.ykul.service.orderparsers.OrderParser;
import ru.ykul.service.orderparsers.OrderParserByOctothorpe;
import ru.ykul.service.orderparsers.OrderParserByPipe;

public class OrderParserFactory {
    public static OrderParser getOrderParser(String filename) {
        if (filename.endsWith(".txt")) {
            return new OrderParserByPipe();
        } else {
            return new OrderParserByOctothorpe();
        }
    }
}
