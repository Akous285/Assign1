package controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import model.Player;
import view.AppMenu;

public class GameManager {

	/*
	 * In this class toy'll need these methods: A constructor A method to load the
	 * txt file into an arraylist (if it exists, so you check if the txt file exists
	 * first) A save method to store the arraylist into the the txt file A method to
	 * search for a player based their name A method to find the top players
	 * Depending on your designing technique you may need and you can add more
	 * methods here
	 */

	public static final String FILE_PATH = "res/CasinoInfo.txt";
	private ArrayList<Player> players;
	AppMenu appMenu;
	BlackjackGame bjg;

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

		if (p == null) {
			String id = appMenu.promptId();
			players.add(new Player(name, id, 0));

		}

		bjg = new BlackjackGame();
		boolean win = bjg.launchGame();
		if (win) {
			for (Player pl : players) {
				if (pl.getName().equals(name)) {
					int num = pl.getNumOfWins();
					pl.setNumOfWins(num + 1);

				}
			}
		}
	}

	// A method to load the txt file into an arraylist (if it exists, so you check
	// if the txt file exists first)
	public void loadAndCheckFile() throws IOException {
		File file = new File(FILE_PATH);
		String currentLine;
		String[] splitLine;

		if (file.exists()) {
			Scanner reader = new Scanner(file);

			while (reader.hasNextLine()) {

				currentLine = reader.nextLine();
				splitLine = currentLine.split(";");
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
		Player searchedPlayer = null;

		for (Player p : players) {
			if (p.getName().equals(name)) {
				searchedPlayer = p;
				break;
			}
		}
		return searchedPlayer;
	}

	// A method to find the top players

	private void topPlayers() {
		// check players with the most wins
		// numOfWins is key here
		for(Player p: players) { // sort through players and stop after you have gotten the player with the most wins
								 // take that player and store them and take them out of the pool for the next iteration
								 // until you have stored three players
			
			if(p.getNumOfWins()) { // need to check their numOfWins and select the top 3
				
			}
		}
	}

	// Depending on your designing technique you may need and you can add more
	// methods here

	private void search() throws IOException {
		char option = appMenu.searchMenu();
		boolean flag = true;

		while (flag) {
			switch (option) {
			case 't':
				topPlayers();
				break;
			case 's':
				String name = appMenu.promptName();
				Player player = searchForPlayer(name);
				appMenu.showPlayer(player);
				break;
			case 'b':
				launchApplication();
				flag = false;
				break;
			}
		}
	}

}
