package alg.fiveChapter;

public class FirstLastLink {
	private class Date{
		private Object obj;
		private Date next = null;
		Date(Object obj){
			this.obj = obj;
		}
	}
	private Date first = null;
	private Date last = null;
	
	public void insertFirst(Object obj){
		Date date = new Date(obj);
		if(first == null)
			last = date;
		date.next = first;
		first = date;
	}
	public void insertLast(Object obj){
		Date date = new Date(obj);
		if(first == null)
			first = date;
		else
			last.next = date;
		last = date;
	}
	public Object deleteFirst()throws Exception{
		if(first == null)
			throw new Exception("empty");
		Date temp = first;
		if(first.next == null)
			last = null;
		first = first.next;
		return temp.obj;
	}
	public void deleteLast()throws Exception{
		if(first == null)
			throw new Exception("empty");
		if(first.next == null){
			first = null;
			last = null;
		}else{
			Date temp = first;
			while(temp.next != null){
				if(temp.next == last){
					last = temp;
					last.next = null;
					break;
				}
				temp = temp.next;
			}
		}
	}
	public void display(){
		if(first == null)
			System.out.println("empty");
		Date cur = first;
		while(cur != null){
			System.out.print(cur.obj.toString() + "->");
			cur = cur.next;
		}
		System.out.println("\n");
	}
	public static void main(String[] args)throws Exception{
		FirstLastLink fll = new FirstLastLink();
		fll.insertFirst(2); 
		fll.insertFirst(1);
		fll.display();
		fll.insertLast(3);
		fll.display();
		fll.deleteFirst();
		fll.display();
		fll.deleteLast();
		fll.display();
	}

}
