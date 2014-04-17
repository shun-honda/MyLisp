package calc;

import java.util.LinkedList;

import parse.Conscell;
import enums.Type;

public class Evaluation {
	LinkedList var = new LinkedList();

	/*評価関数
	 * ParserでConscellにした数式を評価（計算する場合違うメソッドを呼び出す）してValue_List型のリスト構造に結果を格納して返す。
	 * */
	public Value_List eval(Conscell expr, String operator, Conscell head, LinkedList var, Value_List x) {
		;
		if (0 < expr.getValue()) {
			x = new Value_List(expr.getValue());
			if (expr.cdr.getId() != "Nil") {
				if (operator.equals(">") || operator.equals("<") || operator.equals("=")) {
					x = new Value_List(Relational_Operator(expr.cdr, operator, x, head, var));
				}
				else {
					x = new Value_List(Basic_Airthmetic_operations(expr.cdr, operator, x, head, var)
							.getValue());
				}
			}
		}
		else if (expr.getId().equals("car")) {
			int value = eval(expr.car, operator, head, var, x).getValue();
			x = new Value_List(value);
			if (expr.cdr.getId() != "Nil") {
				x = new Value_List(Basic_Airthmetic_operations(expr.cdr, operator, x, head, var).getValue());
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
			x = new Value_List((int) var.get(var.indexOf(expr.getId()) + 1));
			if (expr.cdr.getId() != "Nil") {
				if (operator.equals(">") || operator.equals("<") || operator.equals("=")) {
					x = new Value_List(Relational_Operator(expr.cdr, operator, x, head, var));
					x.setType(Type.string);
				}
				else {
					x = new Value_List(Basic_Airthmetic_operations(expr.cdr, operator, x, head, var)
							.getValue());
					x.setType(Type.number);
				}
			}
		}
		else if (expr.getId().equals("if")) {
			x = eval(expr.cdr.car, operator, head, var, x);
			if (x.getId().equals("T")) {
				x = eval(expr.cdr.cdr.car, operator, head, var, x);
			}
			else {
				x = eval(expr.cdr.cdr.cdr.car, operator, head, var, x);
			}
		}

		else {
			x = eval(expr.cdr, expr.getId(), head, var, x);
		}

		if (expr == head) {
			if (expr.car != null) {
				head = expr.car;
				if (x.getId().equals("")) {
					x = eval(expr.car, "", head, this.var, x);
				}
				else {
					x.nextElement = eval(expr.car, "", head, this.var, x);
				}
			}
		}

		return x;
	}

	public Value_List Basic_Airthmetic_operations(Conscell formula, String operator, Value_List x, Conscell head, LinkedList<String> var) {
		switch (operator) {
		case "+":
			x = new Value_List(x.getValue() + eval(formula, operator, head, var, x).getValue());
			break;

		case "-":
			x = new Value_List(x.getValue() - eval(formula, operator, head, var, x).getValue());
			break;

		case "*":
			x = new Value_List(x.getValue() * eval(formula, operator, head, var, x).getValue());
			break;

		case "/":
			x = new Value_List(x.getValue() / eval(formula, operator, head, var, x).getValue());
			break;
		}
		return x;
	}

	public String Relational_Operator(Conscell formula, String operator, Value_List x, Conscell head,
			LinkedList<String> var) {
		String result = "";
		switch (operator) {
		case ">":
			if (x.getValue() > eval(formula, operator, head, var, x).getValue()) {
				result = "T";
			}
			else {
				result = "Nill";
			}
			break;

		case "<":
			if (x.getValue() < eval(formula, operator, head, var, x).getValue()) {
				result = "T";
			}
			else {
				result = "Nill";
			}
			break;

		case "=":
			if (x.getValue() == eval(formula, operator, head, var, x).getValue()) {
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
