package controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import model.Player;
import view.AppMenu;

public class GameManager {

	public static final String FILE_PATH = "res/CasinoInfo.txt";
	private ArrayList<Player> players;

	AppMenu appMenu;
	CardDeck cardDeck;
	BlackjackGame bjg;

	public GameManager() throws Exception {
		players = new ArrayList<>();
		appMenu = new AppMenu();
		cardDeck = new CardDeck();
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
				appMenu.save();
				flag = false;
				break;
			}
		}
	}

	private void playGame() {
		String name = appMenu.promptName();
		Player p = searchForPlayer(name);

		if (p == null) {
			p = new Player(name, 100, 0);
			players.add(p);
			appMenu.showNewPlayer(name, 100);
		} else {
			if (p.getBalance() <= 0) {
				appMenu.showZeroBalance(p.getName());
				return;
			}
			appMenu.showWelcomeBack(p.getName(), p.getBalance());
		}

		bjg = new BlackjackGame(cardDeck, appMenu);

		boolean continuePlaying = true;
		while (continuePlaying) {
			if (p.getBalance() <= 0) {
				appMenu.showZeroBalance(p.getName());
				break;
			}
			
			int roundResult = bjg.playRound(p);
			p.setBalance(p.getBalance() + roundResult);

			if (p.getBalance() <= 0) {
				p.setBalance(0);
				appMenu.showZeroBalance(p.getName());
				break;
			}

			continuePlaying = appMenu.promptContinue();
		}
	}

	private void search() throws IOException {
		char option = appMenu.searchMenu();
		boolean flag = true;

		while (flag) {
			switch (option) {
			case 't':
				topPlayers();
				flag = false;
				break;
			case 's':
				String name = appMenu.promptName();
				Player player = searchForPlayer(name);
				appMenu.showPlayer(player);
				flag = false;
				break;
			case 'b':
				flag = false;
				break;
			}
			break;
		}
	}

	private void save() throws IOException {
		File file = new File(FILE_PATH);
		if (file.getParentFile() != null) {
			file.getParentFile().mkdirs();
		}
		PrintWriter pw = new PrintWriter(file);

		for (Player p : players) {
			pw.println(p.format());
		}

		pw.close();
	}

	public void loadAndCheckFile() throws IOException {
		File file = new File(FILE_PATH);
		String currentLine;
		String[] splitLine;

		if (file.exists()) {
			Scanner reader = new Scanner(file);

			while (reader.hasNextLine()) {
				currentLine = reader.nextLine().trim();
				if (!currentLine.isEmpty()) {
					splitLine = currentLine.split(",");
					if (splitLine.length >= 3) {
						String name = splitLine[0].trim();
						int balance = Integer.parseInt(splitLine[1].trim());
						int wins = Integer.parseInt(splitLine[2].trim());
						Player p = new Player(name, balance, wins);
						players.add(p);
					}
				}
			}
			reader.close();
		} else {
			file.getParentFile().mkdirs();
			file.createNewFile();
		}
	}

	private Player searchForPlayer(String name) {
		for (Player p : players) {
			if (p.getName().equalsIgnoreCase(name)) {
				return p;
			}
		}
		return null;
	}

	private void topPlayers() {
		if (players.isEmpty()) {
			System.out.println("No players found.");
			appMenu.promptEnterKey();
			return;
		}

		ArrayList<Player> sorted = new ArrayList<>(players);
		sorted.sort((p1, p2) -> Integer.compare(p2.getNumOfWins(), p1.getNumOfWins()));

		ArrayList<Player> topList = new ArrayList<>();
		int maxWins = sorted.get(0).getNumOfWins();

		for (Player p : sorted) {
			if (p.getNumOfWins() == maxWins) {
				topList.add(p);
			}
		}

		appMenu.showTopPlayers(topList);
	}
}
