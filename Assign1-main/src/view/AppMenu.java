package view;

import java.util.ArrayList;
import java.util.Scanner;

import controller.Card;
import model.Player;

public class AppMenu {

	Scanner input;

	public AppMenu() {
		input = new Scanner(System.in);
	}

	public char mainMenu() {
		while (true) {
			System.out.println("Select one of these options:\n");
			System.out.println("\t(P) Play game");
			System.out.println("\t(S) Search");
			System.out.println("\t(E) Exit\n");
			System.out.print("Enter a choice: ");
			String line = input.nextLine().trim();
			if (!line.isEmpty()) {
				char option = Character.toLowerCase(line.charAt(0));
				if (option == 'p' || option == 's' || option == 'e') {
					return option;
				}
			}
			System.out.println("Invalid input. Please try again.");
		}
	}

	public char searchMenu() {
		while (true) {
			System.out.println("Select one of these options:\n");
			System.out.println("\t(T) Top player (Most number of wins)");
			System.out.println("\t(N) Looking for a Name");
			System.out.println("\t(B) Back to Main Menu\n");
			System.out.print("Enter a choice: ");
			String line = input.nextLine().trim();
			if (!line.isEmpty()) {
				char option = Character.toLowerCase(line.charAt(0));
				if (option == 't' || option == 'n' || option == 'b') {
					return option;
				}
			}
			System.out.println("Invalid input. Please try again.");
		}
	}

	public String promptName() {
		System.out.print("What is your name: ");
		return input.nextLine().trim();
	}

	public void showWelcomeBack(String name, int balance) {
		System.out.println("*************************************************************************");
		System.out.println("***   Welcome back " + name.toUpperCase() + " \t\t Your balance is: " + balance + " $   ***");
		System.out.println("*************************************************************************");
	}

	public void showNewPlayer(String name, int balance) {
		System.out.println("*************************************************************************");
		System.out.println("***   Welcome " + name.toUpperCase() + " \t\t Your initial balance is: " + balance + " $   ***");
		System.out.println("*************************************************************************");
	}

	public void showZeroBalance(String name) {
		System.out.println("Sorry " + name + ", your balance is 0 $. You cannot play.");
	}

	public int promptBet(int maxBalance) {
		while (true) {
			System.out.print("How much do you want to bet this round? ");
			if (input.hasNextInt()) {
				int bet = input.nextInt();
				input.nextLine(); // consume newline
				if (bet >= 2 && bet <= maxBalance) {
					return bet;
				} else {
					System.out.println("Invalid bet. Must be at least $2 and cannot exceed your balance ($" + maxBalance + ").");
				}
			} else {
				System.out.println("Please enter a valid number.");
				input.nextLine();
			}
		}
	}

	public int promptHitOrStand() {
		while (true) {
			System.out.println("\nSelect an option:");
			System.out.println("1.Hit");
			System.out.println("2.Stand");
			System.out.print("You choice: ");
			if (input.hasNextInt()) {
				int choice = input.nextInt();
				input.nextLine();
				if (choice == 1 || choice == 2) {
					return choice;
				}
			} else {
				input.nextLine();
			}
			System.out.println("Invalid choice. Enter 1 or 2.");
		}
	}

	public boolean promptContinue() {
		while (true) {
			System.out.print("Do you want to continue(y/n)? ");
			String res = input.nextLine().trim().toLowerCase();
			if (res.equals("y")) return true;
			if (res.equals("n")) return false;
			System.out.println("Please enter 'y' or 'n'.");
		}
	}

	public void displayGameTable(ArrayList<Card> playerCards, ArrayList<Card> dealerCards, boolean hideDealerHoleCard) {
		System.out.println("\n\t- BLACKJACK -");
		System.out.println("+===============================+==============================+");
		System.out.println("| PLAYER\t\t\t| DEALER\t\t       |");
		System.out.println("+===============================+==============================+");
		
		int maxRows = Math.max(playerCards.size(), dealerCards.size());
		for (int i = 0; i < maxRows; i++) {
			String pStr = "";
			String dStr = "";
			
			if (i < playerCards.size()) {
				pStr = playerCards.get(i).toString();
			}
			if (i < dealerCards.size()) {
				if (i == 1 && hideDealerHoleCard) {
					dStr = "Face Down Card";
				} else {
					dStr = dealerCards.get(i).toString();
				}
			}
			
			System.out.printf("| %-29s | %-28s |\n", pStr, dStr);
		}
		System.out.println("+-------------------------------+------------------------------+\n");
	}

	public void showTopPlayers(ArrayList<Player> topPlayers) {
		System.out.println("\n\t- TOP PLAYERS -");
		System.out.println("+===============================+==============+");
		System.out.println("| NAME\t\t\t\t| # WINS       |");
		System.out.println("+===============================+==============+");
		for (Player p : topPlayers) {
			System.out.printf("| %-29s | %-12d |\n", p.getName(), p.getNumOfWins());
		}
		System.out.println("+----------------------------------------------+\n");
		promptEnterKey();
	}

	public void showPlayer(Player player) {
		if (player != null) {
			System.out.println("\n\t- PLAYER INFO -");
			System.out.println("+===============================+==============+==============+");
			System.out.println("| NAME\t\t\t\t| WINS         | BALANCE      |");
			System.out.println("+===============================+==============+==============+");
			System.out.printf("| %-29s | %-12d | %-10d $ |\n", player.getName(), player.getNumOfWins(), player.getBalance());
			System.out.println("+-------------------------------------------------------------+\n");
		} else {
			System.out.println("No records found for that name.");
		}
		promptEnterKey();
	}

	public void promptEnterKey() {
		System.out.print("Press \"Enter\" to continue...");
		input.nextLine();
	}

	public void save() {
		System.out.println("Saving...");
		System.out.println("Done! Please visit us again!");
	}
}
