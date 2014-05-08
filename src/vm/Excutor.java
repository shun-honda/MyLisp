package vm;

import java.util.LinkedList;

public class Excutor {
	int call_count;

	public int excute(Code[] func, int[] entry, int[] label, int argsize) {
		LinkedList var = new LinkedList();
		int[] arg = new int[1000];
		LinkedList<Integer> r_adress = new LinkedList<Integer>();
		Variable_Length_List vm = new Variable_Length_List();
		int x, y;//作業用変数
		int count = func.length;
		int result = 0;
		int pc = 0, argpointer = 0;
		int argcount = 0;
		try {
			while (pc < count) {
				if (func[pc].command != null) {
					switch (func[pc].command) {
					case RET:
						if (r_adress.size() != 0) {
							for (int k = argpointer - (argsize - 1); k <= argpointer; k++) {
								arg[k] = 0;
							}
							argpointer = argpointer - argsize;
							pc = r_adress.pop();
						}
						else {
							result = vm.pop();
							pc = count - 1;
						}
						break;

					case PUSH:
						pc++;
						if (var.indexOf(func[pc].str) != -1) {
							vm.push((int) var.get(var.indexOf(func[pc].str) + 1));
						}
						else {
							vm.push(func[pc].num);
						}
						break;

					case POP:
						vm.pop();
						break;

					case MOV:
						pc++;
						var.add(func[pc].str);
						pc++;
						var.add(func[pc].num);
						break;

					case ADD:
						y = vm.pop();
						x = vm.pop();
						vm.push(x + y);
						break;

					case ADD1:
						x = vm.pop();
						vm.push(x + 1);
						break;

					case ADD2:
						x = vm.pop();
						vm.push(x + 2);
						break;

					case SUB:
						y = vm.pop();
						x = vm.pop();
						vm.push(x - y);
						break;

					case SUB1:
						x = vm.pop();
						vm.push(x - 1);
						break;

					case SUB2:
						x = vm.pop();
						vm.push(x - 2);
						break;

					case MUL:
						y = vm.pop();
						x = vm.pop();
						vm.push(x * y);
						break;

					case DIV:
						y = vm.pop();
						x = vm.pop();
						vm.push(x / y);
						break;

					case GT:
						y = vm.pop();
						x = vm.pop();
						if (x > y) {
							vm.push(1);
						}
						else {
							vm.push(0);
						}
						break;

					case GTEQ:
						y = vm.pop();
						x = vm.pop();
						if (x >= y) {
							vm.push(1);
						}
						else {
							vm.push(0);
						}
						break;

					case LT:
						y = vm.pop();
						x = vm.pop();
						if (x < y) {
							vm.push(1);
						}
						else {
							vm.push(0);
						}
						break;

					case LTEQ:
						y = vm.pop();
						x = vm.pop();
						if (x <= y) {
							vm.push(1);
						}
						else {
							vm.push(0);
						}
						break;

					case EQ:
						y = vm.pop();
						x = vm.pop();
						if (x == y) {
							vm.push(1);
						}
						else {
							vm.push(0);
						}
						break;

					case NEQ:
						y = vm.pop();
						x = vm.pop();
						if (x != y) {
							vm.push(1);
						}
						else {
							vm.push(0);
						}
						break;

					case BEQ0:
						pc++;
						if (vm.pop() == 0) {
							pc = label[func[pc].num];
						}
						break;

					case LABEL:
						pc++;
						break;

					case JUMP:
						pc++;
						pc = label[func[pc].num];
						break;

					case CALL:
						pc++;
						r_adress.push(pc);
						pc = entry[func[pc].num];
						break;

					case ENTRY:
						pc++;
						break;

					case LOADA:
						pc++;
						vm.push(arg[argpointer - (argsize - (func[pc].num))]);
						break;

					case STOREA:
						pc++;
						argcount++;
						arg[argpointer + argsize - argcount] = vm.pop();
						if (argcount == argsize) {
							argpointer = argpointer + argsize;
							argcount = 0;
						}
						break;

					default:
						break;

					}
					pc++;
				}
				else {
					System.out.println("エラー\n");
					System.exit(0);
				}
			}
		} catch (java.lang.NullPointerException e) {
			System.out.println("エラー：入力された数式に誤りがあります\n強制終了します。");
			System.exit(0);
		} catch (java.lang.ArrayIndexOutOfBoundsException e) {
			System.out.println("エラー：入力された数式に誤りがあります\n強制終了します。");
			System.exit(0);
		}
		return result;
	}

}
