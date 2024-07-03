package ru.ykul.objects;

import java.util.LinkedHashMap;
import java.util.Map;

public class OrderReport {
    private static Map<String, Double> orderReportMap = new LinkedHashMap<>();

    public static Map getOrderReportMap() {
        return orderReportMap;
    }

    public static void setOrderReportMap(Map orderReportMap) {
        OrderReport.orderReportMap = orderReportMap;
    }

    public void putToMap(String key, Double value) {
        orderReportMap.put(key, value);
    }

}
