package main;

import java.util.LinkedList;

import parse.Cons_cell;
import parse.Parse;
import token.Token;
import calc.Preparation;

public class Main {
	public static void main(String[] args) {
		String expr = "(+ 1 * (* 1 3)(/ 6 3))";
		Token token = new Token();
		LinkedList<String>list = token.Lexical_Analysis(expr);
		Parse parse = new Parse();
		Cons_cell result = parse.make_cell (list, 0);
		Preparation calculator = new Preparation();
		String ans = calculator.disposition(result,"");
		System.out.println(ans);
	}
}
