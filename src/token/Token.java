package token;

import java.util.LinkedList;

public class Token {
	String expr;
	int count;
	public void Lexical_Analysis(String expr){
		this.expr = expr;
		String num = "",string = "";
		LinkedList<String> list = new LinkedList<String>();
		String[] str = this.expr.split(""); 
		
		
		
		for(int i=0; i < this.expr.length()+1; i++){
			switch (str[i]) {
				
			case "(":
				list.add(str[i]);
				break;
				
			case ")":
				list.add(str[i]);
				break;
				
			case "+":
				list.add(str[i]);
				break;
				
			case "-":
				if(str[i+1].matches("[0-9]")){
					num += str[i];
				}
				else{
					list.add(str[i]);
				}
				break;
				
			case "*":
				list.add(str[i]);
				break;
				
			case "/":
				list.add(str[i]);
				break;
				
			case "<":
				list.add(str[i]);
				break;
				
			case ">":
				list.add(str[i]);
				break;
				
			case "=":
				list.add(str[i]);
				break;
				
			
				
			case " ":
				break;
				
			case "":
				break;
				
				
			default:
				if(str[i].matches("[a-z]")){
					string += str[i]; 
					if(str[i+1].matches("[a-z]")){
					}
					else{
						list.add(string);
						string = "";
					}
				}
				else{
					num += str[i];
					if(str[i+1].matches("[0-9]")){
					}
					else{
						list.add(num);
						num = "";
					}
				}
				break;
				
			}
		}
		
		//Parse.parse(list);
		
		for(int i=0;i < list.size() ;i++){
			System.out.println(list.get(i));
		}
	}
}

