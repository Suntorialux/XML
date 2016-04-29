package by.epam.bll;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import by.epam.model.Passenger;

public class XmlUtils {
	
	private static final String XSI_NAMESPACE ="http://www.w3.org/2001/XMLSchema-instance";
	private static final String TNS_NAMESPACE ="http://www.example.org/passengers";
	private static final String SHEME_LOCATION ="passengers.xsd";
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
	
	public static void exportCatalogToXML(String fileName, List<Passenger> passenger) {
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = null;
		try {
			documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document = documentBuilder.newDocument();
			Element rootElement = document.createElement("passengers");
			rootElement.setAttribute("xmlns:xsi",XSI_NAMESPACE);
			rootElement.setAttribute("xmlns:tns",TNS_NAMESPACE);
			rootElement.setAttribute("xsi:noNamespaceSchemaLocation",SHEME_LOCATION);
			
			document.appendChild(rootElement);
			for (int i = 0; i < passenger.size();i++){
				Element town = document.createElement("town");
				Element street = document.createElement("street");
				Element homeNum = document.createElement("home-number");
				town.appendChild(document.createTextNode(passenger.get(i).getTown()));
				street.appendChild(document.createTextNode(passenger.get(i).getStreet()));
				homeNum.appendChild(document.createTextNode(Integer.toString(passenger.get(i).getHomeNumber())));
				Element adress = document.createElement("adress");
				adress.appendChild(town);
				adress.appendChild(street);
				adress.appendChild(homeNum);
				Element firstName = document.createElement("firstName");
				Element lastName = document.createElement("lastName");
				Element passport_number = document.createElement("passport-number");
				firstName.appendChild(document.createTextNode(passenger.get(i).getFirstName()));
				lastName.appendChild(document.createTextNode(passenger.get(i).getLastName()));
				passport_number.appendChild(document.createTextNode(passenger.get(i).getPassportNumber()));
				Element passengers_info = document.createElement("passengers-info");
				passengers_info.appendChild(firstName);
				passengers_info.appendChild(lastName);
				passengers_info.appendChild(adress);
				passengers_info.appendChild(passport_number);
				Element origin = document.createElement("origin");
				Element destination = document.createElement("destination");
				origin.setAttribute("date",passenger.get(i).getOriginDate());
				origin.setAttribute("time", passenger.get(i).getOriginTime());
				origin.setAttribute("town", passenger.get(i).getOriginTown());
				destination.setAttribute("date", passenger.get(i).getDestinationDate());
				destination.setAttribute("time", passenger.get(i).getDestinationTime());
				destination.setAttribute("town", passenger.get(i).getDestinationTown());
				Element pass = document.createElement("passenger");
				pass.appendChild(passengers_info);
				pass.appendChild(origin);
				pass.appendChild(destination);
				pass.setAttribute("id", Integer.toString(passenger.get(i).getId()));
				rootElement.appendChild(pass);
	
			}
			exportToXml(rootElement,document,fileName);
		} catch (ParserConfigurationException | IOException | TransformerException e) {
			e.printStackTrace();
		} 

	}
	
	private static void exportToXml(Element root,Document document,String fileName) throws IOException, TransformerException{
		TransformerFactory transformerFactory = TransformerFactory. newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(document);
		StreamResult result = new StreamResult(new FileWriter(fileName));
		transformer.transform(source, result);
	}
}
