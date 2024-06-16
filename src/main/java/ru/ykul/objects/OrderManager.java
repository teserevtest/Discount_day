package ru.ykul.objects;

import ru.ykul.service.FileOrderService;

public class OrderManager {
    private final Options options;
    public OrderManager(Options options) throws Exception {

        this.options = options;


        FileOrderService fileOrderService = new FileOrderService(options.getInFileName());
        fileOrderService.read();




    }
}
