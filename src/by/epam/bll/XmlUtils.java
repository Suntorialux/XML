package by.epam.bll;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import by.epam.model.Passenger;

public class XmlUtils {

	public static List<Passenger> readPassengersFromFile(String fileName) {
		List<Passenger> passengers = new ArrayList<>();
		Document doc = null;
		DocumentBuilder docBuilder;
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			docBuilder = dbFactory.newDocumentBuilder();
			doc = docBuilder.parse(fileName);
			Element root = doc.getDocumentElement();
			NodeList nList = root.getElementsByTagName("passenger");
			
			for (int i = 0; i < nList.getLength(); i++){
				Element passangerElement = (Element) nList.item(i);
				Passenger passenger = buildPassenger(passangerElement);
				passengers.add(passenger);
			}
			
		} catch (IOException e) {
			System. err.println("File error or I/O error: " + e);
		}catch (ParserConfigurationException e) {
			System. err.println("Parsing failure: " + e);
		} catch (SAXException e) {
			System. err.println("SAX failure: " + e);
		}
		return passengers;
		
	}
	
	private static Passenger buildPassenger(Element passangerElement){
		Passenger passenger = new Passenger();
		
		passenger.setId(Integer.parseInt(passangerElement.getAttribute("id")));
		Element origin = (Element) passangerElement.getElementsByTagName("origin").item(0);
		passenger.setOriginDate(origin.getAttribute("date"));
		passenger.setOriginTime(origin.getAttribute("time"));
		passenger.setOriginTown(origin.getAttribute("town"));
		passenger.setDestinationTown(origin.getAttribute("town"));
		Element destination = (Element) passangerElement.getElementsByTagName("destination").item(0);
		Element passengerInfo = (Element) passangerElement.getElementsByTagName("passengers-info").item(0);
		passenger.setDestinationDate(destination.getAttribute("date"));
		passenger.setDestinationTime(destination.getAttribute("time"));
		passenger.setFirstName(getElementTextContent(passengerInfo,"firstName"));
		passenger.setLastName(getElementTextContent(passengerInfo,"lastName"));
		passenger.setPassportNumber(getElementTextContent(passengerInfo,"passport-number"));
		Element adress = (Element) passengerInfo.getElementsByTagName("adress").item(0);
		passenger.setTown(getElementTextContent(adress,"town"));
		passenger.setStreet(getElementTextContent(adress,"street"));
		passenger.setHomeNumber(Integer.parseInt(getElementTextContent(adress,"home-number")));
		return passenger;
	}
	
	private static String getElementTextContent(Element element, String elementName) {
		 NodeList nList = element.getElementsByTagName(elementName);
		 Node node = nList.item(0);
		 String text = node.getTextContent();
		 return text;
	}
}
