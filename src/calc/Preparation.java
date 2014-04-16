package calc;

import java.util.LinkedList;

import parse.Conscell;

public class Preparation {
	LinkedList var = new LinkedList();

	public LinkedList eval(Conscell expr, String operator, Conscell head, LinkedList var) {
		LinkedList x = new LinkedList();
		String y = "";
		Calculator calc = new Calculator();
		if (0 < expr.getValue()) {
			x.add(expr.getValue());
			if (expr.cdr.getId() != "Nil") {
				if (operator.equals(">") || operator.equals("<") || operator.equals("=")) {
					x.add(calc.Relational_Operator(expr.cdr, operator, x, head, var));
				}
				else {
					x.add(calc.Basic_Airthmetic_operations(expr.cdr, operator, x, head, var));
				}
			}
		}
		else if (expr.getId().equals("car")) {
			int value = (int) eval(expr.car, operator, head, var).get(0);

			x.add(value);
			if (expr.cdr.getId() != "Nil") {
				x.add(calc.Basic_Airthmetic_operations(expr.cdr, operator, x, head, var));
			}
		}
		else if (expr.getId().equals("setq")) {
			if (expr.cdr.getId().equals("car")) {
				this.var.add(expr.cdr.car.getId());
				this.var.add(expr.cdr.cdr.car.getValue());
			}
			else {
				this.var.add(expr.cdr.getId());
				this.var.add(expr.cdr.cdr.getValue());
			}
		}
		else if (var.indexOf(expr.getId()) != -1) {
			x.add((var.get(var.indexOf(expr.getId()) + 1)));
			if (expr.cdr.getId() != "Nil") {
				if (operator.equals(">") || operator.equals("<") || operator.equals("=")) {
					x.add(calc.Relational_Operator(expr.cdr, operator, x, head, var));
				}
				else {
					x.add(calc.Basic_Airthmetic_operations(expr.cdr, operator, x, head, var));
				}
			}
		}
		else if (expr.getId().equals("if")) {
			x.add(eval(expr.cdr.car, operator, head, var));
			if (x.remove().equals("T")) {
				x.add(eval(expr.cdr.cdr.car, operator, head, var));
			}
			else {
				x.add(eval(expr.cdr.cdr.cdr.car, operator, head, var));
			}
		}

		else {
			x.add(eval(expr.cdr, expr.getId(), head, var));
		}

		if (expr == head) {
			if (expr.car != null) {
				head = expr.car;
				x.add(eval(expr.car, "", head, this.var).remove());
			}
		}

		return x;
	}
}
