package alg.fourChapter;

public class Stack {
	private int maxSize;
	private long[] array;
	private int top;
	
	private Stack(int size){
		maxSize = size;
		array = new long[size];
		top = -1;
	}
	private void push(long element){
		if(isFull()){
			return ;
		}
		top ++;
		array[top] = element;
	}
	public long pop(){
		if(isEmpty()){
			return -1;
		}
		long result = array[top];
		top--;
		return result;
	}
	public long peek(){
		return array[top];
	}
	public boolean isEmpty(){
		return top == -1;
	}
	public boolean isFull(){
		return top == (maxSize-1);
	}

}
