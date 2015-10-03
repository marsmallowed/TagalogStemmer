package model;

import java.util.ArrayList;

public class ExtractedInfo {
	
	private ArrayList<String> persons;
	private ArrayList<String> places;
	private ArrayList<String> dates;
	
	public ExtractedInfo() {
		persons = new ArrayList<String>();
		places = new ArrayList<String>();
		dates = new ArrayList<String>();
	}
	
	public ArrayList<String> getPersons() {
		return persons;
	}
	public ArrayList<String> getPlaces() {
		return places;
	}
	public ArrayList<String> getDates() {
		return dates;
	}
	public void setPersons(ArrayList<String> persons) {
		this.persons = persons;
	}
	public void setPlaces(ArrayList<String> places) {
		this.places = places;
	}
	public void setDates(ArrayList<String> dates) {
		this.dates = dates;
	}
	
	
}
