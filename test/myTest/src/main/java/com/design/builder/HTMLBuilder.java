package com.design.builder;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by zjm on 2017/3/27.
 */
public class HTMLBuilder extends Builder {

    private String fileName;

    private PrintWriter writer;

    @Override
    public void buildTitle(String title) {
        fileName = title + ".html";
        try{
            writer = new PrintWriter(new FileWriter(fileName));
        }catch(IOException e){
            e.printStackTrace();
        }
        writer.println("<html><head><title>" + title + "</title></head><body>");
        writer.println("<h1>" + title + "</h1>");
    }

    @Override
    public void buildBody(String body) {
        writer.println("<p>" + body + "</p>");
    }

    @Override
    public void buildItems(String[] items) {
        writer.println("<ul>");
        for (int i = 0; i < items.length; i++) {
            writer.println("<li>" + items[i] + "</li>");
        }
        writer.println("</ul>");
    }

    @Override
    public void close() {
        writer.println("</body></html>");
        writer.close();
    }

    public String getResult(){
        return fileName;
    }
}
