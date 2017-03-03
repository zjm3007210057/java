package leetCode;

import java.util.Arrays;

public class Candy {
	
	public static void radixSort(int[] data, int radix, int d) {  
        // 缓存数组  
        int[] tmp = new int[data.length];  
        // buckets用于记录待排序元素的信息  
        // buckets数组定义了max-min个桶  
        int[] buckets = new int[radix];  
  
        for (int i = 0, rate = 1; i < d; i++) {  
  
            // 重置count数组，开始统计下一个关键字  
            Arrays.fill(buckets, 0);  
            // 将data中的元素完全复制到tmp数组中  
            System.arraycopy(data, 0, tmp, 0, data.length);  
  
            // 计算每个待排序数据的子关键字  
            for (int j = 0; j < data.length; j++) {  
                int subKey = (tmp[j] / rate) % radix;  
                buckets[subKey]++;  
            }  
  
            for (int j = 1; j < radix; j++) {  
                buckets[j] = buckets[j] + buckets[j - 1];  
            }  
  
            // 按子关键字对指定的数据进行排序  
            for (int m = data.length - 1; m >= 0; m--) {  
                int subKey = (tmp[m] / rate) % radix;  
                data[--buckets[subKey]] = tmp[m];  
            }  
            rate *= radix;  
        }  
    }  
	
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
				arr[left++] = arr[right];
			}
			while(left < right && arr[left] <= pivot){
				left++;
			}
			if(left < right){
				arr[right--] = arr[left];
			}
		}
		arr[left] = pivot;
		return left;
	}
	
	public static int candy(int[] ratings){
		int sum = 0;
		int index = 1;
		int count = 1;
		int m = 0;
		int length = ratings.length;
		if(length == 1){
		    return 1;
		}else if(length == 2){
		    if(ratings[0]==ratings[1]){
		        return 2;
		    }else{
		        return 3;
		    }
		}
		for(int i=0; i<length; i++){
			count = 1;
			index = 1;
			while(i<length-1 && ratings[i]<ratings[i+1]){
				ratings[i] = count;
				sum += ratings[i];
				i++;
				count++;
			}
			while(i<length-1 && ratings[i] > ratings[i+1]){
				index++;
				m = i;
				i++;
				if(i<length-1 && ratings[i]<=ratings[i+1]){
					sum += index;
				}else if(m<length-1 && ratings[m]>ratings[m+1]){
					sum += index;
				}
			}
			if(index<count && index>1){
				sum += (count-index);
			}
			while(i<length-1 && ratings[i]==ratings[i+1]){
				if(i>0 && ratings[i] <= ratings[i-1]){
					sum += 1;
				}else if(i>0 && ratings[i] > ratings[i-1]){
					sum += count;
				}else{
					sum += 1;
				}
				i++;
			}
			if(i==length-1){
				if(ratings[length-1]<=ratings[length-2]){
					sum += 1;
				}else{
					sum += count;
				}
				break;
			}
			i--;
		}
		return sum;
	}
	
	public static void main(String[] args){
		long start = System.currentTimeMillis();
		int[] arr = {5,3,1};
		int[] arr2 = {1,3,4,3,2,1};
		int[] arr1 = {3,2,1,4,2,3,1,1,2,3,4};
		System.out.println(candy(arr));
		long end = System.currentTimeMillis();
		System.out.println("程序运行时间： "+(end-start)+"ms");
	}

}
