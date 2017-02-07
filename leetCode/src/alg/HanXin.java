package alg;

public class HanXin {
	
	 public static int hanXin(int a, int b, int c){
		 if(a<3 && b<5 && c<7){
			 for(int i=1; i<15; i++){
				 if((i*7+c)%5==b && (i*7+c)%3==a && (i*7+c)<=100 && (i*7+c)>=10){
					 return i*7+c;
				 }else if((i*7+c)>100){
					 return -1;
				 }
			 }
		 }else{
			 System.out.println("输入错误，请从新输入3个数");
		 }
		return -1;
	 }
	 
	 public static void main(String[] args){
		 System.out.println(hanXin(1,3,5));
	 }

}
