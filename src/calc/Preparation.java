package calc;

import java.util.LinkedList;

import parse.Cons_cell;

public class Preparation {
	LinkedList<String> var = new LinkedList<String>();
	public String eval(Cons_cell formula, String operator, Cons_cell head, LinkedList<String> var) {
		String x = "", y = "";
		Calculator calc = new Calculator();
		if (formula.getValue().matches("^[0-9]")) {
			x = formula.getValue();
			if(formula.cdr.getValue() != "Nil") {
				x = calc.Caluclation(formula.cdr,operator, x, head, var);
			}
		}
		else if (formula.getValue().equals("car")){
			String value = eval(formula.car, operator, head, var);
			x =value;
			if(formula.cdr.getValue() != "Nil") {
				x = calc.Caluclation(formula.cdr,operator, x, head, var);
			}
		}
		else if(formula.getValue().equals("setq")){
			if (formula.cdr.getValue().equals("car")){
				this.var.add(formula.cdr.car.getValue());
				this.var.add(formula.cdr.cdr.car.getValue());
			}
			else{
				this.var.add(formula.cdr.getValue());
				this.var.add(formula.cdr.cdr.getValue());
			}
		}
		else if(var.indexOf(formula.getValue()) != -1){
			x = var.get(var.indexOf(formula.getValue()) + 1);
			if(formula.cdr.getValue() != "Nil") {
				x = calc.Caluclation(formula.cdr,operator, x, head, var);
			}
		}
		else if(formula.getValue().equals("if")){
			x = eval(formula.cdr.car, operator, head, var);
			if(x.equals("T")){
				x = eval(formula.cdr.cdr.car, operator, head, var);
			}
			else{
				x = eval(formula.cdr.cdr.cdr.car, operator, head, var);
			}
		}
		else {
			x = eval(formula.cdr, formula.getValue(), head, var);
		}

		if(formula == head){
			if(formula.car != null){
				head = formula.car;
				y = eval(formula.car, "", head, this.var);
				x = x + " " + y;
			}
		}

		return x;
	}
}
