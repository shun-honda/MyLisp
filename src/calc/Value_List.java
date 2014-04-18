package calc;

import enums.Flag;

public class Value_List {
	private String id;
	private int num;
	private Flag type;
	public Value_List nextElement;
	public Value_List Type;

	public Value_List(String id) {
		this.id = id;
	}

	public Value_List(int num) {
		this.num = num;
	}

	public Value_List(Flag type) {
		this.type = type;
	}

	public String getId() {
		return this.id;
	}

	public int getValue() {
		return this.num;
	}

	public Flag getType() {
		return this.type;
	}

	public void setType(Flag type) {
		this.type = type;
	}

}
