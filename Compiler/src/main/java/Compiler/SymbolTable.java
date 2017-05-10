package Compiler;

import java.util.ArrayList;
import java.util.List;

public class SymbolTable {
	private static List<String> symbols;

	public SymbolTable() {
		symbols = new ArrayList<>();
	}

	public void addSymbol(String s) {
		symbols.add(s);
	}

	public boolean checkIfInSymbolTable(String s) {
		boolean result = symbols.contains(s);
		if (!result) {
			Parser.error("Variable '" + s + "' is not defined!");
		}
		return result;
	}
}
