package main;

import java.util.LinkedList;

import parse.Conscell;
import parse.Parser;
import token.Lexer;
import calc.Preparation;

public class Main {
	public static void main(String[] args) {
		Conscell root = new Conscell("");
		LinkedList<String> j = new LinkedList<String>();
		String expr = "(setq x 10)(setq y 20)(if(> x y)(- x y)(- y x))";
		Lexer lexer = new Lexer();
		LinkedList list = lexer.Analyse(expr);
		Parser parse = new Parser();
		Conscell result = parse.make_cell(list, 0, root);
		Preparation calculator = new Preparation();
		LinkedList ans = calculator.eval(result, "", result, j);
		while (ans.size() > 0) {
			System.out.println(ans.remove(0));
		}
	}
}
