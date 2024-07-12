package ru.ykul.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileAdapter {
    private String[] linesArray;

    public FileAdapter(String[] lines) {
        this.linesArray = lines;
    }

    public String[] getTrueLines() {
        List<String> lines = new ArrayList<>();
        for (String s : linesArray) {
            lines.add(s.replaceAll("#", "|"));
            System.out.println(s);
        }
        if (lines.size() == 0) {
            return lines.toArray(new String[0]);
        } else {
            return (String[]) lines.toArray();
        }
    }
}
