package com.hf.library;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class FileLog {

    static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";
    static SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DEFAULT_DATE_FORMAT, Locale.US);
    static LogConfig logConfig = null;
    static FilePrinter filePrinter;

    public static void init(LogConfig logConfig) {
        FileLog.logConfig = logConfig;
        filePrinter = new FilePrinter(logConfig);
        enable();
    }


    /**
     * 启动打印任务
     */
    static void enable() {
        Thread thread = new Thread(filePrinter);
        thread.setName("FilePrinter");
        thread.start();
    }

    public static void e(String msg, Throwable throwable) {
        filePrinter.print(convertString(msg, throwable));
    }

    public static void i(String msg) {
        filePrinter.print(msg);
    }

    private static String convertString(String msg, Throwable throwable) {
        String date = simpleDateFormat.format(new Date());
        return date + " " + msg + System.lineSeparator() + FileLogUtil.getStackTraceString(throwable);
    }
}
