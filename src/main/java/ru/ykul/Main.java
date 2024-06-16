package ru.ykul;

import org.kohsuke.args4j.*;
import ru.ykul.objects.Options;
import ru.ykul.objects.OrderManager;

public class Main {

    public void parseArgs(String[] args) throws Exception {
        Options options = new Options();
        CmdLineParser parser = new CmdLineParser(options);
        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            throw new IllegalArgumentException("");
        }


        new OrderManager(options);
    }

    public static void main(String[] args) throws Exception {
        new Main().parseArgs(args);
    }


}