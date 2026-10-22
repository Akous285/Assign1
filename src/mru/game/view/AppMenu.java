package mru.game.view;

import java.util.Scanner;


public class AppMenu {
	
	Scanner input;
	
	public AppMenu() {
		
		input = new Scanner(System.in);
	}
	
	public void mainMenu() {
			
			System.out.println("Select one of these options:\n");
			System.out.println("\t(P) Play game");
			System.out.println("\t(S) Search");
			System.out.println("\t(E) Exit\n");
			System.out.print("Enter a choice:");
			
	}
	
	public void searchMenu() {
			
			System.out.println("Select one of these options:\n");
			System.out.println("\t(T) Top player (Most number of wins)");
			System.out.println("\t(N) Looking for a Name");
			System.out.println("\t(B) Back to Main Menu\n");
			System.out.print("Enter a choice");
			
	}
	
	public String promptName() {
		
		System.out.println("Enter a Name: ");
		String name  = input.nextLine();
		return name;
	}
	
	
	











}


