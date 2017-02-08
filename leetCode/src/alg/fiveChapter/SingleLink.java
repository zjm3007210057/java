package alg.fiveChapter;

import java.util.HashSet;
import java.util.Set;

public class SingleLink {
	
	class Element{
		public Object value = null;
		public Element nextNode = null;
	}
	class Value{
		public String code;
		public String name;
		
		public Value(){}
		public Value(String name, String code){
			this.name = name;
			this.code = code;
		}
		public String toString(){
			return code + "-" + name;
		}
	}
	private Element header = null;
	
	public void add(Object node){
		if(header == null){
			header = new Element();
			header.value = null;
			header.nextNode = null;
		}
		Element element = new Element();
		element.value = node;
		element.nextNode = header.nextNode;
		header.nextNode = element;
	}
	public void clear(){
		header = null;
	}
	public boolean contains(Object o){
		if(header == null){
			return false;
		}
		Element el = header.nextNode;
		while(el != null){
			if(el.value == o){
				return true;
			}
			el = el.nextNode;
		}
		return false;
	}
	public int size(){
		if(header == null)
			return 0;
		Element temp = header.nextNode;
		int i = 0;
		while(temp != null){
			i++;
			temp = temp.nextNode;
		}
		return i;
	}
	public Element getElement(int index){
		if(header == null)
			return null;
		int size = this.size();
		if(index > (size - 1) || index < 0)
			return null;
		Element temp = header.nextNode;
		int i =0;
		while(temp != null){
			if(index == i){
				return temp;
			}
			i++;
			temp = temp.nextNode;
		}
		return null;
	}
	public Object get(int index){
		if(header == null)
			return null;
		int size = this.size();
		if(index > (size - 1) || index < 0)
			return null;
		Element temp = header.nextNode;
		int i =0;
		while(temp != null){
			if(index == i){
				return temp.value;
			}
			i++;
			temp = temp.nextNode;
		}
		return null;
	}
	public boolean isEmpty(){
		if(header == null)
			return true;
		return false;
	}
	public Element remove(Object o){
		if(header == null)
			return null;
		Element elh = header;
		Element el = header.nextNode;
		while(el != null){
			if(el.value == o){
				elh.nextNode = el.nextNode;
				break;
			}
			elh = el;
			el = el.nextNode;
		}
		return elh;
	}
	public boolean checkLoop(){
		if(header == null)
			return false;
		int size = this.size();
		if(size == 0)
			return false;
		Set<Element> set = new HashSet<Element>();
		for(int i=0; i<size; i++){
			Element el = getElement(i);
			if(!set.contains(el)){
				set.add(el);
			}
			if(set.contains(el.nextNode)){
				return true;
			}
		}
		return false;
	}
	
}
