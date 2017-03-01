package info.akritikos.eelections.queries;

import info.akritikos.eelections.contracts.ISearchQueries;

/**
 * Search fields on Candidate entities
 */
public enum EVote implements ISearchQueries {
	PkVoteId,
	FldIsInvalid,
	FldIsBlank
}