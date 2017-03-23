package com.design.adaptor;

import java.io.IOException;

/**
 * Created by zjm on 2017/3/22.
 */
public class FileMain {

    public static void main(String[] args) {
        FileIO f = new FileIOAdapter();
        try{
            f.readFromFile("file.txt");
            f.setValue("year", "2017");
            f.setValue("month", "03");
            f.setValue("day", "22");
            f.writeToFile("newFile.txt");
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
