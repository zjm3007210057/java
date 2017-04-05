package com.design.abstractFactory;

import java.util.Iterator;

/**
 * Created by zjm on 2017/3/28.
 */
public class ListTray extends Tray {

    public ListTray(String caption) {
        super(caption);
    }

    public String makeHtml() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("<li>\n");
        buffer.append(caption + "\n");
        buffer.append("<ul>\n");
        Iterator it = tray.iterator();
        while(it.hasNext()){
            Item item = (Item)it.next();
            buffer.append(item.makeHtml());
        }
        buffer.append("</ul>\n");
        buffer.append("</li>\n");
        return buffer.toString();
    }
}
