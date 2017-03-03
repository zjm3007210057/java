package leetCode;

public class ListNode {
	
	private List first;
	
	public ListNode(){
		first = null;
	}
	//判断是否为空
	public boolean isEmpty(){
		return (first==null);
	}
	//插入数据
	public void insertFirst(int dt){
		List newList = new List(dt);
		newList.next = first;
		first = newList;
	}
	//删除数据
	public List deleteFirst(){
		List temp = first;
		first = first.next;
		return temp;
	}
	//显示
	public void displayListNode(){
		System.out.println("List (first-->last): ");
		List current = first;
		while(current != null){
			current.displayList();
			current = current.next;
		}
		System.out.println(" ");
	}

}
