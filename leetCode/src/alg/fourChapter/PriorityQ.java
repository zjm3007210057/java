package alg.fourChapter;

public class PriorityQ {
	private int[] arr;
	private int nElements;
	private int maxSize;
	
	public PriorityQ(int size){
		maxSize = size;
		arr = new int[size];
		nElements = 0;
	}
	
	public boolean isEmpty(){
		return nElements == 0;
	}
	public boolean isFull(){
		return nElements == maxSize;
	}
	public int size(){
		return nElements;
	}
	public int findPosition(int e){
		int low = 0;
		int high = nElements-1;
		while(low <= high){
			int middle = (low + high) / 2;
			if(e == arr[middle]){
				return middle;
			}else if(middle > 1 && arr[middle-1]<= e && e < arr[middle]){
				return middle;
			}else if(middle < nElements - 1 && arr[middle]< e && e <= arr[middle + 1]){
				return middle+1;
			}else if(e < arr[middle]){
				high = middle - 1;
			}else if(e > arr[middle]){
				low = middle + 1;
			}
		}
		return -1;
	}
	/*
	 * 
	 */
	public void push(int e){
		if(isFull()){
			System.out.println("队列已满,不能push");
			return;
		}
		if(nElements == 0){
			arr[0] = e;
		}else if(arr[nElements-1] <= e){
			arr[nElements] = e;
		}else if(arr[0] >= e){
			for(int i=nElements; i>0; i--){
				arr[i] = arr[i-1];
			}
			arr[0] = e;
		}else{
			int position = findPosition(e);
			for(int i=nElements; i>position; i--){
				arr[i] = arr[i-1];
			}
			arr[position] = e;
		}
		nElements++;
	}
	//
	
	public int pop(){
		nElements--;
		return arr[nElements];
	}
	
	public void clear(){
		for(int i=0; i<nElements; i++){
			arr[i] = -1;
		}
	}
	//
	
	public void display(){
		for(int i=0; i<nElements; i++){
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}
	/*
	 * 
	 //
	 */
	public static void main(String[] args){
		PriorityQ pq = new PriorityQ(10);
		pq.push(7);
		pq.push(3);
		pq.push(5);
		pq.push(17);
		pq.push(13);
		pq.push(8);
		pq.push(7);
		pq.push(9);
		pq.display();
		System.out.println("pq.pop()"+pq.pop()+"弹出");
		pq.display();
		System.out.println("pq.pop()"+pq.pop()+"弹出");
		pq.display();
		System.out.println("pq.pop()"+pq.pop()+"弹出");
		pq.display();
		System.out.println("pq.pop()"+pq.pop()+"弹出");
		pq.display();
		System.out.println("pq.pop()"+pq.pop()+"弹出");
		pq.display();
		System.out.println("pq.pop()"+pq.pop()+"弹出");
		pq.display();
		System.out.println("pq.push(11,7,6)");
		pq.push(11);
		pq.push(7);
		pq.push(6);
		pq.display();
	}

}
