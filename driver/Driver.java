package driver;

import java.io.IOException;

import model.ExtractedInfo;
import model.OutputGenerator;
import model.Stemmer;
import model.ProperNounsExtractor;
import model.XMLFileContents;
import utilities.XmlContentParser;
import view.FileChooser;

public class Driver {
	public static void main(String[] args) {
		
		String fileName = new FileChooser().promptUserForFile();
		
		/** Read xml */
		XMLFileContents xmlFile = XmlContentParser.parseXML(fileName);
                //System.out.println(xmlFile.getBodies().get(1));
		
		/** Save data to extracted info */
		ExtractedInfo extractedInfo = new ExtractedInfo();
//		XMLContentAnalyzer contentReader = new XMLContentAnalyzer();
//		contentReader.analyzePeople(xmlFile, extractedInfo);
//        contentReader.analyzeDates(xmlFile, extractedInfo);
//        contentReader.analyzePlaces(xmlFile, extractedInfo);
		
		Stemmer s = new Stemmer();
		s.getContents(xmlFile, extractedInfo);
		s.analyzeWords();
		s.analyzeProcessedWords();
                

                
                /**testings*/
                
		
		/** Create txt file */
		OutputGenerator outputGenerator = new OutputGenerator();
		try {
			outputGenerator.createTxtResultsFromExtractedInfo(extractedInfo);
		} catch (IOException e) {
			System.out.println("error in writing file");
			e.printStackTrace();
		}
	}
}
