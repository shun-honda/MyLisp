package main;

import token.Token;

public class Main {
	public static void main(String[] args){
		String expr = "(+ 1 * 13 3)";
		Token token = new Token();
		token.make_Token(expr);
	}
}
