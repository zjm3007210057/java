package alg;

import java.net.URL;

public class DynamicPlanning {
	
	public static int dynamicPlanning(int[] arr, int n){
		int[] array = new int[n];
		int len = 1;
		for(int i=0; i<n; i++){
			array[i] = 1;
			for(int j=0; j<i; j++){
				if(arr[i]>=arr[j] && array[j]+1>array[i]){
					array[i] = array[j] + 1;
				}
			}
			if(array[i]>len){
				len = array[i];
			}
		}
		return len;
	}
	public static void backPack(){
		
	}
	
	public static void main(String[] args){
		int[] arr = {3,4,5,6,7,8,9,1,2,3};
		System.out.println(dynamicPlanning(arr,10));
	}

}
