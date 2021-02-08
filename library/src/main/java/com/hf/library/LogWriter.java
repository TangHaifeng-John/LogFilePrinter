package com.hf.library;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


/**
 * 文件输入流
 */
public class LogWriter {
    File logFile;
    BufferedWriter bufferedWriter;
    LogConfig logConfig;
    private volatile boolean isStart = false;
    private volatile boolean needStop = false;

    public LogWriter(LogConfig logConfig) {
        this.logConfig = logConfig;
        open();
    }


    /**
     * 打开文件输入流
     */
    public void open() {
        try {
            logFile = new File(logConfig.absolutePath);

            File logFileDirection = logFile.getParentFile();
            if (!logFileDirection.exists()) {
                logFileDirection.mkdirs();
            }
            if (!logFile.exists()) {
                logFile.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(logFile, true);
            bufferedWriter = new BufferedWriter(fileWriter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 关闭任务
     */
    public void close() {
        try {
            bufferedWriter.close();
            logFile = null;
            needStop = true;
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 是否打开
     *
     * @return
     */
    public boolean isOpen() {
        return bufferedWriter != null && logFile != null && logFile.exists();
    }


    /**
     * @param log 需要写入的文件内容
     */
    public void write(String log) {
        try {
            bufferedWriter.write(log);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
