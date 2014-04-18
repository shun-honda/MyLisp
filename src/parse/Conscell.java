package parse;

import enums.Flag;

public class Conscell {
	private String id;
	private int num;
	private Flag type;
	public Conscell car;
	public Conscell cdr;
	public Conscell Type;

	public Conscell(String id) {
		this.id = id;
	}

	public Conscell(int num) {
		this.num = num;
	}

	public Conscell(Flag type) {
		this.type = type;
	}

	public String getId() {
		return this.id;
	}

	public int getValue() {
		return this.num;
	}

	public Flag getFlag() {
		return this.type;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setValue(int value) {
		this.num = value;
	}

	public void setType(Flag type) {
		this.type = type;
	}
}
