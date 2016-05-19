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
	static int initialscan = 0;
	static String station = null;
	public static String GetURL(String Destination) throws SAXException, IOException, ParserConfigurationException{
		//sends a search query with this url to sl to find the id of the stations
		if(initialscan == 0){
		String URL = new String("http://api.sl.se/api2/typeahead.xml?key=bf4cf6c97ba440789f6f8827577435e2&stationsonly=True&maxresults=3&searchstring=");
		//sets up the document where the xml file ends up
		URL = URL + Destination;
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(URL);
		//find nodes with name siteid and put them in nodelist
		NodeList StationID = doc.getElementsByTagName("SiteId");
		Element StationIDE = (Element)StationID.item(0);
		//get the value of the first child of node with name stationIde
		String ID = StationIDE.getFirstChild().getNodeValue();
		//get the name of it too
		NodeList StationName = doc.getElementsByTagName("Name");
		Element StationNameE = (Element)StationName.item(0);
		String StationNameT = StationNameE.getFirstChild().getNodeValue();
		visualApi.GetStation.setText(StationNameT);
		
		
		//returns site id number
		station = ID;
		System.out.print(ID);
		initialscan++;
		return ID;
		}else{
			return station;
		}
	}

	public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {
		GetURL("Svedmyra");
	}

}
