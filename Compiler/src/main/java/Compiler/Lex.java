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
		String input=null;
		try {
			input = new Scanner(new File(filename)).useDelimiter("\\A").next();
		}
		catch (FileNotFoundException ex) {
			Logger.getLogger(Lex.class.getName()).log(Level.SEVERE, null, ex);
		}

		tokens = new ArrayList<>();
		int lineCount = 1;
		StringBuilder tokenPatternsBuffer = new StringBuilder();
		for (TokenType tokenType : TokenType.values()) {
			tokenPatternsBuffer.append(String.format("|(?<%s>%s)", tokenType.name(), tokenType.pattern));
		}
		Pattern tokenPatterns = Pattern.compile(tokenPatternsBuffer.substring(1));
		Matcher matcher = tokenPatterns.matcher(input);
		while (matcher.find()) {
			for (TokenType token : TokenType.values()) {
				if (matcher.group(token.name()) != null) {
					if (null != token.name()) {
						switch (token.name()) {
							case "unknownTK":	// άγνωστος χαρακτήρας
								break;
							case "newLineTK":	// αλλαγή γραμής
								break;
							case "whitespaceTK":	// λευκός χαρακτήρας
								break;
							default:	// κάθε τι άλλο

								break;
						} // switch token.name
					} // null check
				} // null check
			}	// for TokenType
		} // while matcher.find
	} // ctor
}
