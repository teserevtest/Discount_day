package ru.ykul.model;

import java.util.LinkedHashMap;
import java.util.Map;

public class OrderReport {
    private Map<String, Double> orderReportMap = new LinkedHashMap<>();

    public void putToMap(String key, Double value) {
        orderReportMap.put(key, orderReportMap.getOrDefault(key, 0.0) + value);
    }

    @Override
    public String toString() {
        StringBuilder orderMapStringBuilder = new StringBuilder();
        orderReportMap.forEach((key, value) -> orderMapStringBuilder.append(key + " - " + value + "\n"));
        return orderMapStringBuilder.toString();
    }
}
