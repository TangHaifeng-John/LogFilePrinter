package com.hf.library;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

public class FileSizeChecker {


    private LogConfig logConfig;

    public FileSizeChecker(LogConfig logConfig) {
        this.logConfig = logConfig;
    }

    /**
     * 是否需要重新新的日志文件,会根据配置的日志大小来决定
     *
     * @param file
     * @return
     * @see {{@link LogConfig#getFileSize()}}
     */
    private boolean needCreateNewLogFile(File file) {

        if (file.length() >= logConfig.getFileSize()) {
            return true;
        }
        return false;
    }


    /**
     * 通过删除一半的规则，来减少文件大小
     *
     * @param file
     * @return
     */
    public boolean deleteFileHalfIfNeed(File file) {

        if (!needCreateNewLogFile(file)) {
            return false;
        }

        RandomAccessFile raf = null;
        try {
            raf = new RandomAccessFile(file, "rw");

            raf.seek(file.length() / 2);

            File backupFile = new File(file.getAbsolutePath() + ".tmp");

            if (backupFile.exists()) {
                backupFile.delete();
            }
            backupFile.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(backupFile);
            byte[] b = new byte[1024];
            int len;
            while ((len = raf.read(b)) != -1) {
                fileOutputStream.write(b, 0, len);
            }
            raf.close();
            fileOutputStream.close();
            if (file.delete()) {
                backupFile.renameTo(file);
            }
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            try {
                if (raf != null) {
                    raf.close();
                }
            } catch (IOException e) {
            }
        }
    }

}
