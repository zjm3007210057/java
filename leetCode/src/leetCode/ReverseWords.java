package leetCode;

public class ReverseWords {
	
	public static String reverseWord(String s) {
        if(s=="" || s==null){
			return s;
		}
		String[] str = s.split(" ");
		if(str.length==0){
			return "";
		}
		int length = str.length;
		int count = 0;
		String result = "";
		for(int i=length-1; i>=0; i--){
		    if(!str[i].equals("")){
		        count++;
		    }
		}
		for(int j=length-1; j>0; j--){
		    if(!str[j].equals("")){
				if(count>1){
					result += str[j] + " ";
					count--;
				}else{
				    result += str[j];
				}
			}
		}
		result += str[0];
		return result;
    }
	
	public static String reverseWords(String s){
		if(s=="" || s==null){
			return s;
		}
		String[] str = s.split(" ");
		if(str.length==0){
			return "";
		}
		int length = str.length;
		int index = 0;
		int count = 0;
		String result = "";
		for(int j=length-1; j>0; j--){
			if(!str[j].equals("")){
				count = 0;
				index = j;
				while(index>0){
					index--;
					if(!str[index].equals("")){
						count++;
					}
				}
				if(count>0){
					result += str[j] + " ";
				}else{
				    result += str[j];
				}
			}
		}
		result += str[0];
		return result;
	}
	
	public static void main(String[] args){
		String s = " 1";
		String[] str = s.split(" ");
		System.out.println("str[0]="+str[0]+','+str[1]);
		System.out.println(str[0].equals(""));
		System.out.println(reverseWords( "the sky is blue"));
	}

}
