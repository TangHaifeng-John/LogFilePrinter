package com.hf.library;

import java.io.File;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 日志打印管理类
 */
public class FilePrinter implements Runnable {

    /**
     * 写入文件
     */
    LogWriter writer;
    /**
     * 日志队列
     */
    BlockingQueue<String> blockingQueue = new LinkedBlockingQueue<>();
    /**
     * 日志配置
     */
    LogConfig logConfig;

    /**
     * 日志大小检查
     */
    FileSizeChecker fileSizeChecker;

    public FilePrinter(LogConfig logConfig) {
        writer = new LogWriter(logConfig);
        fileSizeChecker = new FileSizeChecker(logConfig);
        this.logConfig = logConfig;

    }

    /**
     * 将需要打印的日志，加入到打印队列中
     *
     * @param data
     */
    public void print(String data) {
        try {
            blockingQueue.put(data);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 写入日志到文件
     *
     * @param data
     */
    public void writeLogToFile(String data) {
        if (!writer.isOpen()) {
            writer.open();
        }
        //处理超出文件大小的任务
        if (fileSizeChecker.deleteFileHalfIfNeed(writer.logFile)) {
            writer.close();
            writer.open();
        }
        writer.write(data);
    }



    @Override
    public void run() {
        try {
            String log;
            while ((log = blockingQueue.take()) != null) {
                writeLogToFile(log);
            }
        } catch (Exception e) {

        }

    }
}
