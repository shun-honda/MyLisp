package calc;

import java.util.LinkedList;

import parse.Cons_cell;

public class Calculator {
	public String Caluclation(Cons_cell formula, String operator, String x, Cons_cell head, LinkedList<String> var) {
		int y;
		Preparation operation = new Preparation();
		switch(operator){
		case "+":
			y = Integer.parseInt(x);
			y += Integer.parseInt(operation.eval(formula, operator, head, var));
			x = String.valueOf(y);
			break;

		case "-":
			y = Integer.parseInt(x);
			y -= Integer.parseInt(operation.eval(formula, operator, head, var));
			x = String.valueOf(y);
			break;

		case "*":
			y = Integer.parseInt(x);
			y *= Integer.parseInt(operation.eval(formula, operator, head, var));
			x = String.valueOf(y);
			break;

		case "/":
			y = Integer.parseInt(x);
			y /= Integer.parseInt(operation.eval(formula, operator, head, var));
			x = String.valueOf(y);
			break;

		case ">":
			y = Integer.parseInt(x);
			if(y > Integer.parseInt(operation.eval(formula, operator, head, var))){
				x = "T";
			}
			else{
				x = "Nill";
			}
			break;

		case "<":
			y = Integer.parseInt(x);
			if(y < Integer.parseInt(operation.eval(formula, operator, head, var))){
				x = "T";
			}
			else{
				x = "Nill";
			}
			break;

		case "=":
			y = Integer.parseInt(x);
			if(y == Integer.parseInt(operation.eval(formula, operator, head, var))){
				x = "T";
			}
			else{
				x = "Nill";
			}
			break;
		}
		return x;
	}
}
