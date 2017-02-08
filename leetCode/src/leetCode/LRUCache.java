package leetCode;

import java.util.ArrayList;
import java.util.HashMap;

public class LRUCache {
	
	private int count = 0;
	private int capacity;
	private HashMap<Integer,Integer> cache = new HashMap<Integer,Integer>(2*capacity);
	private ArrayList<Integer> link = new ArrayList<Integer>();
	public LRUCache(int capacity){
		this.capacity = capacity;
	}
	
	public int get(int key){
		Integer num = cache.get(key);
		if(num==null){
			return -1;
		}else{
			link.remove((Integer)key);
			link.add(key);
			return num;
		}
	}
	
	public void set(int key, int value){
		if(key<=0){
			System.out.println("key必须大于0，请从新设置key");
			return;
		}
		if(count<capacity){
			cache.put(key, value);
			link.add(key);
			count++;
		}else{
			if(cache.containsKey(key)){
				cache.put(key, value);
				link.remove((Integer)key);
				link.add(key);
			}else{
				cache.remove(link.get(0));
				link.remove(0);
				cache.put(key, value);
				link.add(key);
			}
		}
	}
	public static void main(String[] args){
		LRUCache cache = new LRUCache(2);
		cache.set(2, 1);
		cache.set(1, 1);
		cache.set(5, 3);
		cache.set(4, 1);
		System.out.println(cache.get(2));
		cache.set(4, 1);
		System.out.println(cache.get(1));
		System.out.println(cache.get(2));
	}

}
