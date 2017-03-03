package leetCode;

import java.util.ArrayList;


public class LongestPalindromicSubstring {
	
	public static boolean equal(String st){
		int count = 0;
		for(int i=0; i<st.length()-1; i++){
			if(st.charAt(i)==st.charAt(i+1)){
				++count;
			}
		}
		if(count == st.length()-1){
			return true;
		}
		return false;
	}
	
	public static String longestPalindromeOutTime(String s){
		int first = 0;//回文串起始位置
		int m = 0;
		int n = 0;
		int end = 0;//回文串结尾位置
		int length = s.length();
		int index = 0;//字符串的指针位置
		int count = 0;//回文串的长
		int temp = 0;//count的代替量，作为中间变量
		String str = new String();
		if(length<=1){
			return s;
		}
		if(length == 2){
			if(s.charAt(0) == s.charAt(1)){
				return s;
			}else{
				return null;
			}
		}
		for(int i=1; i<length; i++){
			if(s.charAt(m)==s.charAt(i)){
				count = 2;
				n = i;
				index = m;
				while(s.charAt(++index)==s.charAt(--n) && index<n){
					count += 2;
				}
				if(count == i-m || count == i - m + 1){
					if(temp <= count){
						temp = count;
						first = m;
						end = i;
					}
				}
				if(i != length-1){
					continue;
				}else{
					++m;
					i = m+1;
				}
			}else if(i != length-1){
				continue;
			}else if(i == length -1){
				++m;
				i = m+1;
			}
		}
		if(first != end){
			str = s.substring(first, end+1);
		}else{
			return null;
		}
		return str;
	}
	
	public static String longestPalindromeOut(String s){
		String str;
		int index = 0;
		int m = 0;
		int n = 0;
		int first = 0;
		int end = 0;
		int length = s.length();
		int temp = 0;
		int count = 0;
		int num = 1;
		int fir = 0;
		int tai = 0;
		
		int[] arr = new int[length];
		
		if(length<=1){
			return s;
		}
		if(length == 2){
			if(s.charAt(0) == s.charAt(1)){
				return s;
			}else{
				return null;
			}
		}
		for(int i=1; i<length; i++){
			arr[0] = index;
			if(s.charAt(index) == s.charAt(i)){
				arr[num] = i;
				num += 1;
			}
			if(i == length -1){
				if(num>2){
					count = 2;
					for(int j=0; j<num; j++){
						fir = num -1;
						tai = j;
						if(tai<(fir)/2 && (arr[tai+1]-arr[tai]) == (arr[fir-tai] - arr[fir-tai-1])){
							m = arr[tai];
							n = arr[fir-tai];
							while(s.charAt(++m)==s.charAt(--n) && m<n){
								count += 2;
							}
							if(count == arr[fir-tai]-arr[tai] || count == arr[fir-tai] - arr[tai] + 1){
								if(temp <= count){
									temp = count;
									first = arr[tai];
									end = arr[fir-tai];
								}
							}
						}else if(tai<(fir-1)/2 &&(arr[tai+1]-arr[tai]) == (arr[fir-tai-1] - arr[fir-tai-2])){
							m = arr[tai];
							n = arr[fir-tai-1];
							while(s.charAt(++m)==s.charAt(--n) && m<n){
								count += 2;
							}
							if(count == arr[fir-tai-1]-arr[tai] || count == arr[fir-tai-1] - arr[tai] + 1){
								if(temp <= count){
									temp = count;
									first = arr[tai];
									end = arr[fir-tai-1];
								}
							}
						}else{
							if(tai<fir){
								++tai;
							}
						}
					}
				}else{
					count = 2;
					n = i;
					m = index;
					while(s.charAt(++m)==s.charAt(--n) && m<n){
						count += 2;
					}
					if(count == i-index || count == i - index + 1){
						if(temp <= count){
							temp = count;
							first = index;
							end = i;
						}
					}
				}
				index++;
				if(arr[1] != 0 && arr[1] < length/2){
					if(count>arr[1]){
						i = 2*arr[1] - 1;
					}else{
						i = index +1;
						length = arr[1];
					}
				}else if(arr[1] >= length/2){
					i = index +1;
					length = arr[1];
				}else{
					i = index + 1;
				}
				num =1;
			}
			
		}
		if(first != end){
			str = s.substring(first, end+1);
		}else{
			return null;
		}
		return str;
	}
	
