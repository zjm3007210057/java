package com.others;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.util.Date;

/**
 * Created by zhjm on 2016/12/22.
 */
public class SerilizationDemo {

    public void test()throws Exception{
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(os);
//        out.write(new User("jim", 16));
    }
}
