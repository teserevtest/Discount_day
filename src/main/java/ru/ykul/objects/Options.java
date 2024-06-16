package ru.ykul.objects;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.Option;

import java.util.ArrayList;
import java.util.List;

public class Options {

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getInFileName() {
        return inFileName;
    }

    public void setInFileName(String inFileName) {
        this.inFileName = inFileName;
    }

    public String getOutFileName() {
        return outFileName;
    }

    public void setOutFileName(String outFileName) {
        this.outFileName = outFileName;
    }

    public List<String> getArguments() {
        return arguments;
    }

    public void setArguments(List<String> arguments) {
        this.arguments = arguments;
    }

    @Option(name = "-disc", usage = "max discount percetns")
    private int discount = 50;

    @Option(name = "-cost", usage = "cost of bag")
    private int cost = 500;

    @Option(name = "-in", usage = "input file (default: discount_day.txt)", metaVar = "INPUT")
    private String inFileName = "discount_day.txt";

    @Option(name = "-out", usage = "output to file (default: orders.txt)", metaVar = "OUTPUT")
    private String outFileName = "orders.txt";

    @Argument
    private List<String> arguments = new ArrayList<String>();
}
