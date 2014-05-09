package vm;

import java.util.LinkedList;
import java.util.Stack;

import enums.Command;

public class VM_Main {
	public static void main(String[] args) {
		long t1 = System.currentTimeMillis();
		Stack vm = new Stack();
		//LinkedList<Integer> entry = new LinkedList<Integer>();//関数の開始地点格納用リスト
		//LinkedList<Integer> label = new LinkedList<Integer>();
		Code[] command = new Code[37];
		LinkedList<String> commandarg = new LinkedList<String>();

		command[0] = new Code(Command.PUSH);
		command[1] = new Code(36);
		command[2] = new Code(Command.CALL);
		command[3] = new Code(0);
		command[4] = new Code(Command.RET);
		command[5] = new Code(Command.ENTRY);
		command[6] = new Code(0);
		command[7] = new Code(Command.STOREA);
		command[8] = new Code(0);
		command[9] = new Code(Command.LOADA);
		command[10] = new Code(0);
		command[11] = new Code(Command.PUSH);
		command[12] = new Code(3);
		command[13] = new Code(Command.LT);
		command[14] = new Code(Command.BEQ0);
		command[15] = new Code(0);
		command[16] = new Code(Command.PUSH);
		command[17] = new Code(1);
		command[18] = new Code(Command.RET);
		command[19] = new Code(Command.LABEL);
		command[20] = new Code(0);
		command[21] = new Code(Command.LOADA);
		command[22] = new Code(0);
		command[23] = new Code(Command.PUSH);
		command[24] = new Code(1);
		command[25] = new Code(Command.SUB);
		command[26] = new Code(Command.CALL);
		command[27] = new Code(0);
		command[28] = new Code(Command.LOADA);
		command[29] = new Code(0);
		command[30] = new Code(Command.PUSH);
		command[31] = new Code(2);
		command[32] = new Code(Command.SUB);
		command[33] = new Code(Command.CALL);
		command[34] = new Code(0);
		command[35] = new Code(Command.ADD);
		command[36] = new Code(Command.RET);

		int[] entry = { 6 };
		int[] label = { 20 };
		Excutor r_command = new Excutor();
		//int x = r_command.excute(command, entry, label, 1);
		//System.out.println(x);
		long t2 = System.currentTimeMillis();
		System.out.println(t2 - t1);
	}
}
