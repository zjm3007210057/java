package leetCode;

public class MiniInRotatedSort {
	
	public static int findMin(int[] num){
		int end = num.length-1;
		int first = 0;
		int result = num[0];
		if(num[0]>=num[end]){
			result = num[end];
			for(int i=0; Math.pow(2,i)<(num.length-1); i++){
				//用二分法
				if(result<num[(first+end)/2]){
					first = (first+end)/2;
					if(Math.pow(2,i+1)>(num.length-1)){
						if(first!=(end-1)){
							result = num[(first+end)/2];
						}else{
							result = num[end];
						}
					}
				}else if(result>num[(first+end)/2]){
					result = num[(first+end)/2];
					end = (first+end)/2;
					if(end ==0){
						result = num[1];
					}else if(Math.pow(2,i+1)>(num.length-1)){
						result = num[end];
					}
				}else{
					for(int j=i; j<end; j++){
						if(num[j] < num[end]){
							result = num[j];
							return result;
						}else if(num[j]>num[end]){
							result = num[end];
							return result;
						}
					}
				}
			}
		}else{
			result = num[0];
		}
		return result;
	}
	
	public static void main(String[] args){
		int[] arr0 = {7,9,1,2,3,4,5,6};
		int[] arr1 = {2,3,4,5,6,7,9,1};
		int[] arr2 = {4,5,6,7,9,1,2,3};
		int[] arr3 = {1,2,3,4,5,6,7,9};
		int[] arr4 = {3,0,1,2,3};
		int[] arr5 = {3,1,3};
		int[] arr6 = {5,0,4,5,5,5,5};
		int[] arr7 = {3,1,3,3,3,3,3,3,3,3};
		int[] arr8 = {3,3,3,3,1,3};
		int[] arr9 = {3,3,1,3,3,3};
		System.out.println("arr0的最小值为："+findMin(arr0));
		System.out.println("arr1的最小值为："+findMin(arr1));
		System.out.println("arr2的最小值为："+findMin(arr2));
		System.out.println("arr3的最小值为："+findMin(arr3));
		System.out.println("arr4的最小值为："+findMin(arr4));
		System.out.println("arr5的最小值为："+findMin(arr5));
		System.out.println("arr6的最小值为："+findMin(arr6));
		System.out.println("arr7的最小值为："+findMin(arr7));
		System.out.println("arr8的最小值为："+findMin(arr8));
		System.out.println("arr9的最小值为："+findMin(arr9));
	}

}
