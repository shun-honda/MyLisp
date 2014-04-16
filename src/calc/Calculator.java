package calc;

import java.util.LinkedList;

import parse.Conscell;

public class Calculator {
	public LinkedList Basic_Airthmetic_operations(Conscell formula, String operator, LinkedList x, Conscell head,
			LinkedList<String> var) {
		Preparation operation = new Preparation();
		switch (operator) {
		case "+":
			x.add((int) x.remove() + (int) operation.eval(formula, operator, head, var).remove());
			break;

		case "-":
			x.add((int) x.remove() - (int) operation.eval(formula, operator, head, var).get(0));
			break;

		case "*":
			x.add((int) x.remove() * (int) operation.eval(formula, operator, head, var).get(0));
			break;

		case "/":
			x.add((int) x.remove() / (int) operation.eval(formula, operator, head, var).get(0));
			break;
		}
		return x;
	}

	public String Relational_Operator(Conscell formula, String operator, LinkedList x, Conscell head,
			LinkedList<String> var) {
		String result = "";
		Preparation operation = new Preparation();
		switch (operator) {
		case ">":
			if ((int) x.get(0) > (int) operation.eval(formula, operator, head, var).get(0)) {
				result = "T";
			}
			else {
				result = "Nill";
			}
			break;

		case "<":
			if ((int) x.get(0) < (int) operation.eval(formula, operator, head, var).get(0)) {
				result = "T";
			}
			else {
				result = "Nill";
			}
			break;

		case "=":
			if ((int) x.get(0) == (int) operation.eval(formula, operator, head, var).get(0)) {
				result = "T";
			}
			else {
				result = "Nill";
			}
			break;
		}
		return result;
	}
}
