package parse;

import java.util.LinkedList;


public class Parse {
	public Cons_cell make_cell(LinkedList<String> list, int check) {
		Cons_cell cell;
		if(check == 0){
			list.remove(0);
		}
		switch (list.get(0)) {
		case "(":
			cell = new Cons_cell("car");
			list.remove(0);
			cell.car = make_cell(list, 1);
			cell.cdr = make_cell(list, 1);
			break;

		case ")":
			cell = new Cons_cell ("Nil");
			list.remove(0);
			break;

		default :
			cell = new Cons_cell (list.get(0));
			list.remove(0);
			cell.cdr = make_cell(list, 1);
			break;
		}
		return cell;
	}
}
