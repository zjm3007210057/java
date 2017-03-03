package alg;

public class QuickSort {
	
	public static void quickSort(int[] arr, int left, int right){
		int dp = 0;
		if(left<right){
			dp = partition(arr, left, right);
			quickSort(arr, left, dp-1);
			quickSort(arr, dp+1, right);
		}
	}
	public static int partition(int[] arr, int left, int right){
		int pivot = arr[left];
		while(left<right){
			while(left<right && arr[right] >= pivot){
				right--;
			}
			if(left < right){
				arr[left] = arr[right];
			}
			while(left < right && arr[left] <= pivot){
				left++;
			}
			if(left < right){
				arr[right] = arr[left];
			}
		}
		arr[left] = pivot;
		return left;
	}
	
	public static void main(String[] args){
		int[] arr = {2,1};
		quickSort(arr,0,arr.length-1);
		for(int i=0; i<arr.length; i++){
			System.out.print(arr[i]+",");
		}
	}

}
