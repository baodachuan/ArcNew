package com.bdc.blibrary.log;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HiLogManager {
    private HiLogConfig config;
    private List<HiLogPrinter> printers=new ArrayList<>();


    private HiLogManager() {

    }

    public static HiLogManager getInstance() {
        return Holder.INSTANCE;
    }

    public void init(@NonNull HiLogConfig config,HiLogPrinter... printers) {
        this.config = config;
        this.printers.addAll(Arrays.asList(printers));
    }

    public List<HiLogPrinter> getPrinters() {
        return printers;
    }

    public void addPrinters(HiLogPrinter printer){
        printers.add(printer);
    }

    public void removePrinter(HiLogPrinter printer){
        printers.remove(printer);
    }

    private static class Holder {
        private static HiLogManager INSTANCE = new HiLogManager();
    }

    public HiLogConfig getConfig() {
        return config;
    }
}
