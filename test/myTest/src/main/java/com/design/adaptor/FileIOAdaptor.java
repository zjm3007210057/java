package com.design.adaptor;

import java.io.*;
import java.util.Properties;

/**
 * Created by zjm on 2017/3/22.
 */
public class FileIOAdaptor implements FileIO {

    private ThreadLocal<Properties> properties;

    public FileIOAdaptor(){
        properties = ThreadLocal.withInitial(() -> new Properties());
    }

    @Override
    public void readFromFile(String fileName) throws IOException {
        InputStream is = new FileInputStream(fileName);
        properties.get().load(is);
    }

    @Override
    public void writeToFile(String fileName) throws IOException {
        OutputStream os = new FileOutputStream(fileName);
        properties.get().store(os, "写入文件到" + fileName);
    }

    @Override
    public void setValue(String key, String value) {
        properties.get().setProperty(key, value);
    }

    @Override
    public String getValue(String key) {
        return properties.get().getProperty(key, "defaultValue");
    }

}
