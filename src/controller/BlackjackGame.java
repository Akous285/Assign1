package controller;

import java.util.Random;

import view.AppMenu;

public class BlackjackGame {

	Random rd;
	AppMenu ap;
	
	public BlackjackGame() {
		rd = new Random();
		ap = new AppMenu();
		launchGame();
	}

	public boolean launchGame() {
		int guess;
		int compNum = rd.nextInt(21) + 1;
		boolean win = false;
		
		
		
		for (int i = 1; i<=3; i++) {
			guess = ap.showGameMenu();
			
			if(compNum > guess) {
				ap.showGreaterMsg();
			} else  if (compNum < guess) {
				ap.showLesserMsg();
				
			} else {
				ap.showCongrats();
				win = true;
				break;
			}
		} 
		
		if (!win) {
			ap.showSorryMg(compNum);
			
		}
		
		return win;
		
	}
	
	
	
}