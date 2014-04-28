package vm;

import enums.Command;

public class Code {

	Command command;
	int num;
	String str;

	public Code(int num) {
		this.num = num;
	}

	public Code(String str) {
		this.str = str;
	}

	public Code(Command command) {
		this.command = command;
	}

}
