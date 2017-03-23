package com.design.adaptor;

import java.io.IOException;

/**
 * Created by zjm on 2017/3/22.
 */
public interface FileIO {

    void readFromFile(String fileName) throws IOException;

    void writeToFile(String fileName) throws IOException;

    void setValue(String key, String value);

    String getValue(String key);
}
