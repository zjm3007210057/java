package leetCode;

import java.util.Scanner;

public class ExcelColumnNum {
	
	public static int outNum(String str){
		char[] arr = str.toCharArray();
		int out = 0;
		for(int i=0; i<arr.length; i++){
			out += (arr[i]-64)*Math.pow(26,arr.length-i-1);
		}
		return out;
	}
	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		System.out.println(outNum(input.next()));
	}

}
