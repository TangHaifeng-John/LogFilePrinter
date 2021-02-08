package com.hf.library;

public class LogConfig {


    /**
     * 绝对路径
     */
    public String absolutePath;

    /**
     * 设置tag
     */
    private String tag;


    public long getFileSize() {
        return fileSize;
    }

    /**
     * 设置日志文件大小
     * 默认5M
     */
    private long fileSize = 5 * 1024 * 1024;


    private LogConfig() {

    }

    public static class Builder {
        private String absolutePath;
        private String tag;
        /**
         * 设置日志文件大小
         * 默认5M
         */
        private long fileSize = 5 * 1024 * 1024;

        public Builder absolutePath(String absolutePath) {
            this.absolutePath = absolutePath;
            return this;
        }

        public Builder tag(String tag) {
            this.tag = tag;
            return this;
        }

        public Builder fileSize(long fileSize) {
            this.fileSize = fileSize;
            return this;
        }

        public LogConfig build() {
            LogConfig logConfig = new LogConfig();
            logConfig.absolutePath = this.absolutePath;
            logConfig.tag = this.tag;
            logConfig.fileSize = this.fileSize;
            return logConfig;
        }

    }


}
