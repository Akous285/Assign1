package controller;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;


public class GameManager {
	
	/* In this class toy'll need these methods:
	 * A constructor
	 * A method to load the txt file into an arraylist (if it exists, so you check if the txt file exists first)
	 * A save method to store the arraylist into the the txt file 
	 * A method to search for a player based their name
	 * A method to find the top players
	 * Depending on your designing technique you may need and you can add more methods here 
	 */
	
	public static final String DATA_FOLDER = "res/CasinoInfo.txt";
	
	
	// constructor
	public GameManager() {
		
	}
	
	// A method to load the txt file into an arraylist (if it exists, so you check if the txt file exists first)
	public ArrayList<String[]> loadAndCheckFile(String fileName) throws FileNotFoundException {
		ArrayList<String[]> players = new ArrayList<String[]>();
		//file checking and validation
		File file = new File(fileName);
		file.exists();
		file.canRead();
		
		//file setup for scanner named reader
		FileReader fr = new FileReader(file);		
		Scanner reader = new Scanner(fr);
		
		while(reader.hasNext()) {
			String line = reader.nextLine();
			// trimming whitespace and splitting by comma
			String[] splitLine = line.trim().split(",");
			players.add(splitLine);
		}
		reader.close();
		
		return players;
	}
	
	// A save method to store the arraylist into the the txt file 
	
	public void save(ArrayList<String[]> players) throws FileNotFoundException {
		// grab arraylist and store into text file
		PrintWriter p = new PrintWriter(DATA_FOLDER);
		
		
		
	}
	
	// A method to search for a player based their name
	
	public void searchForPlayer() {
		
	}
	
	// A method to find the top players
	
	public void topPlayers() {
		
		
	}
	
	// Depending on your designing technique you may need and you can add more methods here 
	
//	public void gitCheck() {
//		
//	}
}
