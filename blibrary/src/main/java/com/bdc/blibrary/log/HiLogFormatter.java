package com.bdc.blibrary.log;

public interface HiLogFormatter<T> {
    String format(T data);
}
