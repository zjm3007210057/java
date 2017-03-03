package leetCode;

public class ValidNumber {
	
	public static boolean isNumber(String s){
		char[] arr = s.toCharArray();
		int length = arr.length;
		int index = 0;
		int count = 0;
		int te = 0;
		int mount = 0;
		int num = 0;
		int space = 0;
		int sp = 0;
		int dat = 0;
		int neg = 0;
		int pos = 0;
		int sym = 0;
		int p = 0;
		boolean flag = false;
		if(length<1){
			return false;
		}else if((arr[0]<48 || arr[0]>57) && arr[0]!=46 && arr[0]!=32 && arr[0]!=45 && arr[0]!=43){
			return false;
		}else if(length == 1){
			if(arr[0]>47 && arr[0]<58){
				return true;
			}else{
				return false;
			}
		}else if(arr[0]==48){
			for(int i=1; i<length; i++){
				if(arr[i]==32){
					space = i;
					num = i;
					while(i<length-1){
						if(arr[space+1]==32){
							space++;
						}
						i++;
					}
					if(space != length-1){
						return false;
					}else if(space == length-1 && (index+count+te)==num-1 && index<=1 && te<=1){
						return true;
					}else{
						return false;
					}
				}
				if(arr[i]==46){
					pos = i;
					index++;
				}else if(arr[i]>47 && arr[i]<58){
					count++;
				}else if(arr[i] == 101){
					if(index==1){
						if(pos<i){
							;
						}else{
							return false;
						}
					}
					if(i<length-1 && arr[i+1]>47 && arr[i+1]<58){
						te++;
					}else{
						return false;
					}
				}
			}
			if(index>1 || te>1){
				return false;
			}else if((count+index+te)==(length-1)){
				return true;
			}else{
				return false;
			}
		}
		for(int i=0; i<length; i++){
			count = 0;
			if(i<length && arr[i]==46){
				count++;
				mount = i;
				if(mount == length-1){
					if(num>0){
						return true;
					}else{
						return false;
					}
				}
				while(mount<length && count==1){
					if(mount<length-2 && arr[mount+2]==101){
						te++;
						if(arr[mount+1]>47 && arr[mount+1]<58){
							index++;
							flag = true;
						}else{
							return false;
						}
						if(mount<length-4 && (arr[mount+3]==45 || arr[mount+3]==43)){
							sym++;
							if(sym==1 && arr[mount+4]>47 && arr[mount+4]<58){
								;
							}else{
								return false;
							}
						}else if(mount<length-3 && arr[mount+3]>47 && arr[mount+3]<58){
							;
						}else{
							return false;
						}
					}else if(flag==false && mount<length-1 && arr[mount+1]==101){
						if(mount>0 && arr[mount-1]>47 && arr[mount-1]<58){
							te++;
							if(mount<length-2 && (arr[mount+2]==45 || arr[mount+2]==43)){
								sym++;
								if(mount<length-3 && sym==1 && arr[mount+3]>47 && arr[mount+3]<58){
									;
								}else{
									return false;
								}
							}else if(mount<length-2 && arr[mount+2]>47 && arr[mount+2]<58){
								;
							}else{
								return false;
							}
						}else{
							return false;
						}
					}else if(mount<length-1 && arr[mount+1]>47 && arr[mount+1]<58){
						index++;
					}else if(mount<length-1 && arr[mount+1]==32){
						p = mount;
						while(mount<length-1){
							if(arr[mount+1]==32){
								space++;
							}
							mount++;
						}
						if(space != length-p-1){
							return false;
						}else if(num>0 || index>0 &&index+sym+te==(length-1-i-space)){
							return true;
						}else{
							return false;
						}
					}
					mount++;
				}
				if(count>1 || te>1){
					return false;
				}else if((index+te+sym)==(length-i-1)){
					return true;
				}else{
					return false;
				}
			}else if(i<length && arr[i]==101){
				if(i==length-1){
					return false;
				}
				if(arr[i-1]<48 || arr[i-1]>57){
					return false;
				}
				mount = i;
				count++;
				while(mount<length && count==1){
					if(mount<length-1 && arr[mount+1]>47 && arr[mount+1]<58){
						index++;
					}else if(mount<length-2 && (arr[mount+1]==45 || arr[mount+1]==43)){
						sym++;
						if(sym==1 && mount<length-2 && arr[mount+2]>47 && arr[mount+2]<58){
							;
						}else{
							return false;
						}
					}else if(mount<length-1 && arr[mount+1]==32){
						p = mount;
						while(mount<length-1){
							if(arr[mount+1]==32){
								space++;
							}
							mount++;
						}
						if(space != length-1-p){
							return false;
						}else if(num>0 && index>0 &&index+sym==(length-1-i-space)){
							return true;
						}else{
							return false;
						}
					}else{
						if(mount==length-1){
							;
						}
						else{
							return false;
						}
					}
					mount++;
				}
				if(count>1){
					return false;
				}else if(index+sym==(length-1-i)){
					return true;
				}else{
					return false;
				}
			}else if(arr[i]>47 && arr[i]<58){
				num++;
				continue;
			}else if(arr[i]==45){
				neg++;
				if(neg>1){
					return false;
				}else{
					for(int j=0; j<i; j++){
						if(arr[j]!=32){
							return false;
						}
					}
				}
				continue;
			}else if(arr[i]==43){
				pos++;
				if(pos>1){
					return false;
				}else{
					for(int j=0; j<i; j++){
						if(arr[j]!=32){
							return false;
						}
					}
				}
				continue;
			}else if(arr[i]==32){
				sp = i+1;
				if(sp==1){
					while(sp<length){
						if(arr[sp]==32){
							dat++;
						}else{
							break;
						}
						sp++;
					}
					if(dat == length-1){
						return false;
					}
					i = sp-1;
				}else{
					dat = 0;
					while(sp<length){
						if(arr[sp]==32){
							dat++;
						}else{
							break;
						}
						sp++;
					}
					if(dat != length-1-i){
						return false;
					}else{
						return true;
					}
				}
				continue;
			}else{
				return false;
			}
		}
		if(num>0 && (num+sp+neg+pos)==length){
			return true;
		}
		return false;
	}
	
