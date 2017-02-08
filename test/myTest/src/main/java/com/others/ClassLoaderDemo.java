package com.others;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by zhjm on 2016/12/27.
 */
public class ClassLoaderDemo extends ClassLoader {

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        return super.loadClass(name, resolve);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        Class clazz = null;
        String fileStub = name.replace(".", "/");
        String javaFileName = fileStub + ".java";
        String classFileName = fileStub + ".class";
        File javaFile = new File(javaFileName);
        File classFile = new File(classFileName);
        //当指定的java源文件存在，且class文件不存在，或者java源文件的修改时间比class文件的修改时间晚时，
        //重新编译
        if(javaFile.exists() && (!classFile.exists()) ||
                javaFile.lastModified() > classFile.lastModified()){
            try{
                //如果编译失败，或者该class文件不存在
                if(!compile(javaFileName) || !(classFile.exists())){
                    throw new ClassNotFoundException("ClassNotFoundException:" + javaFileName);
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        //如果class文件存在，系统负责将该文件转换成Class对象
        if(classFile.exists()){
            try{
                //将Class文件的二进制数据读入数组
                byte[] raw = getBytes(classFileName);
                //调用ClassLoader的defineClass方法将二进制数据转换成CLass对象
                clazz = defineClass(name, raw, 0, raw.length);
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        return clazz;
    }

    private byte[] getBytes(String fileName)throws IOException{
        File file = new File(fileName);
        long len = file.length();
        byte[] bytes = new byte[(int)len];
        FileInputStream fin = new FileInputStream(file);
        int r = fin.read(bytes);
        if(r != len){
            throw new IOException("没有读完全部文件: " + r + "!= " + len);
        }
        return bytes;
    }

    private boolean compile(String javaFile)throws IOException{
        System.out.println("正在编译 " + javaFile + " ......");
        //调用系统的javac命令
        Process p = Runtime.getRuntime().exec("javac" + javaFile);
        try{
            p.waitFor();
        }catch(InterruptedException ie){
            System.out.println(ie);
        }
        //获取javac线程的退出值
        int ret = p.exitValue();
        //返回编译是否成功
        return ret == 0;
    }

    public static void main(String[] args) throws Exception{
        //如果运行该程序时没有参数，既没有目标类
        if(args.length < 1){
            System.out.println("缺少目标类，请按照如下格式运行Java源文件:");
            System.out.println("java ClassLoaderDemo ClassName");
        }
        //第一个参数是需要运行的类
        String proClass = args[0];
        //剩下的参数将作为运行的目标类时的参数
        //将这些参数复制到一个新数组中
        String[] proArgs = new String[args.length - 1];
        System.arraycopy(args, 1, proArgs, 0, proArgs.length);
        ClassLoaderDemo demo = new ClassLoaderDemo();
        //加载需要运行的类
        Class<?> clazz = demo.loadClass(proClass);
    }
}
