package parse;

public class Conscell {
	private String id;
	private int num;
	public Conscell car;
	public Conscell cdr;

	public Conscell(String id) {
		this.id = id;
	}

	public Conscell(int num) {
		this.num = num;
	}

	public String getId() {
		return this.id;
	}

	public int getValue() {
		return this.num;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setValue(int value) {
		this.num = value;
	}
}
