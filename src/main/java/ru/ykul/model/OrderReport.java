package ru.ykul.model;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class OrderReport {
    private Map<String, Double> orderReportMap = new LinkedHashMap<>();

    public void putToMap(String key, Double value) {
        orderReportMap.put(key, orderReportMap.getOrDefault(key, 0.0) + value);
    }

    @Override
    public String toString() {
        return orderReportMap.entrySet().stream()
                .map(entry -> entry.getKey() + " - " + entry.getValue())
                .collect(Collectors.joining("\n"))+"\n";
    }
}
