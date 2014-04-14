package main;

import java.util.LinkedList;

import parse.Cons_cell;
import parse.Parse;
import token.Token;

public class Main {
	public static void main(String[] args){
		String expr = "(+ (* a1 13) 3)";
		Token token = new Token();
		LinkedList<String>list = token.Lexical_Analysis(expr);
		Parse parse = new Parse();
		Cons_cell result = parse.make_cell (list, 0);

	}
}
