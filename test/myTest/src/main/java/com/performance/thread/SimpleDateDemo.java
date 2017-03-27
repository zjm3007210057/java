package com.performance.thread;

import java.text.SimpleDateFormat;

/**
 * Created by zhjm on 2017/3/21.
 */
public class SimpleDateDemo {

    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String[] dateStrings = new String[]{"2000-01-01", "2001-01-01", "2002-01-01", "2003-01-01",
                                "2004-01-01", "2005-01-01", "2006-01-01", "2007-01-01", "2008-01-01", "2009-01-01"};
        SimpleDateThread[] threads = new SimpleDateThread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new SimpleDateThread(sdf, dateStrings[i]);
        }
        for (int i = 0; i < 10; i++) {
            new Thread(threads[i]).start();
        }
    }
}
