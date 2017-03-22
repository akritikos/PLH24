package info.akritikos.eelections.utils;

import javax.xml.bind.annotation.*;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Provided code for ElectionResults
 * Created by akritikos on 13/03/2017.
 */
@XmlRootElement
public class ElectionResults extends LinkedHashMap {
	private String name;
	public ElectionResults() {
		super();
	}
	public ElectionResults(String name) {
		super();
		this.name = name;
	}
	//Αντιγράφει τη λίστα m στην τρέχουσα λίστα
	public ElectionResults(String name, Map m) {
		super(m);
		this.name = name;
	}
	@XmlTransient
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@XmlElement
	public Collection<String> getValues() {
		return this.values();
	}
}
