package main;

import token.Token;

public class Main {
	public static void main(String[] args){
		String expr = "(+ ab * 13 3)";
		Token token = new Token();
		token.Lexical_Analysis(expr);
	}
}
