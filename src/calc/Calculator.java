package calc;

import parse.Cons_cell;

public class Calculator {
	public int Caluclation(Cons_cell formula,String operator,int x) {
		Preparation operation = new Preparation();
		switch(operator){
		case "+":
			x += Integer.parseInt(operation.disposition(formula, operator));
			break;

		case "-":
			x -= Integer.parseInt(operation.disposition(formula, operator));
			break;

		case "*":
			x *= Integer.parseInt(operation.disposition(formula, operator));
			break;

		case "/":
			x /= Integer.parseInt(operation.disposition(formula, operator));
			break;
		}

		return x;
	}
}
