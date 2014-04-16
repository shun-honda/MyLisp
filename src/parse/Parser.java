package parse;

import java.util.LinkedList;

public class Parser {
	public Conscell make_cell(LinkedList list, int check, Conscell head) {
		Conscell cell;
		if (check == 0) {
			list.remove(0);
		}

		if (list.get(0).equals("(")) {
			cell = new Conscell("car");
			list.remove(0);
			cell.car = make_cell(list, 1, head);
			cell.cdr = make_cell(list, 1, head);
		}
		else if (list.get(0).equals(")")) {
			cell = new Conscell("Nil");
			list.remove(0);
		}
		else if (list.get(0).getClass().getName().equals("java.lang.String")) {
			cell = new Conscell((String) list.get(0));
			if (check == 0) {
				head = cell;
			}
			list.remove(0);
			cell.cdr = make_cell(list, 1, head);
		}
		else {
			cell = new Conscell((int) list.get(0));
			list.remove(0);
			cell.cdr = make_cell(list, 1, head);
		}
		if (cell == head) {
			if (!(list.isEmpty())) {
				cell.car = make_cell(list, 0, head);
			}
		}
		return cell;
	}
}
