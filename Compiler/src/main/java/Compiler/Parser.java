package Compiler;

import java.util.ArrayList;

public class Parser {

	static ArrayList<Token> tokens;
	static Token token;
	static SymbolTable symbolTable;

	public Parser(ArrayList<Token> tokens) {
		symbolTable = new SymbolTable();
		Parser.tokens = tokens;
		program();
	}

	private static Token next() {
		return tokens.remove(0);
	}

	public static void error(String s) {
		System.out.println("Error: '" + s + "'\n\t in line :" + token.getLine());
	}

	// program program_name <BLOCK>
	private static void program() {
		token = next();
		if (token.getType() == TokenType.programTK) {
			token = next();
			if (token.getType() == TokenType.variableTK) {
				token = next();
				block();
			} else {
				error("Invalid program name");
			}
		} else {
			error("Missing keyword program");
		}
	}

	// begin <DECLARATIONS> <SEQUENCE> end
	private static void block() {
		if (token.getType() == TokenType.beginTK) {
			token = next();
			declarations();
			sequence();
			if (token.getType() != TokenType.endTK) {
				error("Missing keyword end");
			}
		} else {
			error("No begin point defined");
		}
		System.out.println("Syntactical analysis complete");
	}

	//<DECLARATIONS>::= ε | declare <VARLIST> enddeclare
	public static void declarations() {
		if (token.getType() == TokenType.declareTK) {
			token = next();
			varlist();
			if (token.getType() == TokenType.enddeclareTK) {
				token = next();
			} else {
				error("keyword enddeclare expected after keyword declare");
			}
		}
	}

	// ε | variable  ( , variable )*
	private static void varlist() {
		// η λίστα μεταβλητών (0 indexed) θα έχει μεταβλητές για ζυγό index και κόμμα για μονό
		int vars = 0;
		do {
			if (token.getType() == TokenType.variableTK && vars % 2 == 0) {
				symbolTable.addSymbol(token.getData());
				token = next();
			} else if (token.getType() == TokenType.commaTK && vars % 2 == 1) {
				token = next();
			} else if (token.getType() != TokenType.enddeclareTK) {
				error("Malformed variable declaration");
			}
			vars++;
		} while (token.getType() != TokenType.enddeclareTK);
	}

	// <SEQUENCE> ::= <STATEMENT> ( ; <STATEMENT> )*
	private static void sequence() {
		statement();
		while (token.getType() == TokenType.semicolTK) {
			token = next();
			statement();

		}
	}

	// ε | <INPUT-STAT> | <ASSIGNMENT-STAT> | <PRINT-STAT>
	public static void statement() {
		if (null != token.getType())
			switch (token.getType()) {
			case inputTK:
				input_stat();
				break;
			case variableTK:
				symbolTable.checkIfInSymbolTable(token.getData());
				assignment_stat();
				break;
			case printTK:
				print_stat();
				break;
			default:
				break;
			}
	}

	// input (variable)
	public static void input_stat() {
		token = next();
		if (token.getType() == TokenType.leftpTK) {
			token = next();
			if (token.getType() == TokenType.variableTK) {
				symbolTable.checkIfInSymbolTable(token.getData());
				token = next();
				if (token.getType() == TokenType.rightpTK) {
					token = next();
				} else {
					error("Right parenthesis expected");
				}
			} else {
				error("Variable expected");
			}
		} else {
			error("Left parenthesis expected");
		}
	}

	// variable := <EXPRESSION>
	public static void assignment_stat() {
		token = next();
		if (token.getType() == TokenType.assignTK) {
			token = next();
			expression();
		} else {
			error("Assigment symbol expected");
		}
	}

	// print (<EXPRESSION>)
	public static void print_stat() {
		token = next();
		if (token.getType() == TokenType.leftpTK) {
			token = next();
			expression();
			if (token.getType() == TokenType.rightpTK) {
				token = next();
			} else {
				error("Right parenthesis expected");
			}
		}
	}

	// <OPTIONAL-SIGN> <TERM> (<ADD-OPER> <TERM>)*
	public static void expression() {
		optionalSign();
		term();
		while (addOper()) {
			term();
		}
	}

	// <FACTOR> (<MUL-OPER> <FACTOR>)*
	public static void term() {
		factor();
		while (mulOper()) {
			factor();
		}
	}

	// constant | variable | (<EXPRESSION>)
	public static void factor() {
		if (null == token.getType()) {
			if (token.getType() == TokenType.leftpTK) {
				token = next();
				expression();
				if (token.getType() == TokenType.rightpTK) {
					token = next();
				} else {
					error("Right parenthesis is expected");
				}
			}
		} else
			switch (token.getType()) {
			case variableTK:
				symbolTable.checkIfInSymbolTable(token.getData());
				token = next();
				break;
			case constantTK:
				token = next();
				break;
			default:
				if (token.getType() == TokenType.leftpTK) {
					token = next();
					expression();
					if (token.getType() == TokenType.rightpTK) {
						token = next();
					} else {
						error("Right parenthesis is expected");
					}
				}
				break;
			}
	}

	// + | -
	public static boolean addOper() {
		if (token.getType() == TokenType.plusTK || token.getType() == TokenType.minusTK) {
			token = next();
			return true;
		}
		return false;
	}

	// * | /
	public static boolean mulOper() {
		if (token.getType() == TokenType.multTK || token.getType() == TokenType.divTK) {
			token = next();
			return true;
		}
		return false;
	}

	// ε | <ADD-OPER>
	public static void optionalSign() {
		addOper();
	}
}
