package vm;

import java.util.LinkedList;

import parse.Conscell;
import enums.Command;
import enums.Flag;

public class Compiler {
	LinkedList<String> func_name = new LinkedList<String>();
	LinkedList<String> arg = new LinkedList<String>();
	LinkedList<Conscell> head = new LinkedList<Conscell>();
	Code[] command = new Code[1000];
	int pc = 0, label_n = 0, ec = 0, opc;
	int[] entry = new int[50];
	int[] label = new int[50];

	public void compiler(Conscell expr, int fc, Conscell head) {
		try {
			if (expr.getFlag() == Flag.number) {
				this.command[this.pc] = new Code(Command.PUSH);
				this.pc++;
				this.command[this.pc] = new Code(expr.getValue());
				this.pc++;
			}
			else if (this.arg.indexOf(expr.getId()) != -1) {
				this.command[this.pc] = new Code(Command.LOADA);
				this.pc++;
				this.command[this.pc] = new Code(this.arg.indexOf(expr.getId()));
				this.pc++;
			}
			else if (expr.getId().equals("if")) {
				compiler(expr.cdr.car, fc, head);
				this.command[this.pc] = new Code(Command.BEQ0);
				this.pc++;
				this.command[this.pc] = new Code(0);
				this.pc++;
				compiler(expr.cdr.cdr.car, fc, head);
				this.command[this.pc] = new Code(Command.RET);
				this.pc++;
				this.command[this.pc] = new Code(Command.LABEL);
				this.pc++;
				this.command[this.pc] = new Code(this.label_n);
				this.label[label_n] = this.pc;
				this.pc++;
				this.label_n++;
				compiler(expr.cdr.cdr.cdr.car, fc, head);
				this.command[this.pc] = new Code(Command.RET);
				this.pc++;
			}
			else if (expr.getId().equals("defun")) {
				Conscell save_element = expr.cdr.cdr.car;
				while (save_element.getFlag() == Flag.string) {
					if (save_element.getId().equals("Nil")) {
						break;
					}
					this.arg.addLast(save_element.getId());
					;
					save_element = save_element.cdr;
				}
				this.func_name.addLast(expr.cdr.getId());
				head = expr.car;
				compiler(expr.car, fc, head);
				this.command[this.pc] = new Code(Command.ENTRY);
				this.pc++;
				this.entry[this.ec] = this.pc;
				this.ec++;
				this.command[this.pc] = new Code(fc);
				fc++;
				this.pc++;
				for (int i = 0; i < this.arg.size(); i++) {
					this.command[this.pc] = new Code(Command.STOREA);
					this.pc++;
					this.command[this.pc] = new Code(i);
					this.pc++;
				}
				compiler(expr.cdr.cdr.cdr.car, fc, head);
			}
			else if (expr.getId().equals("car")) {
				compiler(expr.car, fc, head);
			}
			else if (this.func_name.indexOf(expr.getId()) != -1) {
				Conscell save_element = expr.cdr;
				for (int i = 0; i < this.arg.size(); i++) {
					compiler(save_element, fc, head);
					save_element = save_element.cdr;
				}
				this.command[this.pc] = new Code(Command.CALL);
				this.pc++;
				this.command[this.pc] = new Code(this.func_name.indexOf(expr.getId()));
				this.pc++;
				if (expr == head) {
					this.command[this.pc] = new Code(Command.RET);
					this.pc++;
				}
			}
			else if (expr.getId().equals("Nil") && this.head.pop() == head) {
				this.command[this.pc] = new Code(Command.RET);
				this.pc++;
			}
			else if (expr.getId().equals("Nil")) {
			}
			else {//演算子の場合
				this.head.push(expr);
				int computation = 0;
				int numbercheck = 0;
				Conscell save_element = expr.cdr;
				String operator = expr.getId();
				while (save_element.getFlag() == Flag.number || save_element.getFlag() == Flag.car || this.arg.indexOf(save_element.getId()) != -1) {
					if (save_element.cdr.getFlag() == Flag.number || save_element.cdr.getFlag() == Flag.car || this.arg.indexOf(save_element.cdr.getId()) != -1) {
						computation++;
					}
					if (numbercheck != 0) {
						if ((save_element.getValue() != 1 && save_element.getValue() != 2) || (operator.equals("*") || operator.equals("/"))) {
							compiler(save_element, fc, head);
						}
					}
					else {
						compiler(save_element, fc, head);
					}
					save_element = save_element.cdr;
					numbercheck++;
				}
				save_element = expr.cdr;
				for (int i = 0; i < computation; i++) {
					if ((save_element.cdr.getValue() != 1 && save_element.cdr.getValue() != 2) || (operator.equals("*") || operator.equals("/"))) {
						make_operator(operator);
					}
					else if (save_element.cdr.getValue() == 1) {
						make_operator(operator + "1");
					}
					else if (save_element.cdr.getValue() == 2) {
						make_operator(operator + "2");
					}
					save_element = save_element.cdr;
				}
				compiler(save_element.cdr, fc, head);
			}
		} catch (java.lang.ArrayIndexOutOfBoundsException e) {
			System.out.println("エラー：入力された数式に誤りがあります。\n強制終了します。");
			System.exit(0);
		} catch (java.lang.NullPointerException e) {
			System.out.println("エラー：入力された数式に誤りがあります。\n強制終了します。");
			System.exit(0);
		}
	}

	public void make_operator(String operator) {
		switch (operator) {
		case "+":
			this.command[this.pc] = new Code(Command.ADD);
			this.pc++;
			break;

		case "+1":
			this.command[this.pc] = new Code(Command.ADD1);
			this.pc++;
			break;

		case "+2":
			this.command[this.pc] = new Code(Command.ADD2);
			this.pc++;
			break;

		case "-":
			this.command[this.pc] = new Code(Command.SUB);
			this.pc++;
			break;

		case "-1":
			this.command[this.pc] = new Code(Command.SUB1);
			this.pc++;
			break;

		case "-2":
			this.command[this.pc] = new Code(Command.SUB2);
			this.pc++;
			break;

		case "*":
			this.command[this.pc] = new Code(Command.MUL);
			this.pc++;
			break;

		case "/":
			this.command[this.pc] = new Code(Command.DIV);
			this.pc++;
			break;

		case "<":
			this.command[this.pc] = new Code(Command.LT);
			this.pc++;
			break;

		case "<=":
			this.command[this.pc] = new Code(Command.LTEQ);
			this.pc++;
			break;

		case ">":
			this.command[this.pc] = new Code(Command.GT);
			this.pc++;
			break;

		case ">=":
			this.command[this.pc] = new Code(Command.GTEQ);
			this.pc++;
			break;

		default:
			break;
		}
	}

	public int getpc() {
		return this.pc;
	}

	public int[] getEntry() {
		return this.entry;
	}

	public int[] getLabel() {
		return this.label;
	}

	public int getargsize() {
		return this.arg.size();
	}

	public Code[] getCommand() {
		return this.command;
	}
}
