package concurrent.chapter02;

import java.util.Random;

public class StopThreadUnsafe {
	
	private static Person p = new Person();
	
	public static class Person{
		private String name;
		private int code;
		
		public Person(){
			code = 0;
			name = "0";
		}
		@Override
		public String toString() {
			return "Person [name=" + name + ", code=" + code + "]";
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getCode() {
			return code;
		}
		public void setCode(int code) {
			this.code = code;
		}
	}
	public static class ChangeObjectThread extends Thread{
		@Override
		public void run(){
			while(true){
				synchronized(p){
					int n = (int) (Math.random()*100);
					p.setCode(n);
					try{
						Thread.sleep(100);
					}catch(InterruptedException e){
						e.printStackTrace();
					}
					p.setName(String.valueOf(n));
				}
				Thread.yield();
			}
		}
	} 
	
	public static class ReadThread extends Thread{
		@Override
		public void run(){
			while(true){
				synchronized(p){
					if(p.getCode() != Integer.parseInt(p.getName())){
						System.out.println(p.toString());
					}
				}
				Thread.yield();
			}
		}
	}
	
	public static void main(String[] args)throws InterruptedException{
		/*new ReadThread().start();
		while(true){
			Thread t =new ChangeObjectThread();
			t.start();
			Thread.sleep(100);
			t.stop();
		}*/
		Thread t = new Thread(new Runnable(){
			public void run(){
				while(true){
					if(Thread.currentThread().isInterrupted()){
						System.out.println("thread stoped");
						break;
					}
					System.out.println("I am running");
					Thread.yield();
				}
			}
		});
		t.start();
		Thread.sleep(10);
		t.interrupt();  
	}

}
