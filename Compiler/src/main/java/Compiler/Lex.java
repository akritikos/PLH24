package Compiler;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lex {
	private ArrayList<Token> tokens;

	public ArrayList<Token> getTokens() {
		return tokens;
	}

	public Lex(String filename) {
		String input = null;
		try {
			input = new Scanner(new File(filename)).useDelimiter("\\A").next();
		} catch (FileNotFoundException ex) {
			Logger.getLogger(Lex.class.getName()).log(Level.SEVERE, null, ex);
			System.exit(0);
		}

		tokens = new ArrayList<>();
		int lineCount = 1;
		StringBuilder tokenPatternsBuffer = new StringBuilder();
		for (TokenType tokenType : TokenType.values()) {
			tokenPatternsBuffer.append(String.format("|(?<%s>%s)", tokenType.name(), tokenType.pattern));
		}
		Pattern tokenPatterns = Pattern.compile(tokenPatternsBuffer.substring(1));
		Matcher matcher = tokenPatterns.matcher(input);
		System.out.println("Beginning analysis of file:\t" + filename);
		while (matcher.find()) {
			for (TokenType token : TokenType.values()) {
				if (matcher.group(token.name()) != null) {
					if (null != token.name()) {
						switch (token.name()) {
						case "unknownTK": // άγνωστος χαρακτήρας
							// Captured invalid token, output token name and line it was met at
							System.out.println("Unacceptable entry: " + matcher.group() + "\n\tin line:" + lineCount);
							System.exit(0);
							break;
						case "newlineTK": // αλλαγή γραμής
							// move the line counter
							lineCount++;
							break;
						case "whitespaceTK": // λευκός χαρακτήρας
							// whitespace is irrelevant, no actions required
							break;
						default: // κάθε τι άλλο
							// captured valid token, create new instance and save it
							Token t = new Token(token, matcher.group(), lineCount);
							tokens.add(t);
							break;
						} // switch token.name
					} // null check
				} // null check
			} // for TokenType
		} // while matcher.find
		System.out.println("Lexical analysis complete on file:\t" + filename);
	} // ctor
}
