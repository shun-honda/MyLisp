package parse;

public class Cons_cell {
	private String str;
	public Cons_cell car;
	public Cons_cell cdr;
	public Cons_cell(String str){
		this.str = str;
	}

	public String getValue() {
		return this.str;
	}

	public void setValue(String value){
		this.str = value;
	}
}
