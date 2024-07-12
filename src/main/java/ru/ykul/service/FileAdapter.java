package ru.ykul.service;

import java.util.ArrayList;
import java.util.List;

public class FileAdapter {
    private String[] linesArray;

    public FileAdapter(String[] l) {
        this.linesArray = l;
    }

    public String[] getTrueLines() {
        List<String> lines = new ArrayList<>();
        for (String s : linesArray) {
            lines.add(s.replaceAll("#", "|"));
        }
        return lines.toArray(new String[0]);
    }
}
