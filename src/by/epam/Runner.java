package by.epam;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import by.epam.bll.XmlUtils;
import by.epam.model.Passenger;
 

public class Runner {
	public static void main(String[] args) {
		List<Passenger> pass;
		
		pass = XmlUtils.readPassengersFromFile("src/passengers.xml");
		for (Passenger passenger : pass) {
			System.out.println(passenger);
		}
		XmlUtils.exportCatalogToXML("src/passengersExpotr.xml", pass);
	}
}
