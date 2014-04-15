package main;

import java.util.LinkedList;

import parse.Cons_cell;
import parse.Parse;
import token.Token;
import calc.Preparation;

public class Main {
	public static void main(String[] args) {
		Cons_cell i = new Cons_cell("");
		String expr = "(setq x 10)(setq y 3)(setq z 30)(if(> (* x y) z)(-(* x y) z)(- z (* x y)))";
		Token token = new Token();
		LinkedList<String>list = token.Lexical_Analysis(expr);
		Parse parse = new Parse();
		Cons_cell result = parse.make_cell (list, 0, i);
		Preparation calculator = new Preparation();
		String ans = calculator.eval(result,"", result, list);
		System.out.println(ans);
	}
}
