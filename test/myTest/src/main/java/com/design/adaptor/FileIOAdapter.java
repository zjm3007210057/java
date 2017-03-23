package com.design.adaptor;

import java.io.*;
import java.util.Properties;

/**
 * Created by zjm on 2017/3/22.
 */
public class FileIOAdapter extends Properties implements FileIO{

    @Override
    public void readFromFile(String fileName) throws IOException{
        InputStream is = new FileInputStream(fileName);
        this.load(is);
    }

    @Override
    public void writeToFile(String fileName) throws IOException{
        OutputStream os = new FileOutputStream(fileName);
        this.store(os, "写入文件" + fileName);
    }

    @Override
    public void setValue(String key, String value) {
        this.setProperty(key, value);
    }

    @Override
    public String getValue(String key) {
        return this.getProperty(key, "defaultValue");
    }
}
