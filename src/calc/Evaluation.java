package calc;

import java.util.HashMap;
import java.util.LinkedList;

import parse.Conscell;
import enums.Flag;

public class Evaluation {
	LinkedList var = new LinkedList();
	LinkedList saved_var = new LinkedList();
	LinkedList<Integer> result = new LinkedList<Integer>();
	LinkedList<String> name = new LinkedList<String>();
	Flag flag;
	HashMap<String, Conscell> defun_map = new HashMap<String, Conscell>();
	HashMap<String, Integer> var_map = new HashMap<String, Integer>();
	String function_name;
	int size;

	/*評価関数
	ParserでConscellにした数式を評価（計算する場合違うメソッドを呼び出す）してValue_List型のリスト構造に結果を格納して返す。*/
	public Value_List eval(Conscell expr, String operator, Conscell head, LinkedList var, Value_List x) {
		if (expr.getFlag() == Flag.number) {
			x = new Value_List(expr.getValue());
			if (expr.cdr.getId() != "Nil") {
				if (operator.equals(">") || operator.equals("<") || operator.equals("=")) {
					x = new Value_List(Relational_Operator(expr.cdr, operator, x, head, var));
					x.setType(Flag.string);
				}
				else {
					x = new Value_List(Basic_Airthmetic_operations(expr.cdr, operator, x, head, var).getValue());
					x.setType(Flag.number);
				}
			}
			else {
				x.setType(Flag.number);
			}
		}
		else if (expr.getId().equals("car")) {
			x = eval(expr.car, operator, head, var, x);
			Flag flag = x.getType();
			int value = x.getValue();
			x = new Value_List(value);
			x.setType(flag);
			if (expr.cdr.getId() != "Nil") {
				if (operator.equals(">") || operator.equals("<") || operator.equals("=")) {
					x = new Value_List(Relational_Operator(expr.cdr, operator, x, head, var));
					x.setType(Flag.string);
				}
				else {
					x = new Value_List(Basic_Airthmetic_operations(expr.cdr, operator, x, head, var).getValue());
					x.setType(Flag.number);
				}

			}
		}
		else if (expr.getId().equals("setq")) {
			this.flag = Flag.setq;
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
				if (operator.equals(">") || operator.equals("<") || operator.equals("=") || operator.equals("<=") || operator.equals(">=")) {
					x = new Value_List(Relational_Operator(expr.cdr, operator, x, head, var));
					x.setType(Flag.string);
				}
				else {
					x = new Value_List(Basic_Airthmetic_operations(expr.cdr, operator, x, head, var).getValue());
					x.setType(Flag.number);
				}
			}
			else {
				x.setType(Flag.number);
			}
		}
		else if (expr.getId().equals("if")) {
			x = eval(expr.cdr.car, operator, head, var, x);
			if (x.getId().equals("T")) {
				if (expr.cdr.cdr.getFlag() == Flag.car) {
					x = eval(expr.cdr.cdr.car, operator, head, var, x);
				}
				else {
					x = eval(expr.cdr.cdr, operator, head, var, x);
				}
			}
			else {
				if (expr.cdr.cdr.cdr.getFlag() == Flag.car) {
					x = eval(expr.cdr.cdr.cdr.car, operator, head, var, x);
				}
				else {
					x = eval(expr.cdr.cdr.cdr, operator, head, var, x);
				}
			}
		}
		else if (expr.getId().equals("defun")) {
			this.name.add(expr.cdr.getId());
			make_local_variable(expr.cdr.cdr.car);
			this.size = (this.name.size() - 1) * 2;
			this.defun_map.put(expr.cdr.getId(), expr.cdr.cdr.cdr);
			this.flag = Flag.defun;
		}
		else if (expr.getId().equals(this.name.get(0))) {
			set_local_q(expr.cdr, this.name, 1, operator, head, var, x, 0);
			x = eval(this.defun_map.get(expr.getId()), "", head, var, x);
			if (saved_var.size() != 0) {
				reposit_local_q();
			}
		}
		else {
			x = eval(expr.cdr, expr.getId(), head, var, x);
		}

		if (expr == head) {
			if (expr.car != null) {
				head = expr.car;
				if (this.flag == Flag.setq || this.flag == Flag.defun) {
					this.flag = null;
					x = eval(expr.car, "", head, this.var, x);
				}
				else {
					x.nextElement = eval(expr.car, "", head, this.var, x);
				}
			}
		}

		return x;
	}

	/*
	 四則演算を行い結果を返すメソッド
	 */
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

	/*比較演算をして結果を返すメソッド*/
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

		case "<=":
			if (x.getValue() <= eval(formula, operator, head, var, x).getValue()) {
				result = "T";
			}
			else {
				result = "Nill";
			}
			break;

		case ">=":
			if (x.getValue() >= eval(formula, operator, head, var, x).getValue()) {
				result = "T";
			}
			else {
				result = "Nill";
			}
			break;
		}
		return result;
	}

	public void make_local_variable(Conscell expr) {
		int count = 0;
		this.name.add(expr.getId());
		if (expr.cdr.getId() != "Nil") {
			make_local_variable(expr.cdr);
		}
	}

	public void set_local_q(Conscell expr, LinkedList<String> var_name, int count, String operator, Conscell head, LinkedList var, Value_List x, int rcount) {
		if (expr.getFlag() == Flag.car) {
			if (expr.car.getFlag().equals(Flag.operator)) {
				expr.setValue(eval(expr.car, operator, head, var, x).getValue());
				this.var.add(var_name.get(count));
				this.var.add(expr.getValue());
			}
			else {
				this.result.add(eval(expr.car, operator, head, var, x).getValue());
				rcount++;
			}
		}
		else if (this.var.indexOf(expr.getId()) != -1) {
			this.var.add(var_name.get(count));
			this.var.add(this.var.get(this.var.indexOf(expr.getId()) + 1));
		}
		else {
			this.var.add(var_name.get(count));
			this.var.add(expr.getValue());
		}
		count++;
		if (expr.cdr.getId() != "Nil") {
			set_local_q(expr.cdr, var_name, count, operator, head, var, x, rcount);
		}
		else {
			if (this.var.size() == this.size) {
				int i = 0;
				while (i != this.size) {
					this.saved_var.add(this.var.get(i));
					i++;
				}
			}
			else if (this.var.size() > this.size) {
				int i = this.size;
				while (i != 0) {
					this.saved_var.add(this.var.remove());
					i--;
				}
			}
			if (rcount == (this.size / 2)) {
				int i = this.size - 1;
				while (i > 0) {
					this.var.remove(i);
					this.var.add(i, this.result.removeLast());
					i = i - 2;
				}
			}
		}
	}

	public void reposit_local_q() {
		int count = this.var.size() / 2;
		this.var.clear();
		while (count != 0) {
			this.var.addFirst(this.saved_var.removeLast());
			this.var.addFirst(this.saved_var.removeLast());
			count--;
		}
	}
}
