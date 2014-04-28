package vm;


public class Variable_Length_List {
	int[] list ;
	int i = 0;
	public Variable_Length_List(){
		this.list = new int[1000];
	}

	public void push(int value){
		this.list[this.i] = value;
		this.i++;
	}

	public int pop(){
		this.i--;
		int value = this.list[this.i];
		this.list[this.i] = 0;
		return value;
	}

	public int size(){
		return this.i + 1;
	}
}
