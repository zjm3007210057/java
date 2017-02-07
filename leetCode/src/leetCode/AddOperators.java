package leetCode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddOperators {
	
	public List<String> addOperators(String num, int target) {
		int length = num.length();
		int[] arr = new int[length];
		for(int i=0; i<length; i++){
			arr[i] = Integer.parseInt(num.substring(i));
		}
        return null;
    }
	//加减乘运算结果
	public List<String> operate(int num, int end, int result){
		List<String> list = new ArrayList<String>();
		if((num+end) == result){
			list.add(num+"+"+end);
		}
		if((num - end) == result){
			list.add(num+"-"+end);
		}
		if((num * end) == result){
			list.add(num+"*"+end);
		}
		return list;
	}
	//数组迭代方法
	public List<String> iterative(int[] arr, int length, int result){
		if(length == 2){
			return operate(arr[0], arr[1], result);
		}else{
			return iterative(Arrays.copyOf(arr, length - 1), length - 1, result);
		}
	}
	//获取各种组合数组
	public List<int[]> getArray(String str, int length){
		List<int[]> list = new ArrayList<int[]>();
		int[] arr = new int[str.length()];
		while(length > 0){
			getArray(str.substring(0, length-1),length-1);
		}
		if(str.length() == 1){
			list.add(arr);
		}
		return null;
	}

}
