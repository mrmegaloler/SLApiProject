package apiTest;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class URLParser {
	public static String GetURL(String Destination) throws SAXException, IOException, ParserConfigurationException{
		String URL = new String("http://api.sl.se/api2/typeahead.xml?key=bf4cf6c97ba440789f6f8827577435e2&stationsonly=True&maxresults=1&searchstring=");
		URL = URL + Destination;
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(URL);
		
		NodeList StationID = doc.getElementsByTagName("SiteId");
		Element StationIDE = (Element)StationID.item(0);
		
		String ID = StationIDE.getFirstChild().getNodeValue();
		System.out.print(ID);
		return ID;
	}

	public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {
		GetURL("Svedmyra");
	}

}
