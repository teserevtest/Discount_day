package ru.ykul.objects;

import java.util.LinkedHashMap;
import java.util.Map;

public class OrderReport {
    private static Map<String, Double> orderReportMap = new LinkedHashMap<>();

    public static Map getOrderReportMap() {
        return orderReportMap;
    }

    public void putToMap(String key, Double value) {
        orderReportMap.put(key, orderReportMap.getOrDefault(key, 0.0) + value);
    }

}
