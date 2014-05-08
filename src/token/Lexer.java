package token;

import java.util.LinkedList;

public class Lexer {
	int count, pcount = 0;
	LinkedList list = new LinkedList();

	public LinkedList<String> Analyse(String expr) {
		String num = "", string = "";
		String[] str = expr.split("");
		try {
			for (this.count = 0; this.count < str.length; this.count++) {
				if (str[this.count].matches("^[0-9]")) {
					addNumToken(str);
				}
				else if (str[this.count].matches("\n")) {
				}
				else if (str[this.count].matches("")) {
				}
				else if (str[this.count].matches("[A-Za-z()]*||\\p{Punct}")) {
					addIdToken(str);
				}
			}
		} catch (java.lang.IndexOutOfBoundsException e) {
			System.out.println("エラー：入力された数式に誤りがあります。\n強制終了します。");
			System.exit(0);
		}
		if (pcount != 0) {
			System.out.println("エラー：入力された数式に誤りがあります。\n（括弧の数が合いません。）\n強制終了します。");
			System.exit(0);
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
			if (id.equals("(")) {
				this.list.add(id);
				this.pcount++;
				break;
			}
			else if (id.equals(")")) {
				this.list.add(id);
				this.pcount--;
				break;
			}
			else if (id.matches("\\p{Punct}")) {
				if (this.count == 0) {
					System.out.println("エラー：入力された数式に誤りがあります。\n強制終了します。");
					System.exit(0);
				}
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
