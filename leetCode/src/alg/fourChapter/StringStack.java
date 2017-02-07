package alg.fourChapter;

public class StringStack {
	
	private int maxSize;
	private String[] array;
	private int top;
	
	public StringStack(int size){
		maxSize = size;
		array = new String[size];
		top = -1;
	}
	public void push(String element){
		if(isFull()){
			return ;
		}
		top ++;
		array[top] = element;
	}
	public String pop(){
		if(isEmpty()){
			return "";
		}
		String result = array[top];
		top--;
		return result;
	}
	public String peek(){
		return array[top];
	}
	public boolean isEmpty(){
		return top == -1;
	}
	public boolean isFull(){
		return top == (maxSize-1);
	}

}