	public static String longestPalindrome(String s){
		int index = 0;
		int num = 0;
		int first = 0;
		int end = 0;
		int count = 0;
		int temp = 0;
		int m = 0;
		int n = 0;
		int length = s.length();
		String str = "null";
		
		if(s=="" || s==null){
			return null;
		}
		if(length == 1){
			return s;
		}else if(length == 2){
			if(s.charAt(0) == s.charAt(1)){
				return s;
			}else{
				return null;
			}
		}else if(equal(s)){
			return s;
		}
		else{
			OK:
			for(int i=length/2; i>0; i--){
				count = 0;
				m = i;
				n = i;
				while(n<length-1 && m>0 && s.charAt(n+1)==s.charAt(m-1)){
					count += 2;
					index = m-1;
					num = n+1;
					++n;
					--m;
					if(count>=temp){
						first = index;
						end = num;
						temp = count +1;
					}
					if(temp>=2*i){
						break OK;
					}
				}
				
				index = 0;
				m = i;
				n = i;
				while(n<length && m>0 && s.charAt(n)==s.charAt(m-1)){
					index += 2;
					count = m-1;
					num = n;
					++n;
					--m;
					if(index>=temp){
						first = count;
						end = num;
						temp = index;
					}
					if(temp>=2*i){
						break OK;
					}
				}
				
				num = 0;
				m = i;
				n = i;
				while(n<length-1 && m>=0 && s.charAt(n+1)==s.charAt(m)){
					num += 2;
					index = m;
					count = n+1;
					++n;
					--m;
					if(num>=temp){
						first = index;
						end = count;
						temp = num;
					}
					if(temp>=2*i){
						break OK;
					}
				}
				
			}
			if(temp<length-1){
				flag:
				for(int i=length/2; i<length-temp/2; i++){
					count = 0;
					m = i;
					n = i;
					while(n<length-1 && m>0 && s.charAt(n+1)==s.charAt(m-1)){
						count += 2;
						index = m-1;
						num = n+1;
						++n;
						--m;
						if(count>=temp){
							first = index;
							end = num;
							temp = count +1;
						}
						if(temp>=2*i){
							break flag;
						}
					}
					
					index = 0;
					m = i;
					n = i;
					while(n<length && m>0 && s.charAt(n)==s.charAt(m-1)){
						index += 2;
						count = m-1;
						num = n;
						++n;
						--m;
						if(index>=temp){
							first = count;
							end = num;
							temp = index;
						}
						if(temp>=2*i){
							break flag;
						}
					}
					
					num = 0;
					m = i;
					n = i;
					while(n<length-1 && m>=0 && s.charAt(n+1)==s.charAt(m)){
						num += 2;
						index = m;
						count = n+1;
						++n;
						--m;
						if(num>=temp){
							first = index;
							end = count;
							temp = num;
						}
						if(temp>=2*i){
							break flag;
						}
					}
				}
			}
		}
		if(first != end){
			str = s.substring(first, end+1);
		}else{
			return null;
		}
		return str;
	} 
	public static void main(String[] args){
//		int[] a = new int[4];
//		System.out.println(a[1]);
		System.out.println(longestPalindromeOutTime("aba"));
		System.out.println(longestPalindromeOutTime("a"));
		System.out.println(longestPalindromeOutTime("ab"));
		System.out.println(longestPalindromeOutTime("aaaa"));
		System.out.println(longestPalindromeOutTime("dahfjhdfiuewcnidsahfhasdincfacjdshfj"));
		System.out.println(longestPalindrome("aba"));
		System.out.println(longestPalindrome("a"));
		System.out.println(longestPalindrome("ab"));
		System.out.println(longestPalindrome("abb"));
		System.out.println(longestPalindrome("aab"));
		System.out.println(longestPalindrome("abacc"));
		System.out.println(longestPalindrome("abacdc"));
		System.out.println(longestPalindrome("tattarrattat"));
		System.out.println(longestPalindrome("aaaa"));
		System.out.println(longestPalindrome("dahfjhdfiuewcnidsahfhasdincfacjdshfj"));
	}

}