	public static void main(String[] args){
//		char a = 'l';
//		char b = ' ';
//		char c = '+';
//		String s = "1";
//		char[] arr = s.toCharArray();
//		System.out.println(a-1);
//		System.out.println(b-1);
//		System.out.println(c-1);
//		System.out.println(isNumber("1 ."));
//		System.out.println(isNumber("12  ."));
//		System.out.println(isNumber("  ."));
//		System.out.println(isNumber("  .12"));
//		System.out.println(isNumber("."));
//		System.out.println(isNumber("01"));
//		System.out.println(isNumber(" 1"));
//		System.out.println(isNumber(".0"));
//		System.out.println(isNumber("3."));
//		System.out.println(isNumber("2e0"));
//		System.out.println(isNumber(" 2.e+6"));
//		System.out.println(isNumber("46.e3"));
//		System.out.println(isNumber(".2e12"));
//		System.out.println(isNumber(".e3"));
//		System.out.println(isNumber("1e312  "));
//		System.out.println(isNumber("  e312  "));
//		System.out.println(isNumber("  .e312  "));
//		System.out.println(isNumber(" 2e0  "));
//		System.out.println(isNumber("063.e57002"));
		System.out.println(isNumber("05.e1 "));
//		System.out.println(isNumber("  234  "));
//		System.out.println(isNumber(" 342 1 "));
//		System.out.println(isNumber("01 2  "));
//		System.out.println(isNumber("0143   "));
//		System.out.println(isNumber("-1. "));
//		System.out.println(isNumber("+1. "));
	}

}
