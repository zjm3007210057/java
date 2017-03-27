package com.performance;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;

/**
 * 软引用对象不会立即被jvm收回jvm会根据当前的堆使用情况来判断何时收回，
 * 当堆使用率临近阈值的时候，jvm才会去回收软引用的对象，但如果内存足够，
 * 软引用对象可以在jvm中存活很长一段时间，因此软引用很适合实现对内存敏感的cache
 *
 * 比如一个应用程序，需要记录用户之前的操作，比如浏览器按回退要进到上一个页面，
 * 如果此时重新加载页面，万一之前的对象还没有被清除，相当于此时建立了两遍对象，但如果一直
 * 保存对象在内存中，则又会浪费内存、
 *
 * 当程序中需要引用所占内存比较大的对象时，可以使用软引用
 *
 * 软引用的存在不会妨碍垃圾收集线程对java对象的收集
 * Created by zhjm on 2017/2/15.
 */
public class SoftReferenceDemo {

    public static ReferenceQueue softQueue;

    @Override
    protected void finalize() throws Throwable{
        System.out.println("进入finalize方法");
        super.finalize();
        Reference<SoftReferenceDemo> obj = null;
        try{
            obj = (Reference<SoftReferenceDemo>) softQueue.remove();
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        System.out.println(obj);
        if(obj != null){
            System.out.println("Object for SoftReference is " + obj.get());
        }
        //只有当GC回收时才会输出
        System.out.println("SoftReferenceDemo's finalize called");
    }

    @Override
    public String toString(){
        return "I am SoftReferenceDemo";
    }

    public static void main(String[] args) {
        SoftReferenceDemo demo = new SoftReferenceDemo();//强引用
        softQueue = new ReferenceQueue<SoftReferenceDemo>();//创建引用那个队列
        SoftReference<SoftReferenceDemo> softReference = new SoftReference<SoftReferenceDemo>(demo, softQueue);//创建软引用
        System.out.println(demo);
        demo = null;//删除强引用
//      直接调用gc并不能保证gc
//      同理finalize不可靠，因为你不知道什么时候gc就不知道什么时候finalize被调用
        System.gc();
        System.out.println(demo);
        System.out.println("After GC:soft Get= " + softReference.get());
        System.out.println("分配大内存");
//        byte[] b = new byte[1000*1024*925];//分配一块大内存，强迫GC
        System.out.println("After new byte[]:soft Get= " + softReference.get());
        System.out.println(demo);

        SoftReference<String> sr = new SoftReference<String>(new String("hello"));
        System.out.println(sr.get());
    }
}
