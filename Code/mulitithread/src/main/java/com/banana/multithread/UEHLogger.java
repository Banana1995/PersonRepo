package com.banana.multithread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UEHLogger implements Thread.UncaughtExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("1231312313");
        logger.error("未捕获异常处理器处理中");
    }
}
