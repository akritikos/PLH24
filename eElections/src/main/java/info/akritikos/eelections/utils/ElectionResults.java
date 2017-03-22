package info.akritikos.eelections.utils;

import javax.xml.bind.annotation.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Provided code for ElectionResults
 * Created by akritikos on 13/03/2017.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class ElectionResults extends HashMap {
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
//	@XmlAttribute
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
