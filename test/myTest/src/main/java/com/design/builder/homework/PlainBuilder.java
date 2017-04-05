package com.design.builder.homework;

/**
 * Created by zjm on 2017/3/27.
 */
public class PlainBuilder implements Builder {

    private StringBuffer buffer = new StringBuffer();

    @Override
    public void makeTitle(String title) {
        buffer.append("==========================\r\n");
        buffer.append(title);
        buffer.append("\r\n");
    }

    @Override
    public void makeBody(String body) {
        buffer.append(">" + body);
        buffer.append("\r\n");
    }

    @Override
    public void makeItems(String[] items) {
        buffer.append("\r\n");
        for (int i = 0; i < items.length; i++) {
            buffer.append("." + items[i]);
            buffer.append("\r\n");
        }
        buffer.append("\r\n");
    }

    @Override
    public void close() {
        buffer.append("======================\r\n");
    }

    @Override
    public Object getResult() {
        return buffer.toString();
    }
}
