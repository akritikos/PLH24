package Compiler;

public enum TokenType {
	whitespaceTK("\\s"), //λευκός χαρακτήρας
	newlineTK("\\n"), // αλλαγή γραμμής
	constantTK("-?\\d+\\.\\d+|-?\\d+"), // αριθμητική σταθερά
	programTK("program"), // δεσμευμένη λέξη program
	declareTK("declare"), // δεσμευμένη λέξη declare
	enddeclareTK("enddeclare"), // δεσμευμένη λέξη enddeclare
	beginTK("begin"), // δεσμευμένη λέξη begin
	endTK("end"), // δεσμευμένη λέξη end
	printTK("print"), // δεσμευμένη λέξη print
	inputTK("input"), // δεσμευμένη λέξη input
	variableTK("[a-zA-Ζ]([a-ZA-z]|[0-9])*"), // μεταβλητή
	plusTK("\\+"), // σύμβολο πρόσθεσης
	minusTK("\\-"), // σύμβολο αφαίρεσης
	multTK("\\*"), // σύμβολο πολλαπλασιασμού
	divTK("\\/"), // σύμβολο διαίρεσης
	leftpTK("\\("), // σύμβολο αριστερής παρένθεσης
	rightpTK("\\)"), // σύμβολο δεξιάς παρένθεσης
	assignTK(":="), // σύμβολο εκχώρησης
	semicolTK(";"), // ελληνικό ερωτηματικό
	commaTK("."), // κόμμα
	unknownTK("."), // άγνωστος χαρακτήρας, θα οδηγεί σε μήνυμα σφάλματος
	ofTK("\\Z"); // τέλος προγράμματος

	public final String pattern;

	private TokenType(String pattern) {
		this.pattern = pattern;
	}
}
