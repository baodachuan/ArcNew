package com.bdc.blibrary.log;

public abstract class HiLogConfig {
    static final int MAX_LEN = 512;
    static HiStackTraceFormatter Hi_STACK_TRACE_FORMATTER=new HiStackTraceFormatter();
    static HiThreadFormatter HI_THREAD_FORMATTER=new HiThreadFormatter();
    public String getGlobalTag(){
        return "BDC_LOG";
    }

    public boolean enable(){
        return true;
    }
    public boolean includeThread(){
        return false;
    }

    public JsonParser injectJsonParser(){
        return  null;
    }

    public int stackTraceDepth(){
        return 5;
    }

    public HiLogPrinter[] printers(){
        return null;
    }



    public interface JsonParser{
        String toJson(Object src);
    }

}
