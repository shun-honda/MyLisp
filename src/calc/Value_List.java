package calc;

import enums.Type;

public class Value_List {
	private String id;
	private int num;
	private Type type;
	public Value_List nextElement;
	public Value_List Type;

	public Value_List(String id) {
		this.id = id;
	}

	public Value_List(int num) {
		this.num = num;
	}

	public Value_List(Type type) {
		this.type = type;
	}

	public String getId() {
		return this.id;
	}

	public int getValue() {
		return this.num;
	}

	public Type getType() {
		return this.type;
	}

	public void setType(Type type) {
		this.type = type;
	}

}
