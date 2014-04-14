package calc;

import parse.Cons_cell;

public class Preparation {
	public String disposition(Cons_cell formula, String operator) {
		int x = 0;
		Calculator calc = new Calculator();
		if (formula.getValue().matches("^[0-9]")) {
			x = Integer.parseInt(formula.getValue());
			if(formula.cdr.getValue() != "Nil") {
				x = calc.Caluclation(formula.cdr,operator,x);
			}
		}
		else if (formula.getValue().equals("car")){
			String value = disposition(formula.car, operator);
			x =Integer.parseInt(value);
			if(formula.cdr.getValue() != "Nil") {
				x = calc.Caluclation(formula.cdr,operator,x);
			}
		}
		else {
			x = Integer.parseInt(disposition(formula.cdr, formula.getValue()));
		}

		return String.valueOf(x);
	}
}
