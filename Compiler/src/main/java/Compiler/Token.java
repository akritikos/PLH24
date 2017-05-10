package Compiler;

/**
 * Simple implementation of Tokens, saves the type of the token,
 * data and the line number it occured in
 */
public class Token {
	private final TokenType type;
	private final String data;
	private final int line;

	public Token(TokenType type, String data, int line) {
		this.type = type;
		this.data = data;
		this.line = line;
	}

	public TokenType getType() {
		return type;
	}

	public String getData() {
		return data;
	}

	public int getLine() {
		return line;
	}

	@Override
	public String toString() {
		return String.format("Type: %s \tData: %s \tLine: %d", type, data, line);
	}
}
