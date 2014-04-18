package main;

import java.util.LinkedList;

import parse.Conscell;
import parse.Parser;
import token.Lexer;
import calc.Evaluation;
import calc.Value_List;
import enums.Flag;

public class Main {
	public static void main(String[] args) {
		Conscell root = new Conscell("");
		Value_List ans = new Value_List("");
		LinkedList<String> j = new LinkedList<String>();
		String expr = "(defun fib (n) (if(< n 3)(1)(+ (fib(- n 1))(fib(- n 2))))) (fib 36)";
		Lexer lexer = new Lexer();
		LinkedList list = lexer.Analyse(expr);
		Parser parse = new Parser();
		Conscell result = parse.make_cell(list, 0, root);
		Evaluation calculator = new Evaluation();
		ans = calculator.eval(result, "", result, j, ans);
		while (ans != null) {
			if (ans.getType() == Flag.number) {
				System.out.println(ans.getValue());
				ans = ans.nextElement;
			}
			else {
				System.out.println(ans.getId());
				ans = ans.nextElement;
			}
		}
	}
}
