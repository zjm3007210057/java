package com.design.abstractFactory;

/**
 * Created by zjm on 2017/3/28.
 */
public class ListLink extends Link {

    public ListLink(String caption, String url) {
        super(caption, url);
    }

    public String makeHtml() {
        return "<li><a href=\"" + url + "\">" + caption + "</a></li>\n";
    }
}
