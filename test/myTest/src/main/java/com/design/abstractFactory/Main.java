package com.design.abstractFactory;

/**
 * Created by zjm on 2017/3/28.
 */
public class Main {

    public static void main(String[] args) {
        Factory factory = Factory.getFactory("com.design.abstractFactory.ListFactory");
        Link people = factory.createLink("人民日报", "http://www.people.com.cn");
        Link gmw = factory.createLink("光明日报", "http://www.gmw.cn");
        Link google = factory.createLink("谷歌", "http://www.google.com");
        Link baidu = factory.createLink("百度", "http://www.baidu.com");

        Tray tryNews = factory.createTray("日报");
        tryNews.add(people);
        tryNews.add(gmw);

        Tray traySearch = factory.createTray("搜索");
        traySearch.add(google);
        traySearch.add(baidu);

        Page page = factory.createPage("LinkPage", "jm");
        page.add(tryNews);
        page.add(traySearch);
        page.output();
    }
}
