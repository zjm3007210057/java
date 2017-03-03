package leetCode;

public class List {
	public int val;
	public List next;
	public List(int dt){
		val = dt;
		next = null;
	} 
	public void displayList(){
		System.out.println("{"+val+"}");
	}
	

}
