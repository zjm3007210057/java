package com.performance;

/**
 * Created by zhjm on 2017/2/16.
 */
public class ArrayCopyTest {

    private static int size = 10000000;

    public static int[] getArray(int size){
        int[] array = new int[size];
        for(int i=0; i<size; i++){
            array[i] = i;
        }
        return array;
    }

    public static void arraycopy(){
        int[] array = getArray(size);
        int[] dst = new int[size];
        long start = System.currentTimeMillis();
        System.arraycopy(array, 0, dst, 0, size);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    public static void myArraycopy(){
        int[] array = getArray(size);
        int[] dst = new int[size];
        long start = System.currentTimeMillis();
        for(int i=0; i<size; i++){
            dst[i] = array[i];
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    public static void main(String[] args) {
        arraycopy();
        myArraycopy();
    }
}
