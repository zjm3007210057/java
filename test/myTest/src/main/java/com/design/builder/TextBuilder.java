package com.design.builder;

/**
 * Created by zjm on 2017/3/27.
 */
public class TextBuilder extends Builder {

    private StringBuffer buffer = new StringBuffer();

    @Override
    public void buildTitle(String title) {
        buffer.append("======================\r\n");
        buffer.append("[" + title + "]");
        buffer.append("\r\n");
    }

    @Override
    public void buildBody(String body) {
        buffer.append('>' + body + "\r\n");
        buffer.append("\r\n");
    }

    @Override
    public void buildItems(String[] items) {
        for (int i = 0; i <items.length ; i++) {
            buffer.append("   ." + items[i] + "\r\n");
        }
        buffer.append("\r\n");
    }

    @Override
    public void close() {
        buffer.append("==========================\r\n");
    }

    public String getResult(){
        return buffer.toString();
    }
}
