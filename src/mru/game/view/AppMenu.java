package mru.game.view;

import java.util.Scanner;


public class AppMenu {
	
	String in;
	Scanner input = new Scanner(System.in);
	
	public void mainMenu() {
		
		
		
		
		
			
			System.out.println("Select one of these options:\n");
			System.out.println("\t(P) Play game");
			System.out.println("\t(S) Search");
			System.out.println("\t(E) Exit\n");
			System.out.println("Enter a choice:");
			input.nextLine();
			

	}
	
	public void searchMenu() {
			
		
			System.out.println("Select one of these options:\n");
			System.out.println("\t(T) Top player (Most number of wins)");
			System.out.println("\t(N) Looking for a Name");
			System.out.println("\t(B) Back to Main Menu\n");
			System.out.println("Enter a choice");
			input.nextLine();
		
	}
	
	/**
	 * 
	 *
	 * This class will be used to show the menus and sub menus to the user
	 * It also prompts the user for the inputs and validates them 
	 */

	











}


