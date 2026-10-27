package Controller;

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

	// A save method to store the arraylist into the the txt file

	private void save() throws IOException {
		File file = new File(FILE_PATH);
		PrintWriter pw = new PrintWriter(file);

		for (Player p : players) {
			pw.println(p.format());
		}

		pw.close();

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

	// A method to search for a player based their name

	private Player searchForPlayer(String name) {
		name = appMenu.promptName();
		Player searchedPlayer = null;

		for (Player p : players) {
			if (p.getName().equals(name)) {
				searchedPlayer = p;
				break;
			}
		}
		return searchedPlayer;
	}

	/**
	 * Finds the three players with the most wins from the players list. The players
	 * are returned in ranking order: index 0 is 1st place, index 1 is 2nd place,
	 * and index 2 is 3rd place. The original players list is not modified.
	 * 
	 * If there are fewer than three players, the returned list will contain only as
	 * many players as exist. If players are tied, the one that appears earlier in
	 * players is ranked higher.
	 *
	 * @return a new list of up to three players, ordered from most wins to fewest
	 */
	private ArrayList<Player> topPlayers() {
		// new list so we don't corrupt existing one
		ArrayList<Player> remainingPlayers = new ArrayList<Player>(players);
		ArrayList<Player> topThree = new ArrayList<Player>();

		for (int place = 1; place <= 3 && remainingPlayers.size() > 0; place++) {
			Player bestPlayer = playerMostWins(remainingPlayers);
			topThree.add(bestPlayer);
			remainingPlayers.remove(bestPlayer);
		}

		return topThree;
	}

	/**
	 * Finds the player with the most wins in the given list. If multiple players
	 * are tied for the most wins, the one that appears earliest in the list is
	 * returned.
	 *
	 * @return the player with the highest number of wins
	 */
	private Player playerMostWins(ArrayList<Player> playerList) {
		Player bestPlayer = playerList.get(0);

		for (int i = 1; i < playerList.size(); i++) {
			Player currentPlayer = playerList.get(i);

			if (currentPlayer.getNumOfWins() > bestPlayer.getNumOfWins()) {
				bestPlayer = currentPlayer;
			}
		}

		return bestPlayer;
	}

}
