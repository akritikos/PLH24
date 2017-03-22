package info.akritikos.eelections.utils;

import info.akritikos.eelections.contracts.XMLExport;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.StringWriter;

/**
 * Created by akritikos on 13/03/2017.
 */
@XmlType
@XmlRootElement
public class XMLElectionResults extends ElectionResults implements XMLExport {

	@Override
	public String toXML() {
		java.io.StringWriter sw = new StringWriter();
		try {
			JAXBContext context = JAXBContext.newInstance(XMLElectionResults.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(this, sw);
		} catch (JAXBException e) {
			return null;
		}
		return sw.toString();
	}
}
