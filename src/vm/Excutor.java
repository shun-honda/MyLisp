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
		int i = 0, j = 0;
		int argcount = 0;
		while (i < count) {
			if (func[i].command != null) {
				switch (func[i].command) {
				case RET:
					if (r_adress.size() != 0) {
						for(int k = j - (argsize - 1); k <= j; k++) {
							arg[k]=0;
						}
						j= j - argsize;
						i = r_adress.pop();
					}
					else {
						result = vm.pop();
						i = count - 1;
					}
					break;

				case PUSH:
					i++;
					if (var.indexOf(func[i].str) != -1) {
						vm.push((int) var.get(var.indexOf(func[i].str) + 1));
					}
					else {
						vm.push(func[i].num);
					}
					break;

				case POP:
					vm.pop();
					break;

				case MOV:
					i++;
					var.add(func[i].str);
					i++;
					var.add(func[i].num);
					break;

				case ADD:
					y = vm.pop();
					x = vm.pop();
					vm.push(x + y);
					break;

				case SUB:
					y = vm.pop();
					x = vm.pop();
					vm.push(x - y);
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
					i++;
					if (vm.pop() == 0) {
						i = label[func[i].num];
					}
					break;

				case LABEL:
					i++;
					break;

				case JUMP:
					i++;
					i = label[func[i].num];
					break;

				case CALL:
					i++;
					r_adress.push(i);
					i = entry[func[i].num];
					break;

				case ENTRY:
					i++;
					break;

				case LOADA:
					i++;
					vm.push(arg[j - (argsize - (func[i].num))]);
					break;

				case STOREA:
					i++;
					argcount++;
					arg[j + argsize - argcount] = vm.pop();
					if(argcount == argsize){
						j = j + argsize;
						argcount = 0;
					}
					break;

				default:
					break;

				}
				i++;
			}
			else {
				System.out.println("エラー\n");
				System.exit(0);
			}
		}
		return result;
	}

}
