package leetCode;

import java.util.HashMap;
import java.util.Map;

public class LRUCache1 {
	
	private int capacity;
	private Map<Integer, Integer> cache;
	private int[] arr;
	private int nElements;
	
	public LRUCache1(int capacity){
		this.capacity = capacity;
		nElements = 0;
		cache = new HashMap<Integer, Integer>(2*capacity);
		arr = new int[capacity];
	}
	public int get(int key){
		if(cache.get(key) != null && cache.get(key) > 0){
			arr[nElements] = key;
			return cache.get(key);
		}else{
			return -1;
		}
	}
	public boolean isExit(int key){
		return (cache.get(key) != null && cache.get(key) > 0);
	}
	public void set(int key, int value){
		if(nElements >= capacity){
			return;
		}
		if(!isExit(key)){
			nElements++;
		}
		cache.put(key, value);
	}

}
