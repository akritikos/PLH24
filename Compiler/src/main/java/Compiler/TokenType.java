package Compiler;

public enum TokenType {
	whitespaceTK(""),					//λευκός χαρακτήρας
	newlineTK(""),						// αλλαγή γραμμής
	// Έκδοση 1.1 Διαγράφηκε το παρακάτω
	// digitwordTK(""),
	constantTK("-?\\d+\\.\\d+|-?\\d+"),	// αριθμητική σταθερά
	programTK(""),						// δεσμευμένη λέξη program
	declareTK(""),						// δεσμευμένη λέξη declare
	enddeclareTK(""),					// δεσμευμένη λέξη enddeclare
	beginTK("begin"),					// δεσμευμένη λέξη begin
	endTK(""),							// δεσμευμένη λέξη end
	printTK(""),						// δεσμευμένη λέξη print
	inputTK(""),						// δεσμευμένη λέξη input
	variableTK(""),						// μεταβλητή
	plusTK("\\+"),						// σύμβολο πρόσθεσης
	minusTK(""),						// σύμβολο αφαίρεσης
	multTK(""),							// σύμβολο πολλαπλασιασμού
	divTK(""),							// σύμβολο διαίρεσης
	leftpTK(""),						// σύμβολο αριστερής παρένθεσης
	rightpTK(""),						// σύμβολο δεξιάς παρένθεσης
	assignTK(""),						// σύμβολο εκχώρησης
	semicolTK(""),						// ελληνικό ερωτηματικό
	commaTK(""),						// κόμμα
	unknownTK(""),						// άγνωστος χαρακτήρας, θα οδηγεί σε μήνυμα σφάλματος
	ofTK("\\Z");			// τέλος προγράμματος

	public final String pattern;
	private TokenType(String pattern) {
		this.pattern = pattern;
	}
}
