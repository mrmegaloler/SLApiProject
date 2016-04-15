package apiTest;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;



public class ReadXML {
	
	public static Document doc; 
	
	public static void XMLGen(String direction) throws ParserConfigurationException, SAXException, IOException {
	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	DocumentBuilder builder = factory.newDocumentBuilder();
	String URLB = new String(
			"http://api.sl.se/api2/realtimedepartures.xml?key=e082a3a9b23b418ebc63266c6c0480bd&timewindow=20&siteid=");
	String URLC = URLB + URLParser.GetURL(direction);
	doc = builder.parse(URLC);
	
	}
	
	public static String timeleft(String direction) throws ParserConfigurationException, SAXException, IOException {
		int n = 0;
		boolean isMetro = false;
		
		NodeList DisplayTime = doc.getElementsByTagName("DisplayTime");
		NodeList transportMode = doc.getElementsByTagName("TransportMode");
		int departureNo = transportMode.getLength();
		NodeList Direction = doc.getElementsByTagName("JourneyDirection");
		NodeList Line = doc.getElementsByTagName("GroupOfLineId");
		Element timeleft = null;
		
		String timeLeft = "x";
		
		do {
			Element transportMode1 = (Element) transportMode.item(n);
			Element Direction1 = (Element) Direction.item(n);
			//System.out.println(transportMode1.getFirstChild().getNodeValue());
			String transportText = "error";
			if(n<departureNo){
			transportText = new String(transportMode1.getFirstChild().getNodeValue());
			String directionText = new String(Direction1.getFirstChild().getNodeValue());
			}
			        
			if (transportText.equals("Metro")) {
				timeleft = (Element) DisplayTime.item(n);
				timeLeft = timeleft.getFirstChild().getNodeValue();
				isMetro = true;
			} else if (transportText != "Metro" && n<=departureNo) {
				n++;
				isMetro = false;
			} else if (n>departureNo){
				timeLeft = ("No subway departures from here.");
				isMetro = true;
			}
		} while (isMetro == false);
		
		n=0;
		
		
		return timeLeft;

	}

	public static String departure(String direction) throws SAXException, IOException, ParserConfigurationException {
		int n = 0;
		boolean isMetro = false;
		
		
		//Get the nodes with tag name "Destination"
		NodeList Destination = doc.getElementsByTagName("Destination");
		Element destination1 = null;
		//gets the nodes with tag name "transportmode"
		NodeList transportMode = doc.getElementsByTagName("TransportMode");
		int departureNo = transportMode.getLength();
		
		NodeList Direction = doc.getElementsByTagName("JourneyDirection");
		
		String timeLeft = "x";
		
		do {
			//creates an element from the nth item in the transportmode nodelist
			Element transportMode1 = (Element) transportMode.item(n);
			Element Direction1 = (Element) Direction.item(n);
			//System.out.println(transportMode1.getFirstChild().getNodeValue());
			String transportText = "Error";
			String text = new String("METRO");
			
			if(n<departureNo){
				transportText = new String(transportMode1.getFirstChild().getNodeValue());
				String directionText = new String(Direction1.getFirstChild().getNodeValue());
				
			}
			
			if (transportText.equals(text)) {
				destination1 = (Element) Destination.item(n);
				timeLeft = destination1.getFirstChild().getNodeValue();
				isMetro = true;
			} else if (transportText != text && n<=departureNo) {
				n++;
				isMetro = false;
			} else if (n>departureNo){
				System.out.print("error 1");
				isMetro = true;
			}
		} while (isMetro == false);
		
		n=0;

		return timeLeft;

	}

	public static void main(String[] args) {

		try {
			departure("Svedmyra");
			timeleft("Svedmyra");

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
