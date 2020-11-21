package com.bdc.blibrary.log;

public class HiThreadFormatter implements HiLogFormatter<Thread> {

    @Override
    public String format(Thread data) {
        return "Thread"+data.getName();
    }
}
