package model;

import java.util.ArrayList;

public class ExemptedWords {
	private ArrayList<String> exceptions;
	
	public ExemptedWords() {
		exceptions = new ArrayList<String>();
		
		exceptions.add("hinagpis");
		exceptions.add("pakiramdam");
		exceptions.add("kaibigan");
		exceptions.add("kamatis");
		exceptions.add("halaman");
		exceptions.add("kabanata");
		exceptions.add("kabaong");
		exceptions.add("kagawad");
		exceptions.add("kanan");
		exceptions.add("bilang");
		exceptions.add("mayroon");
		exceptions.add("karoon");
		exceptions.add("totoo");
		exceptions.add("titig");
		exceptions.add("taumbayan");
		exceptions.add("madlangbayan");
		exceptions.add("lipunan");
		exceptions.add("namin");
		exceptions.add("manang");
		exceptions.add("kaliwa");
		exceptions.add("kabayo");
	}
	
	public boolean checkExempted(String word) {
		if (exceptions.contains(word))
			return true;
		else return false;
	}
}
