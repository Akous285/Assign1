package mru.game.view;

import java.util.Scanner;

import mru.game.model.Player;


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
			System.out.print("Enter a choice:");
			char option = input.nextLine().toLowerCase().charAt(0);
			return option;
	}
	
	public char searchMenu() {
			
			System.out.println("Select one of these options:\n");
			System.out.println("\t(T) Top player (Most number of wins)");
			System.out.println("\t(N) Looking for a Name");
			System.out.println("\t(B) Back to Main Menu\n");
			System.out.print("Enter a choice");
			char option = input.nextLine().toLowerCase().charAt(0);
			return option;
	}
	
	public String promptName() {
		
		System.out.println("Enter a Name: ");
		String name  = input.nextLine();
		return name;
	}
	
	public void showPlayer(Player player) {
		System.out.println(player);
	}
}


