package leetCode;

public class MoveZeroes {
	
	public void moveZeroes(int[] nums) {
		int count = 0;
        for(int i=0; i<nums.length-count; i++){
        	if(nums[i] == 0){
        		int n = i;
        		int m = nums[n];
        		count++;
        		for(int j=nums.length-count; j>i; i++){
        			print(nums);
        			nums[i] = nums[i+1];
        			System.out.println();
        		}
        		nums[nums.length-count] = m;
        		i = -1;
        	}
        	System.out.println("==========");
        }
    }
	public void print(int[] arr){
		for(int i=0; i<arr.length; i++){
			System.out.print(arr[i]+",");
		}
	}
	public static void main(String[] args){
		MoveZeroes mz = new MoveZeroes();
		int[] arr = {0,1,0,3,11};
		mz.moveZeroes(arr);
		for(int i=0; i<arr.length; i++){
			System.out.println(arr[i]);
		}
	}

}
