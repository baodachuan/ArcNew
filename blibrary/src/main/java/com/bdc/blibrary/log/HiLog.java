package com.bdc.blibrary.log;

import android.util.Log;

import androidx.annotation.IntDef;
import androidx.annotation.NonNull;

import java.util.Arrays;
import java.util.List;

public class HiLog {
    public static void i(Object... contents) {
        log(HiLogType.I, contents);
    }

    public static void d(Object... contents) {
        log(HiLogType.D, contents);
    }

    public static void e(Object... contents) {
        log(HiLogType.E, contents);
    }

    public static void w(Object... contents) {
        log(HiLogType.W, contents);
    }


    public static void d(String tag, Object... contents) {
        log(HiLogType.D, tag, contents);
    }

    public static void i(String tag, Object... contents) {
        log(HiLogType.I, tag, contents);
    }

    public static void e(String tag, Object... contents) {
        log(HiLogType.E, tag, contents);
    }

    public static void w(String tag, Object... contents) {
        log(HiLogType.W, tag, contents);
    }

    public static void log(@HiLogType.Type int type, Object... contents) {
        log(type, HiLogManager.getInstance().getConfig().getGlobalTag(), contents);
    }

    public static void log(@HiLogType.Type int type, @NonNull String tag, Object... content) {
        log(HiLogManager.getInstance().getConfig(), type, tag, content);
    }

    public static void log(@NonNull HiLogConfig config, @HiLogType.Type int type, @NonNull String tag, Object... content) {
        if (!config.enable()) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        if (config.includeThread()) {
            String threadInfo = HiLogConfig.HI_THREAD_FORMATTER.format(Thread.currentThread());
            sb.append(threadInfo).append("\n");
        }
        if (config.stackTraceDepth() > 0) {
            String stackTrace = HiLogConfig.Hi_STACK_TRACE_FORMATTER.format(new Throwable().getStackTrace());
            sb.append(stackTrace).append("\n");
        }
        String body = parseBody(content, config);
        sb.append(body);
        List<HiLogPrinter> printers =
                config.printers() != null ? Arrays.asList(config.printers()) : HiLogManager.getInstance().getPrinters();
//        Log.println(type,tag,body);
        if (printers == null) {
            return;
        }
        for (HiLogPrinter printer : printers) {
            printer.print(config, type, tag, sb.toString());
        }
    }

    private static String parseBody(Object[] contents, HiLogConfig config) {
        if (config.injectJsonParser() != null) {
            return config.injectJsonParser().toJson(contents);
        }
        StringBuilder sb = new StringBuilder();
        for (Object content : contents) {
            sb.append(content.toString()).append(";");
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }
}
