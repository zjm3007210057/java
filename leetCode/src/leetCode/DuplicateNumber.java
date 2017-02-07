package leetCode;

public class DuplicateNumber {
	
	public int findDuplicate(int[] nums){
		int result = 0;
		int length = nums.length - 1;
		int[] arr = new int[length];
		for(int i=0; i<=length; i++){
			arr[nums[i] - 1] += 1;
		}
		for(int i=0; i<length; i++){
			if(arr[i] >= 2){
				result = i+1;
				break;
			}
		}
		return result;
	}
	public static void main(String[] args){
		DuplicateNumber du = new DuplicateNumber();
		int[] array = {1,2,3,4,5,6,7,8,9,9};
		System.out.println(du.findDuplicate(array));
	}

}
