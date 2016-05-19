package apiTest;

	//import relevant packages
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

	//initialize variables
public class ReadXML {
	public static Document doc;
	static int XMLPos = 0;
	static int XMLPosR = 0;
	static int XMLPosBus = 0;
	static int DirectionJM = 1;
	static String journeydM = "2";
	static int DirectionJB = 1;
	static String journeydB = "2";
	static String directionText = null;
	static String lineText = null;
	
	//Sets up the document builder which will handle the xml file downloaded from sl

	public static void XMLGen(String direction) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		// please dont steal my api key smile emoticon
		//Creates the URL appropiate for station searched for
		String URLB = new String(
				"http://api.sl.se/api2/realtimedepartures.xml?key=e082a3a9b23b418ebc63266c6c0480bd&timewindow=20&siteid= ");
		String URLC = URLB + URLParser.GetURL(direction);
		//create the document
		doc = builder.parse(URLC);
	}
	
	//fetches time left for departure on green line
	public static String timeleft(String direction) throws ParserConfigurationException, SAXException, IOException {
		int n = 0;
		boolean isMetro = false;
		//Creates nodelist with nodes named DisplayTime, TransportMode from the document doc
		NodeList DisplayTime = doc.getElementsByTagName("DisplayTime");
		NodeList transportMode = doc.getElementsByTagName("TransportMode");
		//get length of the list so we dont go over and get errors when searching for ndoes
		int departureNo = transportMode.getLength();
		//More nodelists
		NodeList Direction = doc.getElementsByTagName("JourneyDirection");
		NodeList Line = doc.getElementsByTagName("GroupOfLineId");
		Element timeleft = null;
		//default result in case it doesnt find an entry
		String timeLeft = "x";
		do {
			//Creates elements from entry N in the nodelist
			Element transportMode1 = (Element) transportMode.item(n);
			Element Direction1 = (Element) Direction.item(n);
			Element Line1 = (Element) Line.item(n);
			// System.out.println(transportMode1.getFirstChild().getNodeValue());
			String transportText = "error";
			if (n < departureNo) {
				//gets the value of this element and put it in string vairables.
				transportText = new String(transportMode1.getFirstChild().getNodeValue());
				directionText = new String(Direction1.getFirstChild().getNodeValue());
				lineText = new String(Line1.getFirstChild().getNodeValue());
			}
			//loop through the value of elements until it finds one matching prerequisites
			if (transportText.equals("METRO") && directionText.equals(journeydM) && lineText.equals("1")) {
				timeleft = (Element) DisplayTime.item(n + XMLPos);
				timeLeft = timeleft.getFirstChild().getNodeValue();
				//if found then we exit the loop
				isMetro = true;
			} else if (!transportText.equals("Metro") && n <= departureNo) {
				n++;
				isMetro = false;
			} else if (n > departureNo) {
				timeLeft = ("No subway departures from here.");
				isMetro = true;
			}
		} while (isMetro == false);
		n = 0;
		return timeLeft;
	}

	public static String timeleftRed(String direction) throws ParserConfigurationException, SAXException, IOException {
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
			Element Line1 = (Element) Line.item(n);
			String transportText = "error";

			if (n < departureNo) {
				transportText = new String(transportMode1.getFirstChild().getNodeValue());
				if (transportText.equals("METRO")) {
					directionText = new String(Direction1.getFirstChild().getNodeValue());
					lineText = new String(Line1.getFirstChild().getNodeValue());
				}
			}
			if (transportText.equals("METRO") && directionText.equals(journeydM) && lineText.equals("2")) {
				timeleft = (Element) DisplayTime.item(n + XMLPos);
				timeLeft = timeleft.getFirstChild().getNodeValue();
				isMetro = true;
			} else if (!transportText.equals("METRO") && n <= departureNo) {
				n++;
				isMetro = false;
			} else if (!lineText.equals("2") && n <= departureNo) {
				n++;
				isMetro = false;
			} else if (n > departureNo) {
				timeLeft = ("No subway departures from here.");
				isMetro = true;
			}
		} while (isMetro == false);
		n = 0;
		return timeLeft;
	}

	public static String departure(String direction) throws SAXException, IOException, ParserConfigurationException {
		int n = 0;
		boolean isMetro = false;
		// Get the nodes with tag name "Destination"
		NodeList Destination = doc.getElementsByTagName("Destination");
		Element destination1 = null;
		// gets the nodes with tag name "transportmode"
		NodeList transportMode = doc.getElementsByTagName("TransportMode");
		int departureNo = transportMode.getLength();
		NodeList Line = doc.getElementsByTagName("GroupOfLineId");
		NodeList Direction = doc.getElementsByTagName("JourneyDirection");
		String timeLeft = "x";
		do {
			// creates an element from the nth item in the transportmode
			// nodelist
			Element transportMode1 = (Element) transportMode.item(n);
			Element Direction1 = (Element) Direction.item(n);
			// System.out.println(transportMode1.getFirstChild().getNodeValue());
			String transportText = "Error";
			String text = new String("METRO");
			Element Line1 = (Element) Line.item(n);
			if (n < departureNo) {
				transportText = new String(transportMode1.getFirstChild().getNodeValue());
				directionText = new String(Direction1.getFirstChild().getNodeValue());
				lineText = new String(Line1.getFirstChild().getNodeValue());
			}
			if (transportText.equals(text) && directionText.equals(journeydM) && lineText.equals("1")) {
				destination1 = (Element) Destination.item(n + XMLPos);
				timeLeft = destination1.getFirstChild().getNodeValue();
				journeydM = String.valueOf(DirectionJM);
				isMetro = true;
			} else if (transportText != text && n <= departureNo || !lineText.equals("1") && n <= departureNo) {
				n++;
				isMetro = false;
			} else if (n > departureNo) {
				System.out.print("error 1");
				isMetro = true;
			}
		} while (isMetro == false);
		XMLPos = n + 1;
		n = 0;
		return timeLeft;
	}

	public static String departureRed(String direction) throws SAXException, IOException, ParserConfigurationException {
		int n = 0;
		boolean isMetro = false;
		// Get the nodes with tag name "Destination"
		NodeList Destination = doc.getElementsByTagName("Destination");
		Element destination1 = null;
		// gets the nodes with tag name "transportmode"
		NodeList transportMode = doc.getElementsByTagName("TransportMode");
		int departureNo = transportMode.getLength();
		NodeList Line = doc.getElementsByTagName("GroupOfLineId");
		NodeList Direction = doc.getElementsByTagName("JourneyDirection");
		String timeLeft = "x";
		do {
			// creates an element from the nth item in the transportmode
			// nodelist
			Element transportMode1 = (Element) transportMode.item(n);
			Element Direction1 = (Element) Direction.item(n);
			// System.out.println(transportMode1.getFirstChild().getNodeValue());
			String transportText = "Error";
			String text = new String("METRO");
			Element Line1 = (Element) Line.item(n);
			if (n < departureNo) {
				transportText = new String(transportMode1.getFirstChild().getNodeValue());
				if (transportText.equals("METRO")) {
					directionText = new String(Direction1.getFirstChild().getNodeValue());
					lineText = new String(Line1.getFirstChild().getNodeValue());
				}
			}
			if (transportText.equals(text) && directionText.equals(journeydM) && lineText.equals("2")) {
				destination1 = (Element) Destination.item(n + XMLPos);
				timeLeft = destination1.getFirstChild().getNodeValue();
				journeydM = String.valueOf(DirectionJM);
				isMetro = true;
			} else if (!transportText.equals("METRO") && n <= departureNo) {
				n++;
				isMetro = false;
			} else if (!lineText.equals("2") && n <= departureNo) {
				n++;
				isMetro = false;
			} else if (n > departureNo) {
				System.out.print("error 1");
				isMetro = true;
			}
		} while (isMetro == false);
		XMLPosR = n + 1;
		n = 0;
		return timeLeft;
	}

	public static String timeleftBus(String direction) throws ParserConfigurationException, SAXException, IOException {
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
			Element transportMode1 = (Element) transportMode.item(n + XMLPosBus);
			Element Direction1 = (Element) Direction.item(n + XMLPosBus);
			// System.out.println(transportMode1.getFirstChild().getNodeValue());
			String transportText = "error";
			if ((n + XMLPosBus) < departureNo) {
				transportText = new String(transportMode1.getFirstChild().getNodeValue());
				directionText = new String(Direction1.getFirstChild().getNodeValue());
			}
			if (transportText.equals("BUS")) {
				timeleft = (Element) DisplayTime.item(n + XMLPosBus);
				timeLeft = timeleft.getFirstChild().getNodeValue();
				isMetro = true;
			} else if (!transportText.equals("BUS") && n <= departureNo) {
				n++;
				isMetro = false;
			} else if (n > departureNo) {
				timeLeft = ("No bus departures from here.");
				isMetro = true;
			}
		} while (isMetro == false);
		n = 0;
		return timeLeft;
	}

	public static String departureBus(String direction) throws SAXException, IOException, ParserConfigurationException {
		int n = 0;
		boolean isMetro = false;
		// Get the nodes with tag name "Destination"
		NodeList Destination = doc.getElementsByTagName("Destination");
		Element destination1 = null;
		// gets the nodes with tag name "transportmode"
		NodeList transportMode = doc.getElementsByTagName("TransportMode");
		int departureNo = transportMode.getLength();
		NodeList Direction = doc.getElementsByTagName("JourneyDirection");
		String timeLeft = "x";
		do {
			// creates an element from the nth item in the transportmode
			// nodelist
			Element transportMode1 = (Element) transportMode.item(n + XMLPosBus);
			Element Direction1 = (Element) Direction.item(n + XMLPosBus);
			// System.out.println(transportMode1.getFirstChild().getNodeValue());
			String transportText = "Error";
			String text = new String("BUS");
			if ((n + XMLPosBus) < departureNo) {
				transportText = new String(transportMode1.getFirstChild().getNodeValue());
				directionText = new String(Direction1.getFirstChild().getNodeValue());
			}
			if (transportText.equals(text)) {
				destination1 = (Element) Destination.item(n + XMLPosBus);
				timeLeft = destination1.getFirstChild().getNodeValue();
				isMetro = true;
			} else if (transportText != text && n <= departureNo) {
				n++;
				isMetro = false;
			} else if (n > departureNo) {
				System.out.print("error 1");
				isMetro = true;
			}
		} while (isMetro == false);
		XMLPosBus = n + 1;
		n = 0;
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