package com.performance.thread;

import com.me.string.StringUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zhjm on 2017/3/21.
 */
public class SimpleDateThread implements Runnable {

    private SimpleDateFormat sdf;

    private String dataString;

    public SimpleDateThread(SimpleDateFormat sdf, String dataString) {
        this.sdf = sdf;
        this.dataString = dataString;
    }

    public void run() {
        try{
            Date dateStr = sdf.parse(dataString);
            String newDateStr = sdf.format(dateStr).toString();
            if(!StringUtil.equals(newDateStr, dataString)){
                System.out.println("ThreadName=" + Thread.currentThread().getName() +
                        "报错了，日期字符串：" + dataString + "转换成的日期为：" + newDateStr);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
