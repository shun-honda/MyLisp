package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

import parse.Conscell;
import parse.Parser;
import token.Lexer;
import vm.Code;
import vm.Compiler;
import vm.Excutor;

public class Main {
	public static void main(String[] args) {
		//int i = 0;
		String inputexpr = args[0];
		try {
			File file = new File(inputexpr);
			Scanner scan = new Scanner(file);
			String expr = "";
			while (scan.hasNext()) {
				expr = expr + scan.nextLine();
			}
			System.out.println("\nInput Expression：\n\n" + expr + "\n\nResult：\n");
			excute_command(expr);
		} catch (FileNotFoundException e) {
			System.out.println(e);
		}
		/*
		while (i == 0) {
			System.out.println("\n式の入力\n手入力の場合は０、ファイルからの入力の場合は１を入力してください。\n入力：");
			try {
				int inputNum = new java.util.Scanner(System.in).nextInt();
				if (inputNum == 0) {
					System.out.println("\n手入力が選択されました。\n式の入力をしてください\n\n式入力：");
					String inputexpr = new java.util.Scanner(System.in).nextLine();
					System.out.println("\n入力を確認しました。これより実行します。\n実行結果：\n");
					excute_command(inputexpr);
				}
				else if (inputNum == 1) {
					System.out.println("\nファイルからの入力が選択されました。\nファイル名の入力をしてください\n\nファイル名入力：");
					String inputexpr = new java.util.Scanner(System.in).nextLine();
					try {
						File file = new File(inputexpr);
						Scanner scan = new Scanner(file);
						String expr = "";
						while (scan.hasNext()) {
							expr = expr + scan.nextLine();
						}
						System.out.println("\n入力を確認しました。\nファイルから読み取った式：\n\n" + expr + "\n\nこれより実行します。\n実行結果：\n");
						excute_command(expr);
					} catch (FileNotFoundException e) {
						System.out.println(e);
					}
				}
				else {
					System.out.println("入力エラー\n");
				}
			} catch (java.util.InputMismatchException e) {
				System.out.println(e + "　　（int型で入力してください。）\n");
			}
			i = 2;
			while (i != 0 && i != 1) {
				System.out.println("\nもう一度行いますか（続行：０　終了：１）");
				try {
					i = new java.util.Scanner(System.in).nextInt();
					if (i != 0 && i != 1) {
						System.out.println("\n入力エラー\n");
					}
				} catch (java.util.InputMismatchException e) {
					System.out.println("\n" + e + "　　（int型で入力してください。）\n");
				}
			}
			if (i == 0) {
				System.out.println("\n続行します。");
			}
			else {
				System.out.println("\n終了します。");
			}
		}
		*/
	}

	public static void excute_command(String expr) {
		long t1 = System.currentTimeMillis();
		Conscell root = new Conscell("");
		Lexer lexer = new Lexer();
		LinkedList list = lexer.Analyse(expr);
		Parser parse = new Parser();
		Conscell cell = parse.make_cell(list, 0, root);
		Compiler compiler = new Compiler();
		compiler.compiler(cell, 0, cell);
		Code[] command = compiler.getCommand();
		Excutor r_command = new Excutor();
		LinkedList<Integer> result = new LinkedList<Integer>();
		result = r_command.excute(command, compiler.getEntry(), compiler.getLabel(), compiler.getargsize(), compiler.getFormulapointer(), compiler.getCommandsize());
		/*
		Evaluator calculator = new Evaluator();
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
		}*/
		while (result.size() != 0) {
			System.out.println(result.removeFirst());
		}
		long t2 = System.currentTimeMillis();
		System.out.println(t2 - t1 + " [ms]\n");
	}
}
