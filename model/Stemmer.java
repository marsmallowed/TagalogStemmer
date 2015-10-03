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
	private ArrayList<String> processedWords;
	private ArrayList<String> finalList;	
	private ProperNounsExtractor namesAndDates = new ProperNounsExtractor();
	
	public Stemmer() {
		xmlTitles = new ArrayList<String>();
		xmlBodies = new ArrayList<String>();
		xmlDates = new ArrayList<String>();
		
		unprocessedWords = new ArrayList<String>();
		processedWords = new ArrayList<String>();
		finalList = new ArrayList<String>();
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
		String pattern;
		Pattern r;
		Matcher m;
		System.out.println("Unprocessed: " + unprocessedWords.size());
		for (int i = 0; i < unprocessedWords.size(); i++) {
			/** PREFIX/CIRCUMFIX CASES: i, ika, ikina, ipa, ipina, ipag, ipinag */
			pattern = "(([Ii])([KkPp])(in)?a(g)?(-)*([a-z]-?){3,})";
			r = Pattern.compile(pattern);
			m = r.matcher(unprocessedWords.get(i));
			
			String found;
			String[] arr;
			while (m.find( )) {
				found = m.group();
				System.out.println("Found: " + found);
				found.toLowerCase();
				if (found.startsWith("i"))
				// Prefix: i
				if (found.startsWith("i") && !found.startsWith("ika") && !found.startsWith("ipa")) {
					arr = found.split("i", 2);
					found = arr[1];
					processedWords.add(found);
					System.out.println("\nAdded: " + found);
				}
				// Prefix: ika
				else if (found.startsWith("ika")) {
					arr = found.split("ika", 2);
					found = arr[1];
					processedWords.add(found);
					System.out.println("\nAdded: " + found);
				}
				// Prefix: ikina
				else if (found.startsWith("ikina")) {
					arr = found.split("ikina", 2);
					found = arr[1];
					processedWords.add(found);
					System.out.println("\nAdded: " + found);
				}
				// Prefix: ipa
				else if (found.startsWith("ipa")) {
					if (found.startsWith("g", 3)) {
						arr = found.split("ipag", 2);
					} else {
						arr = found.split("ipa", 2);
					}
					found = arr[1];
					processedWords.add(found);
					System.out.println("\nAdded: " + found);
				}
				// Prefix: ipina
				else if (found.startsWith("ipina")) {
					if (found.startsWith("g", 3)) {
						arr = found.split("ipinag", 2);
					} else {
						arr = found.split("ipina", 2);
					}
					found = arr[1];
					processedWords.add(found);
					System.out.println("\nAdded: " + found);
				}
			}
			
			/** PREFIX CASES: pag, mag, mag-, nag, nag- */
			pattern = "[MmNn](in)*a(g)?(pa)?(-)*([a-z]-?){3,}";
			r = Pattern.compile(pattern);
			m = r.matcher(unprocessedWords.get(i));
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
