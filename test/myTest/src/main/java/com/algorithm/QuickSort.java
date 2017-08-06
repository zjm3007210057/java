package com.algorithm;

/**
 * Created by zjm on 11/05/2017.
 */
public class QuickSort {

    public void quick_sort(int[] arr, int l, int r){
        if(l < r){
            int i = l, j = r, key = arr[l];
            while(i < j){
                //arr = [43, 23, 12, 53, 29, 76, 19, 32]
                //如果x小于数组后面的值，指针向前移一位，如果大于，则将数组后面的值填到X的位置
                while(i < j && key <= arr[j]){
                    j--;
                }
                //43 [32, 23, 12, 53, 29, 76, 19, 32]
                arr[i] = arr[j];
                while(i < j && key >= arr[i]){
                    i++;
                }
                arr[j] = arr[i];
                //i=3, j=7
                //43 [32, 23, 12, 53, 29, 76, 19, 53]
            }
            //[32, 23, 12, 19, 29, 43, 76, 53]
            arr[i] = key;
            quick_sort(arr, l, i-1);
            quick_sort(arr, i+1, r);
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{43, 23, 12, 53, 29, 43, 76, 19, 32, 23};
        new QuickSort().quick_sort(arr, 0, 9);
        for(int k=0; k<10; k++){
            System.out.println(arr[k]);
        }
    }
}
