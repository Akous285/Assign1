package controller;

import java.util.Random;

import view.AppMenu;

public class BlackjackGame {
 
		Random rnd;
		AppMenu gm;
		
		
		public BlackjackGame () {
			rnd = new Random();
			gm = new AppMenu();
			launchGame();
			
		}

		private void launchGame() {
			char option;
			int compNumber = rnd.nextInt(10) +1;
			
			for (int i=1; i<+3; i++ );{
				option = gm.searchMenu();
				
				if (compNumber > option ) {
					gm.showGreaterMsg();
				
				} else if (compNumber < option) {
					gm.showLesserMsg();
				} else if (compNumber == option) {
					gm.gotBlackJack();
				} else {
					gm.showCongrats();
					break;
				
				}
			}
		}
}
		
		
		if (!win) {
			ap.showSorryMg(compNum);
			
		}
		
		return win;
		
	}
	
	
	
}
