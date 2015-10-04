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
		
		String found;
		String[] arr;
                String firstTwoChars;
                String nextTwoChars;
		
		System.out.println("Unprocessed: " + unprocessedWords.size());
    
                for (int i = 0; i < unprocessedWords.size(); i++) {
                    /** PREFIX CASES: tag taga tagapag */
                    pattern = "(([Tt])ag(a)?(pag)?(-)*([a-z]-?){3,})";
                    r = Pattern.compile(pattern);
                    m = r.matcher(unprocessedWords.get(i));
                    
                    while (m.find( )) {
                            found = m.group();
                            found.toLowerCase();
                            
                            //Prefix: ka
                            if (found.startsWith("tag") && !found.startsWith("taga") && !found.startsWith("tagapag")) {
					arr = found.split("tag", 2);
					found = arr[1];
                                        unprocessedWords.remove(i);
					processedWords.add(found);
					System.out.println("\nAdded: " + found);
				}
                            else if (found.startsWith("taga")) {
					arr = found.split("taga", 2);
					found = arr[1];
                                        unprocessedWords.remove(i);
					processedWords.add(found);
					System.out.println("\nAdded: " + found);
				}
                            else if (found.startsWith("tagapag")) {
					arr = found.split("tagapag", 2);
					found = arr[1];
                                        unprocessedWords.remove(i);
					processedWords.add(found);
					System.out.println("\nAdded: " + found);
				}
                    }
                }
                
                for (int i = 0; i < unprocessedWords.size(); i++) {
                    /** PREFIX CASES: ka */
                    pattern = "(([Kk])a(-)*([a-z]-?){3,})";
                    r = Pattern.compile(pattern);
                    m = r.matcher(unprocessedWords.get(i));
                    
                    while (m.find( )) {
                            found = m.group();
                            found.toLowerCase();
                            
                            //Prefix: ka
                            if (found.startsWith("ka")) {
					arr = found.split("ka", 2);
					found = arr[1];
                                        unprocessedWords.remove(i);
					processedWords.add(found);
					System.out.println("\nAdded: " + found);
				}
                    }
                }
                
		for (int i = 0; i < unprocessedWords.size(); i++) {
			/** PREFIX/CIRCUMFIX CASES: i, ika, ikina, ipa, ipina, ipag, ipinag ipaki */
			pattern = "(([Ii])([KkPp])(in)?a(g)?(-)*([a-z]-?){3,})";
			r = Pattern.compile(pattern);
			m = r.matcher(unprocessedWords.get(i));
						
			while (m.find( )) {
				found = m.group();
//				System.out.println("Found: " + found);
				found.toLowerCase();
				
				// Prefix: i
				if (found.startsWith("i") && !found.startsWith("ika") && !found.startsWith("ipa")) {
					arr = found.split("i", 2);
					found = arr[1];
                                        unprocessedWords.remove(i);
					processedWords.add(found);
					System.out.println("\nAdded: " + found);
				}
				// Prefix: ika
				else if (found.startsWith("ika")) {
					arr = found.split("ika", 2);
					found = arr[1];
                                        unprocessedWords.remove(i);
					processedWords.add(found);
					System.out.println("\nAdded: " + found);
				}
				// Prefix: ikina
				else if (found.startsWith("ikina")) {
					arr = found.split("ikina", 2);
					found = arr[1];
                                        unprocessedWords.remove(i);
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
                                        unprocessedWords.remove(i);
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
                                        unprocessedWords.remove(i);
					processedWords.add(found);
					System.out.println("\nAdded: " + found);
				}
				// Prefix: ipaki
				else if (found.startsWith("ipaki")) {
					
                                    arr = found.split("ipinaki", 2);
				
					found = arr[1];
                                        unprocessedWords.remove(i);
					processedWords.add(found);
					System.out.println("\nAdded: " + found);
				}
			}
		}
		
		for (int i = 0; i < unprocessedWords.size(); i++) {
			/** PREFIX CASES: pa, pag, pag-, pam, pang, pang-, pinag, pinag-, pinang, pinang-, pinam, 
			 * 				  ma, mag, mag-, mam, man, mang, mang-, min, 
			 * 				  na, nag, nag-, nam, nan, nang, nang-, nin */
			pattern = "[MmNn](in)*a(g)?(pa)?(-)*([a-z]-?){3,}";
			r = Pattern.compile(pattern);
			m = r.matcher(unprocessedWords.get(i));
			
			while (m.find( )) {
				found = m.group();
//				System.out.println("Found: " + found);
				found.toLowerCase();
				
				// Prefix: pa, pag, pag-
				if (found.startsWith("pa") && !found.startsWith("ng", 2) && !found.startsWith("m", 2)) {
					if (found.startsWith("g", 2)) {
						arr = found.split("pag", 2);
					} else {
						arr = found.split("pa", 2);
					}
					found = arr[1];
					if (found.contains("-"))
						arr = found.split("-", 2);
					found = arr[1];
                                        unprocessedWords.remove(i);
					processedWords.add(found);
					System.out.println("\nAdded: " + found);
				}
				// Prefix: pam, pang, pang-
				else if (found.startsWith("pa")) {
					if (found.startsWith("m", 3)) {
						arr = found.split("pam", 2);
					} else if (found.startsWith("ng", 3)) {
						arr = found.split("pang", 2);
					} else {
						arr = found.split("pa", 2);
					}
					found = arr[1];
					if (found.contains("-"))
						arr = found.split("-", 2);
					found = arr[1];
                                        unprocessedWords.remove(i);
					processedWords.add(found);
					System.out.println("\nAdded: " + found);
				}
				// Prefix/Circumfix: pina, pinag, pinag-, pinam, pinang, pinang-
				else if (found.startsWith("pina")) {
					if (found.startsWith("g", 4)) {
						arr = found.split("pinag", 2);
					} else if (found.startsWith("m", 4)) {
						arr = found.split("pinag", 2);
					} else if (found.startsWith("ng", 4)) {
						arr = found.split("pinang", 2);
					} else {
						arr = found.split("pa", 2);
					}
					found = arr[1];
					if (found.contains("-"))
						arr = found.split("-", 2);
					found = arr[1];
                                        unprocessedWords.remove(i);
					processedWords.add(found);
					System.out.println("\nAdded: " + found);
				}
				// Prefix: ma, mag, mag-
				else if (found.startsWith("ma") && !found.startsWith("ng", 2) && !found.startsWith("m", 2)) {
					if (found.startsWith("g", 2)) {
						arr = found.split("mag", 2);
					} else {
						arr = found.split("ma", 2);
					}
					found = arr[1];
					if (found.contains("-"))
						arr = found.split("-", 2);
					found = arr[1];
                                        unprocessedWords.remove(i);
					processedWords.add(found);
					System.out.println("\nAdded: " + found);
				}
				// Prefix: mam, man, mang, mang-
				else if (found.startsWith("ma")) {
					if (found.startsWith("m", 3)) {
						arr = found.split("mam", 2);
					} else if (found.startsWith("n", 3)) {
						arr = found.split("man", 2);
					} else if (found.startsWith("ng", 3)) {
						arr = found.split("mang", 2);
					} else {
						arr = found.split("ma", 2);
					}
					found = arr[1];
					if (found.contains("-"))
						arr = found.split("-", 2);
					found = arr[1];
                                        unprocessedWords.remove(i);
					processedWords.add(found);
					System.out.println("\nAdded: " + found);
				}
				// Prefix: na, nag, nag-
				else if (found.startsWith("na") && !found.startsWith("ng", 2) && !found.startsWith("m", 2)) {
					if (found.startsWith("g", 2)) {
						arr = found.split("nag", 2);
					} else {
						arr = found.split("na", 2);
					}
					found = arr[1];
					if (found.contains("-"))
						arr = found.split("-", 2);
					found = arr[1];
                                        unprocessedWords.remove(i);
					processedWords.add(found);
					System.out.println("\nAdded: " + found);
				}
				// Prefix: nang, nang-
				else if (found.startsWith("na")) {
					if (found.startsWith("m", 3)) {
						arr = found.split("nam", 2);
					} else if (found.startsWith("n", 3)) {
						arr = found.split("nan", 2);
					} else if (found.startsWith("ng", 3)) {
						arr = found.split("nang", 2);
					} else {
						arr = found.split("na", 2);
					}
					found = arr[1];
					if (found.contains("-"))
						arr = found.split("-", 2);
					found = arr[1];
                                        unprocessedWords.remove(i);
					processedWords.add(found);
					System.out.println("\nAdded: " + found);
				}
				// Prefix/Circumfix: min
				else if (found.startsWith("min")) {
					arr = found.split("min", 2);
					found = arr[1];
                                        unprocessedWords.remove(i);
					processedWords.add(found);
					System.out.println("\nAdded: " + found);
				}
				// Prefix/Circumfix: nin
				else if (found.startsWith("nin")) {
					arr = found.split("nin", 2);
					found = arr[1];
                                        unprocessedWords.remove(i);
					processedWords.add(found);
					System.out.println("\nAdded: " + found);
				}
			}
		}
		
		for (int i = 0; i < unprocessedWords.size(); i++) {
			/** PREFIX CASES: recurring syllables
			 * 	SPECIAL CASE NOT INCLUDED: tatrabaho*/
			pattern = "([A-Za-z]([a-z])?[a-z])\1([a-z]*-?[a-z]*)";
			r = Pattern.compile(pattern);
			m = r.matcher(unprocessedWords.get(i));
			
			while (m.find( )) {
				//found = m.group(1);
                                found = m.group();
				System.out.println("Found: " + found);
				found.toLowerCase();
				
				// TODO fix case
                                firstTwoChars = found.substring(0, 2);
                                nextTwoChars = found.substring(2, 4);
                                
                                if(firstTwoChars.equals(nextTwoChars))
                                {
                                arr = found.split(firstTwoChars, 2);
                                found = arr[1];
                                unprocessedWords.remove(i);
                                processedWords.add(found);
                                System.out.println("\nAdded: " + found);
                                }
			}
		}
                /** START OF SUFFIXES*/
                    for (int i = 0; i < unprocessedWords.size(); i++) {
                    /** Suffix CASES: an */
                    pattern = "(([a-z]*)an)";
                    r = Pattern.compile(pattern);
                    m = r.matcher(unprocessedWords.get(i));
                    
                    while (m.find( )) {
                            found = m.group();
                            found = "kainan";
                            found.toLowerCase();
                            
                            //Suffix: an
                            if (found.endsWith("an")) {
					found = found.substring(0, found.length() - 2);
					//found = arr[1];
                                        unprocessedWords.remove(i);
					processedWords.add(found);
					System.out.println("\nAdded: " + found);
				}
                    }
                }
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
