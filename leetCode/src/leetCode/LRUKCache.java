package leetCode;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

class NumDate{
	int num;
	long date;
	
}

public class LRUKCache {
	/**
	 * 定义容量
	 */
	private int capacity;
	private NumDate numDate;
	private HashMap<Integer,NumDate> cache = new HashMap<Integer,NumDate>(capacity);
	private LinkedList<Integer> link = new LinkedList<Integer>();
	
	public LRUKCache(int capacity) {
        this.capacity = capacity;
    }
	
    public int get(int key) {
    	if(link.contains(key)){
    		return key;
    	}
    	if(cache.containsKey(key)){
    		return key;
    	}
		return -1;
    }
    
    public void set(int key, int value) {
    	numDate = new NumDate();
    	int count = 0;
    	int index = 0;
    	long date;
    	Date time = new Date();
    	if(link.contains(value)){
    		link.remove(value);
    		link.addLast(value);
    	}else if(cache.containsKey(value)){
    		numDate = cache.get(value);
    		numDate.num++;
    		date = time.getTime();
    		numDate.date = date;
    		if(numDate.num<key){
    			cache.put(value, numDate);
    		}else{
    			count--;
    			cache.remove(value);
    			link.addLast(value);
    			index++;
    			if(index==capacity){
    				link.removeFirst();
    				index--;
    			}
    		}
    	}else{
    		numDate.num = 1;
    		date = time.getTime();
    		numDate.date = date;
    		if(count<capacity){
    			cache.put(value, numDate);
    			count++;
    		}else{
    			for(Map.Entry<Integer, NumDate> entry: cache.entrySet()){
    				if(date>entry.getValue().date){
    					date = entry.getValue().date;
    					index = entry.getKey();
    				}
    			}
    			cache.remove(index);
    			cache.put(value, numDate);
    		}
    	}
    }
	
    public static void main(String[] args){
    	LRUKCache cache = new LRUKCache(10);
    	cache.set(3, 5);
    	cache.get(3);
    }
}
