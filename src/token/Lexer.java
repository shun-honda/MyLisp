package token;

import java.util.LinkedList;

public class Lexer {
	int count;
	LinkedList list = new LinkedList();

	public LinkedList<String> Analyse(String expr) {
		String num = "", string = "";
		String[] str = expr.split("");
		for (this.count = 0; this.count < str.length; this.count++) {
			if (str[this.count].matches("^[0-9]")) {
				addNumToken(str);
			}
			else if (str[this.count].matches("")) {
			}
			else if (str[this.count].matches("[A-Za-z()]*||\\p{Punct}")) {
				addIdToken(str);
			}
		}
		return list;
	}

	public void addNumToken(String[] str) {
		String num = str[this.count];
		while (true) {
			if (str[this.count + 1].matches("^[0-9]")) {
				num += str[this.count + 1];
				this.count++;
			}
			else {
				this.list.add(Integer.parseInt(num));
				break;
			}
		}
	}

	public void addIdToken(String[] str) {
		String id = str[this.count];
		while (true) {
			if (id.matches("[()]")) {
				this.list.add(id);
				break;
			}
			else if (id.matches("\\p{Punct}")) {
				if (str[this.count + 1].equals("=")) {
					id += str[this.count + 1];
					this.count++;
				}
				this.list.add(id);
				break;
			}
			if (str[this.count + 1].matches("^[0-9A-Za-z_]")) {
				id += str[this.count + 1];
				this.count++;
			}
			else {
				this.list.add(id);
				break;
			}
		}
	}

}
