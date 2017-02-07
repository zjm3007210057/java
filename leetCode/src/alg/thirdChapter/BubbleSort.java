package alg.thirdChapter;

public class BubbleSort {
	
	private long[] arr;
	private int nElems;
	public BubbleSort(int max){
		arr = new long[max];
		nElems = 0;
	}
	public void insert(long value){
		arr[nElems] = value;
		nElems++;
	}
	public void display(){
		for(int i=0; i<nElems; i++){
			System.out.print(arr[i] + " ");
		}
		System.out.println("");
	}
	public void bubbleSort(){
		for(int i=nElems-1; i>0; i--){
			for(int j=0; j<i; j++){
				if(arr[j] > arr[j+1]){
					long temp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = temp;
				}
			}
		}
	}

}
