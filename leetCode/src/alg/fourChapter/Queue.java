package alg.fourChapter;

public class Queue {
	private int maxSize;
	private String[] arr;
	private int front;
	private int nItems;
	private int rear;
	
	public Queue(int size){
		maxSize = size;
		arr = new String[size];
		front = -1;
		rear = -1;
		nItems = 0;
	}
	public void push(String s){
		if(isFull()){
			System.out.println("队列已满，不能再加入元素！");
			return;
		}
		nItems ++;
		if(rear == maxSize){
			rear = 0;
		}else{
			rear ++;
		}
		arr[rear] = s;
	}
	public String pop(){
		if(isEmpty()){
			System.out.println("队列已空，不能再弹出元素！");
			return "";
		}
		if(front == maxSize){
			front = 0;
		}else{
			front++;
		}
		nItems--;
		String s = arr[front];
		arr[front] = "";
		return s;
	}
	public int size(){
		return nItems;
	}
	public void clear(){
		for(int i=0; i<arr.length; i++){
			arr[i] = "";
		}
	}
	public boolean isEmpty(){
		/*boolean is = true;
		for(int i=0; i<arr.length; i++){
			if(arr[i] != null && arr[i] != ""){
				return false;
			}
		}
		return is;*/
		return front == rear;
	}
	
	public boolean isFull(){
		/*boolean is = true;
		for(int i=0; i<arr.length; i++){
			if(arr[i] == null || arr[i] == ""){
				return false;
			}
		}
		return is;*/
		return ((rear + 1 == front) || (front == 0 && rear == maxSize - 1));
	}

}
