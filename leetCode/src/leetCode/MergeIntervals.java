package leetCode;
import java.util.ArrayList;
import java.util.List; 

class Interval{
	int start;
	int end;
	Interval(){
		start = 0;
		end = 0;
	}
	Interval(int s, int e){
		start = s;
		end = e;
	}
}

public class MergeIntervals {
	
	public static void quickSort(List<Interval> intervals, int left, int right){
		int dp = 0;
		if(left<right){
			dp = partition(intervals, left, right);
			quickSort(intervals, left, dp-1);
			quickSort(intervals, dp+1, right);
		}
	}
	public static int partition(List<Interval> intervals, int left, int right){
		Interval pivot = new Interval();
		pivot.start = intervals.get(left).start;
		pivot.end = intervals.get(left).end;
		while(left<right){
			while(left<right && intervals.get(right).start >= pivot.start){
				right--;
			}
			if(left < right){
				intervals.get(left).start = intervals.get(right).start;
				intervals.get(left).end = intervals.get(right).end;
			}
			while(left < right && intervals.get(left).start <= pivot.start){
				left++;
			}
			if(left < right){
				intervals.get(right).start = intervals.get(left).start;
				intervals.get(right).end = intervals.get(left).end;
			}
		}
		intervals.get(left).start = pivot.start;
		intervals.get(left).end = pivot.end;
		return left;
	}
	public static List<Interval> merge(List<Interval> intervals){
		int length = intervals.size();
		if(length == 0 || length==1){
			return intervals;
		}
		
		//按start对intervals进行排序
		quickSort(intervals, 0, length-1);
		for(int i=0; i<length; i++){
			if(i<length-1 && intervals.get(i).end>=intervals.get(i+1).start){
				if(intervals.get(i).end<=intervals.get(i+1).end){
					intervals.get(i).end = intervals.get(i+1).end;
				}
				intervals.remove(i+1);
				length--;
				i--;
			}
		}
		return intervals;
	}
	
	public static void main(String[] args){
		List<Interval> intervals = new ArrayList<Interval>();
		intervals.add(new Interval(2,3));
		intervals.add(new Interval(4,5));
		intervals.add(new Interval(6,7));
		intervals.add(new Interval(8,9));
		intervals.add(new Interval(1,4));
		intervals.add(new Interval(0,1));
//		Interval[] arr = (Interval[]) intervals.toArray();
//		for(int i=0; i<intervals.toArray().length; i++){
//			System.out.println(arr[i]);
//		}
		merge(intervals);
		for(Interval interval : intervals){
			System.out.print(interval.start+",");
			System.out.print(interval.end);
			System.out.println();
		}
	}

}
