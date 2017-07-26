package com.gosun.isap.warn.api.alert.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 多线程，保存文件到本地
 * <p>创建时间：2017-5-18 15:47</p>
 *
 * @author 娄存银
 * @version 1.0
 */
public class ResourceWriter implements Runnable {
    private static final Logger LOGGER = LoggerFactory.getLogger(ResourceWriter.class);
    /**
     * 线程池
     */
    private static final Executor EXECUTOR = new ThreadPoolExecutor(
            0,
            20,
            0L,
            TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>());

    private static final int BYTES_LENGTH = 1024;
    private byte[] bytes;
    private File file;
    private InputStream inputStream;

    public ResourceWriter(byte[] bytes, File file) {
        this.bytes = bytes;
        this.file = file;
    }

    public ResourceWriter(InputStream inputStream, File file) {
        this.file = file;
        this.inputStream = inputStream;
    }

    /**
     * 将文件保存到本地
     */
    public void write() {
        EXECUTOR.execute(this);
    }

    @Override
    public void run() {
        try {
            if (bytes != null) {
                writeBytes();
            } else if (inputStream != null) {
                writeInputStream();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeBytes() throws IOException {
        if (file == null || bytes == null) {
            LOGGER.info("writeBytes : {}","文件为 null 或者 bytes 为 null 写入失败");
            return;
        }
        OutputStream outputStream = null;
        if (!file.exists()) {
            if (!file.createNewFile()) {
                LOGGER.info("writeBytes : {}","文件不存在，创建文件失败，写入失败");
                return;
            }
        }
        try {
            outputStream = new FileOutputStream(file);
            outputStream.write(bytes);
        } catch (IOException e) {
            LOGGER.error("writeBytes : 写入文件异常",e);
        } finally {
            if (outputStream != null) {
                outputStream.close();
            }
        }

    }

    private void writeInputStream() throws IOException {
        if (file == null || inputStream == null) {
            LOGGER.info("writeInputStream : {}","文件为 null 或者 inputStream 为 null 写入失败");
            return;
        }
        OutputStream outputStream = null;
        byte[] bytes = new byte[BYTES_LENGTH];
        if (!file.exists()) {
            if(!file.createNewFile()){
                LOGGER.info("writeInputStream : {}","文件不存在，创建文件失败，写入失败");
                return;
            }
        }
        try {
            outputStream = new FileOutputStream(file);
            while (inputStream.read(bytes) != -1) {
                outputStream.write(bytes);
            }
            outputStream.flush();
        } catch (IOException e) {
            LOGGER.error("writeInputStream : 写入文件异常",e);
        } finally {
            if (outputStream != null) {
                outputStream.close();
                inputStream.close();
            }
        }
    }
}
