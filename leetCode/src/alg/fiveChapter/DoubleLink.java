package alg.fiveChapter;

public class DoubleLink {
	private class Date{
		private Object obj;
		private Date pre = null;
		private Date next = null;
		
		public Date(Object obj){
			this.obj = obj;
		}
	}
	private Date first = null;
	private Date last = null;
	
	public void insertFirst(Object obj){
		Date date = new Date(obj);
		if(first == null){
			date.pre = first;
			date.next = last;
			first = last = date;
		}else{
			first.next = date;
			last.pre = date;
		}
	}
	

}
