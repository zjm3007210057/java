package com.algorithm.sort;

/**
 * Created by zjm on 11/05/2017.
 */
public class BubbleSort {

    public static void sort(int[] arr){
        int len = arr.length;
        int temp;
        for(int i=0; i<len; i++){
            for(int j=0; j<len; j++){
                if(arr[i] < arr[j]){
                    temp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{43, 23, 12, 53, 29, 43, 76, 19, 32, 23};
        sort(arr);
        for(int k=0; k<10; k++){
            System.out.println(arr[k]);
        }
    }
}
