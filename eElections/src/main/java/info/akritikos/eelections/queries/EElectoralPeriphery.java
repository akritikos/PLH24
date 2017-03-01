package info.akritikos.eelections.queries;

import info.akritikos.eelections.contracts.ISearchQueries;

/**
 * Search fields on ElectoralPeriphery entities
 */
public enum EElectoralPeriphery implements ISearchQueries {
	PkElectoralPeripheryId,
	FldName,
	FldRegisteredCitizensCount,
	FldSeatsCount
}
