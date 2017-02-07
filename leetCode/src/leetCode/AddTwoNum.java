package leetCode;

public class AddTwoNum {
	
	public static List addTwoNum(List l1, List l2){
		List header = new List(0);
        List l3 = header;
       int num = 0;
       while(l1 != null || l2 != null){
           if(l1 != null){
               num += l1.val;
               l1 = l1.next;
           }
           if(l2 != null){
               num += l2.val;
               l2 = l2.next;
           }
           l3.next = new List(num%10);
           l3 = l3.next;
           num /= 10;
       }
       if(num == 1){
           l3.next = new List(1);
       }
       return header.next;
	}

}
