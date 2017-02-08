package alg.fourChapter;

public class BracketsMatching {
	
	private StringStack stringStack;
	private int maxSize;
	
	public BracketsMatching(int size){
		maxSize = size;
		stringStack = new StringStack(maxSize);
	}
	
	public boolean isLeftBrackts(String s){
		boolean is = false;
		if(s.equals("{") || s.equals("(") || s.equals("[") || s.equals("<") ){
			is = true;
		}
		return is;
	}
	
	public boolean isRightBrackts(String s){
		boolean is = false;
		if(s.equals("}") || s.equals(")") || s.equals("]") || s.equals(">") ){
			is = true;
		}
		return is;
	}
	
	public boolean isMatching(String left, String right){
		boolean is = false;
		if(left.equals("{") && right.equals("}")){
			is = true;
		}else if(left.equals("(") && right.equals(")")){
			is = true;
		}else if(left.equals("[") && right.equals("]")){
			is = true;
		}else if(left.equals("<") && right.equals(">")){
			is = true;
		}
		return is;
	}
	
	public void push(String s){
		if(isLeftBrackts(s)){
			stringStack.push(s);
		}
	}
	
	public String pop(){
		if(isEmpty()){
			return "";
		}
		return stringStack.pop();
	}
	
	public boolean isEmpty(){
		return stringStack.isEmpty();
	}
	
	public boolean isFull(){
		return stringStack.isFull();
	}
	
	public boolean bracktsMatching(String str){
		boolean is = true;
		int length = str.length();
		for(int i=0; i<length; i++){
			if(isLeftBrackts(str.substring(i))){
				push(str.substring(i));
			}
			if(isRightBrackts(str.substring(i))){
				String popString = pop();
				if(!isMatching(popString, str.substring(i))){
					return false;
				}
			}
		}
		if(!isEmpty()){
			is = false;
		}
		return is;
	}
	
}
