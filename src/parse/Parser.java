package parse;

import java.util.LinkedList;

import enums.Flag;

public class Parser {
	public Conscell make_cell(LinkedList list, int check, Conscell head) {
		Conscell cell;
		if (check == 0) {
			list.remove(0);
		}

		if (list.get(0).equals("(")) {
			cell = new Conscell("car");
			cell.setType(Flag.car);
			list.remove(0);
			cell.car = make_cell(list, 1, head);
			cell.cdr = make_cell(list, 1, head);
		}
		else if (list.get(0).equals(")")) {
			cell = new Conscell("Nil");
			cell.setType(Flag.string);
			list.remove(0);
		}
		else if (list.get(0).getClass().getName().equals("java.lang.String")) {
			cell = new Conscell((String) list.get(0));
			if (cell.getId().matches("\\p{Punct}")) {
				cell.setType(Flag.operator);
			}
			else {
				cell.setType(Flag.string);
			}
			if (check == 0) {
				head = cell;
			}
			list.remove(0);
			cell.cdr = make_cell(list, 1, head);
		}
		else {
			cell = new Conscell((int) list.get(0));
			cell.setType(Flag.number);
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
