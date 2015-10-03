package utilities;

import java.io.File;

public final class TextGenerator {
	public static File createFile(String fileName) {
		
		return new File(fileName+".txt");
	}
}
