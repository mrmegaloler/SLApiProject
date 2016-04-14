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
	public static String timeleft(String direction) throws ParserConfigurationException, SAXException, IOException {
		int n = 0;
		boolean isMetro = false;
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		String URLB = new String(
				"http://api.sl.se/api2/realtimedepartures.xml?key=e082a3a9b23b418ebc63266c6c0480bd&timewindow=20&siteid=");
		String URLC = URLB + URLParser.GetURL(direction);
		Document doc = builder.parse(URLC);
		NodeList DisplayTime = doc.getElementsByTagName("DisplayTime");
		NodeList transportMode = doc.getElementsByTagName("TransportMode");
		NodeList Direction = doc.getElementsByTagName("JourneyDirection");
		NodeList Line = doc.getElementsByTagName("GroupOfLineId");
		Element timeleft = null;
		do {
			Element transportMode1 = (Element) transportMode.item(n + visualApi.pos);
			Element Direction1 = (Element) Direction.item(n + visualApi.pos);
			System.out.println(transportMode1.getFirstChild().getNodeValue());
			String transportText = new String(transportMode1.getFirstChild().getNodeValue());
			String directionText = new String(Direction1.getFirstChild().getNodeValue());
			String text = new String("METRO");
			if (transportText.equals(text)) {
				timeleft = (Element) DisplayTime.item(n + visualApi.pos);
				isMetro = true;
			} else if (transportMode1.getFirstChild().getNodeValue() != text) {
				n++;
				isMetro = false;
			}
		} while (isMetro == false);
		
		n=0;
		return timeleft.getFirstChild().getNodeValue();

	}

	public static String departure(String direction) throws SAXException, IOException, ParserConfigurationException {
		int n = 0;
		boolean isMetro = false;
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		String URLB = new String(
				"http://api.sl.se/api2/realtimedepartures.xml?key=e082a3a9b23b418ebc63266c6c0480bd&timewindow=10&siteid=");
		String URLC = URLB + URLParser.GetURL(direction);
		Document doc = builder.parse(URLC);
		NodeList Destination = doc.getElementsByTagName("Destination");
		Element destination1 = null;
		NodeList transportMode = doc.getElementsByTagName("TransportMode");
		
		do {
			Element transportMode1 = (Element) transportMode.item(n + visualApi.pos);
			System.out.println(transportMode1.getFirstChild().getNodeValue());
			String transportText = new String(transportMode1.getFirstChild().getNodeValue());
			String text = new String("METRO");
			if (transportText.equals(text)) {
				destination1 = (Element) Destination.item(n + visualApi.pos);
				isMetro = true;
			} else if (transportMode1.getFirstChild().getNodeValue() != text) {
				n++;
				isMetro = false;
			}
		} while (isMetro == false);
		
		n=0;

		return destination1.getFirstChild().getNodeValue();

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
