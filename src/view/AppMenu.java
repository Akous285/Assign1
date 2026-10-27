package view;

import java.util.Scanner;

import model.Player;


public class AppMenu {
	
	Scanner input;
	
	public AppMenu() {
		
		input = new Scanner(System.in);
	}
	
	public char mainMenu() {
			
			System.out.println("Select one of these options:\n");
			System.out.println("\t(P) Play game");
			System.out.println("\t(S) Search");
			System.out.println("\t(E) Exit\n");
			System.out.print("Enter a choice: ");
			char option = input.nextLine().toLowerCase().charAt(0);
			
			//ignore first line in the casino.txt file
			input.nextLine();
			
			return option;
	}
	
	public char searchMenu() {
			
			System.out.println("Select one of these options:\n");
			System.out.println("\t(T) Top player (Most number of wins)");
			System.out.println("\t(N) Looking for a Name");
			System.out.println("\t(B) Back to Main Menu\n");
			System.out.print("Enter a choice: ");
			char option = input.nextLine().toLowerCase().charAt(0);
			return option;
	}
	
	public String promptName() {
		
		System.out.print("Enter a Name: ");
		String name  = input.nextLine().trim().toLowerCase();
		return name;
	}
	
	public void showPlayer(Player player) {
		if (player != null)
		System.out.println(player);
		else
			System.out.println("No records found for that name");
	}
	
	public String promptId() {
		
		System.out.print("Enter Your ID here: ");
		String id = input.nextLine().trim();
		return id;
	}

	public int showGameMenu() {
		int guess;
		System.out.print("Please enter a number between 1 and 21: ");
		guess = input.nextInt();
		
		return guess;
		
	}

	public void showGreaterMsg() {
		
		System.out.println("My name is greater than yours!");
	}

	public void showLesserMsg() {
		
		System.out.println("My name is less than yours! ");
		
	}

	public void showCongrats() {
		
		System.out.println("Congrats! You won!");
		
		
	}

	public void showSorryMg(int number) {
		
		System.out.println("Sorry my number was " + number);
		
	}

	public String save() {
		System.out.println("Saving... ");
		System.out.println("Done! Please Visit Us Again!");
		return null;
	}
	
	
}

