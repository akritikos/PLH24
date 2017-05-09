package Compiler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Parser {

	static ArrayList<Token> tokens;
	static Token token;

	public Parser(ArrayList<Token> tokens) {
		this.tokens = tokens;
	}

	private static Token next() { // Missing part
	}

	public static void error(String s) { // Missing part
		System.out.println("Σφάλμα: " + s + "\n\t στη γραμμή :" + token.getLine());
	}

	// Missing part

	public static void declarations() {
		if (token.type.name() == "declareTK") {
			token = next();
			varlist();
			if (token.type.name() == "enddeclareTK") {
				token = next();
			} else {
				error("keyword enddeclare expected after keyword declare");
			}
		}
	}

	// Missing part

	public static void statement() {
		if (token.type.name() == "inputTK")
			input_stat();
		else if (token.type.name() == "variableTK") {
			symbolTable.checkIfInSymbolTable(token.data);
			assignment_stat();
		} else if (token.type.name() == "printTK")
			print_stat();
	}
}
