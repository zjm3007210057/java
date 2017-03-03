package alg.fiveChapter;

public class SortLink {
	private class Date{
		private Object obj;
		private Date next = null;
		Date(Object obj){
			this.obj = obj;
		}
	}
	private Date first = null;
	
	public void insert(Object obj){
		Date date = new Date(obj);
		Date pre = null;
		Date cur = first;
		while(cur != null && (Integer.valueOf(date.obj.toString()).intValue() > Integer.valueOf(cur.obj.toString()).intValue())){
			pre = cur;
			cur = cur.next;
		}
		if(pre == null)
			first = date;
		else
			pre.next = date;
		date.next = cur;
	}
	public Object deleteFirst()throws Exception{
		if(first == null)
			throw new Exception("empty");
		Date temp = first;
		first = first.next;
		return temp.obj;
	}
	public void display(){
		if(first == null)
			System.out.println("empty");
		System.out.print("first -> last :");
		Date cur = first;
		while(cur != null){
			System.out.print(cur.obj.toString() + "->");
			cur = cur.next;
		}
		System.out.println("\n");
	}
	public static void main(String[] args)throws Exception{
		SortLink sl = new SortLink();
		sl.insert(80);  
        sl.insert(2);  
        sl.insert(100);  
        sl.display();  
        System.out.println(sl.deleteFirst());  
        sl.insert(33);  
        sl.display();  
        sl.insert(99);  
        sl.display(); 
	}

}
