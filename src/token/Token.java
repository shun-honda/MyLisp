package token;

import java.util.LinkedList;

public class Token {
	String expr;
	int count;
	public void make_Token(String expr){
		this.expr = expr;
		String[] str = this.expr.split(" ");
		this.count = str.length;
		LinkedList<String> list = new LinkedList<String>();
		list = divide_parentheses(str);
		for(int i=0;i < list.size() ;i++){
			System.out.println(list.get(i));
		}
	}
	
	public LinkedList<String> divide_parentheses(String[] str){
		int num=0;
		LinkedList<String> list = new LinkedList<String>();
		for(int i=0; i< this.count; i++){
			String result_str = str[i];
			for(int j=0; j < str[i].length();j++){
				char c = str[i].charAt(j);
				if(c == '('){
					result_str = result_str.substring(1); 
					list.add("(");
				}
				else if(c == ')'){
					result_str = result_str.substring(result_str.length()-2,result_str.length()-1);
					num++;
				}
			}
			list.add(result_str);
			if(num>0){
				for(int k=0;k<num;k++){
					list.add(")");
				}
			}
		}
		return list;
	}
}

