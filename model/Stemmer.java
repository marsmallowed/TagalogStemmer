package model;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Stemmer {
	// article elements
	private ArrayList<String> xmlTitles;
    private ArrayList<String> xmlBodies;
    private ArrayList<String> xmlDates;
	
	private ArrayList<String> unprocessedWords;
	private ArrayList<String> rootWords;
	
	private ProperNounsExtractor namesAndDates = new ProperNounsExtractor();
	
	public Stemmer() {
		xmlTitles = new ArrayList<String>();
		xmlBodies = new ArrayList<String>();
		xmlDates = new ArrayList<String>();
		
		unprocessedWords = new ArrayList<String>();
		rootWords = new ArrayList<String>();
	}
	
	public void getContents(XMLFileContents xmlFile, ExtractedInfo extractedInfo) {
		/** gets XML elements */
		xmlTitles = xmlFile.getTitles();
		xmlBodies = xmlFile.getBodies();
		xmlDates = xmlFile.getDates();
		
		ArrayList<String> notIncluded = new ArrayList<String>();
		
		/** gets names and dates */
		/* PAKIAYOS ITO PARANG AWA NIYO NA HINDI MAKAKUHA NANG MAAYOS*/
		namesAndDates.analyzePeople(xmlFile, extractedInfo);
		for (int i = 0; i < namesAndDates.getPeople().size(); i++)
			notIncluded.add(namesAndDates.getPeople().get(i));
		for (int i = 0; i < namesAndDates.getPlaces().size(); i++)
			notIncluded.add(namesAndDates.getPlaces().get(i));
		for (int i = 0; i < namesAndDates.getDates().size(); i++)
			notIncluded.add(namesAndDates.getDates().get(i));
		
		/** gets only the common nouns (DEPENDENT ON PROPERNOUNSEXTRACTOR) */
		for (int i = 0; i < 1; i++) {
			System.out.println("Body " + i);
			String line = xmlBodies.get(i);
			
			String pattern = "[A-Za-z]*-?'?[A-Za-z]*";
			Pattern r = Pattern.compile(pattern);
			
			Matcher m = r.matcher(line);
			while (m.find( )) {
				if (!notIncluded.contains(m.group())) {
					unprocessedWords.add(m.group());
				}
			}
		}		
	}
	
	public void analyzeWords() {
		// TODO refer to pdf for cases.
		// SPECIAL CASE: Hinagpis
		System.out.println("Unprocessed: " + unprocessedWords.size());
		for (int i = 0; i < unprocessedWords.size(); i++) {
			/** CASE: ikina, pina, pinag, mag, mag-, nag, nag- */
			String pattern = "([Ii])*(ki)*([Pp]i)*[MmNn]a(g)?(pa)?(-)*([a-z]-?){3,}";
			Pattern r = Pattern.compile(pattern);
			Matcher m = r.matcher(unprocessedWords.get(i));
			
			while (m.find( )) {
				System.out.println("Found: " + m.group());
				
			}
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
