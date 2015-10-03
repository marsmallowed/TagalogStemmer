package utilities;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import model.XMLFileContents;

public final class XmlContentParser {
	private static ArrayList<String> titles;
	private static ArrayList<String> bodies;
	private static ArrayList<String> dates;
	
	public static XMLFileContents parseXML(String fileName) {
		
		XMLFileContents xmlContents = new XMLFileContents();
		
		try {
			File fXmlFile = new File(fileName);
//			File fXmlFile = new File("src\\News and Opinion Corpus - Ralph Regalado\\Opinyon\\2001\\April.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			
			
			titles = new ArrayList<String>();
			bodies = new ArrayList<String>();
			dates = new ArrayList<String>();
			
			doc.getDocumentElement().normalize();

//			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
					
			NodeList nList = doc.getElementsByTagName("article");
			
//			System.out.println("----------------------------");

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);
						
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;

					titles.add(eElement.getElementsByTagName("title").item(0).getTextContent());
					dates.add(eElement.getElementsByTagName("month").item(0).getTextContent() + " " +
							eElement.getElementsByTagName("day").item(0).getTextContent() + ", " +
							eElement.getElementsByTagName("year").item(0).getTextContent());
					bodies.add(eElement.getElementsByTagName("body").item(0).getTextContent());
				}
			}
			
			xmlContents.setTitles(titles);
			xmlContents.setDates(dates);
			xmlContents.setBodies(bodies);
			
		} catch (Exception e) {
//		   	e.printStackTrace();
			xmlContents.setTitles(new ArrayList<String>());
			xmlContents.setDates(new ArrayList<String>());
			xmlContents.setBodies(new ArrayList<String>());
			System.out.println("problem in reading file men");
	    }
		
		return xmlContents;
		
	}
}
