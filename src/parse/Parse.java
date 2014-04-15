package parse;

import java.util.LinkedList;


public class Parse {
	public Cons_cell make_cell(LinkedList<String> list, int check, Cons_cell head) {
		Cons_cell cell;
		if(check == 0){
			list.remove(0);
		}
		switch (list.get(0)) {
		case "(":
			cell = new Cons_cell("car");
			list.remove(0);
			cell.car = make_cell(list, 1, head);
			cell.cdr = make_cell(list, 1, head);
			break;

		case ")":
			cell = new Cons_cell ("Nil");
			list.remove(0);
			break;

		default :
			cell = new Cons_cell (list.get(0));
			if(check == 0){
				head = cell;
			}
			list.remove(0);
			cell.cdr = make_cell(list, 1, head);
			break;
		}
		if(cell == head){
			if(!(list.isEmpty())){
				cell.car = make_cell(list, 0, head);
			}
		}
		return cell;
	}
}
