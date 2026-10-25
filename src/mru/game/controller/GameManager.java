package mru.game.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import mru.game.model.Player;
import mru.game.view.AppMenu;

public class GameManager {
	
	/*
	 * In this class toy'll need these methods: A constructor A method to load the
	 * txt file into an arraylist (if it exists, so you check if the txt file exists
	 * first) A save method to store the arraylist into the the txt file A method to
	 * search for a player based their name A method to find the top players
	 * Depending on your designing technique you may need and you can add more
	 * methods here
	 */

	
	int numOfWins;
	
	
	public static final String FILE_PATH = "res/CasinoInfo.txt";
	ArrayList<Player> players;
	AppMenu appMenu;
	BlackjackGame bg;

	// constructor
	public GameManager() throws Exception {
		players = new ArrayList<>();
		appMenu = new AppMenu();
		loadAndCheckFile();
		launchApplication();
	}

	private void launchApplication() throws IOException {

		boolean flag = true;
		char option;

		while (flag) {
			option = appMenu.mainMenu();
			switch (option) {
			case 'p':
				playGame();
				break;
			case 's':
				search();
				break;
			case 'e':
				save();
				flag = false;
				break;
			}
		}

	}

	private void playGame() {
		String name = appMenu.promptName();
		Player p = searchForPlayer(name);
		
		if(p == null) {
			String id = appMenu.promptId();
			players.add(new Player(name, id, numOfWins));
		}
		
		bg = new BlackjackGame();
		boolean win = bg.launchGame();
		if (win) {
			for (Player p1 : players) {
				if(p1.getName().equals(name)) {
					int num = p1.getNumOfWins();
					p1.setNumOfWins(num+1);
				}
				
			}
		}
		
	}

	// A method to load the txt file into an arraylist (if it exists, so you check
	// if the txt file exists first)
	public void loadAndCheckFile() throws IOException {

		File file = new File(FILE_PATH);
		if (file.exists()) {
			Scanner reader = new Scanner(file);
			while (reader.hasNextLine()) {
				String line = reader.nextLine();
				String[] splitLine = line.split(";");
				Player p = new Player(splitLine[0], splitLine[1], Integer.parseInt(splitLine[2]));
				players.add(p);
			}
			reader.close();
		}
	}

	// A save method to store the arraylist into the the txt file

	private void save() throws IOException {
		File file = new File(FILE_PATH);
		PrintWriter pw = new PrintWriter(file);

		for (Player p : players) {
			pw.println(p.format());
		}

		pw.close();

	}

	// A method to search for a player based their name

	private Player searchForPlayer(String name) {
		String namee = appMenu.promptName();
		Player searchedPlayer = null;
		boolean found = false;
		
		for(Player p: players) {
			if(p.getName().equals(name)) {
				searchedPlayer = p;
				break;
			}
		}
		return searchedPlayer;
	}

	// A method to find the top players

	private void topPlayers() {
		
	}

	// Depending on your designing technique you may need and you can add more
	// methods here
	
	private void search() {
		char option = appMenu.searchMenu();
		
		switch(option) {
		case 't':
			topPlayers();
			break;
		case 's':
			String name = appMenu.promptName();
			Player player = searchForPlayer(name);
			appMenu.showPlayer(player);
			break;
		case 'b':
			break;
		}
	}
	
	

}
