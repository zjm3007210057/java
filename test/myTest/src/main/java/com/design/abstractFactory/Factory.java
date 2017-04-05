package com.design.abstractFactory;

/**
 * Created by zjm on 2017/3/27.
 */
public abstract class Factory {

    public static Factory getFactory(String className){
        Factory factory = null;
        try{
            factory = (Factory) Class.forName(className).newInstance();
        }catch(ClassNotFoundException e){
            System.out.println("没有找到 " + className + " 类");
            e.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
        return factory;
    }

    public abstract Link createLink(String caption, String url);

    public abstract Tray createTray(String caption);

    public abstract Page createPage(String title, String author);
}
