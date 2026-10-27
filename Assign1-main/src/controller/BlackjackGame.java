package controller;

import java.util.ArrayList;

import controller.Card;
import controller.CardDeck;
import model.Player;
import view.AppMenu;

public class BlackjackGame {

	private CardDeck cardDeck = new CardDeck();
	private AppMenu ap;
	
	public BlackjackGame(CardDeck cardDeck, AppMenu ap) {
		this.cardDeck = cardDeck;
		this.ap = ap;
	}

	public int playRound(Player player) {
		if (player.getBalance() <= 0) {
			ap.showZeroBalance(player.getName());
			return 0;
		}

		int bet = ap.promptBet(player.getBalance());

		ArrayList<Card> playerCards = new ArrayList<>();
		ArrayList<Card> dealerCards = new ArrayList<>();

		// Deal initial cards: Player gets 2 face up, Dealer gets 1 face up, 1 face down
		playerCards.add(cardDeck.drawCard());
		dealerCards.add(cardDeck.drawCard());
		playerCards.add(cardDeck.drawCard());
		dealerCards.add(cardDeck.drawCard());

		boolean playerNatural = isNatural(playerCards);
		boolean dealerNatural = isNatural(dealerCards);

		ap.displayGameTable(playerCards, dealerCards, true);

		// Check for naturals immediately
		if (playerNatural || dealerNatural) {
			ap.displayGameTable(playerCards, dealerCards, false);
			if (playerNatural && dealerNatural) {
				System.out.println("Both have Blackjack! Stand-off (Tie).");
				return 0; // No balance change
			} else if (playerNatural) {
				int winnings = (int)(bet * 1.5);
				System.out.println("Blackjack! You won $" + winnings);
				player.setNumOfWins(player.getNumOfWins() + 1);
				return winnings;
			} else {
				System.out.println("Dealer has Blackjack! You lost $" + bet);
				return -bet;
			}
		}

		// Player turn
		boolean busted = false;
		while (true) {
			int pScore = calculateScore(playerCards);
			if (pScore > 21) {
				busted = true;
				break;
			}
			if (pScore == 21) {
				break;
			}

			int choice = ap.promptHitOrStand();
			if (choice == 1) { // Hit
				playerCards.add(cardDeck.drawCard());
				ap.displayGameTable(playerCards, dealerCards, true);
			} else {
				break;
			}
		}

		if (busted) {
			ap.displayGameTable(playerCards, dealerCards, false);
			System.out.println("You went bust! You lost $" + bet);
			return -bet;
		}

		// Dealer turn
		System.out.println("Dealer's turn...");
		while (true) {
			int dScore = calculateScore(dealerCards);
			// Dealer must stand on 17 or more (including soft 17 where ace counts as 11)
			if (dScore >= 17) {
				break;
			}
			dealerCards.add(cardDeck.drawCard());
		}

		ap.displayGameTable(playerCards, dealerCards, false);

		int finalPlayerScore = calculateScore(playerCards);
		int finalDealerScore = calculateScore(dealerCards);

		if (finalDealerScore > 21) {
			System.out.println("Dealer went bust! You won $" + bet);
			player.setNumOfWins(player.getNumOfWins() + 1);
			return bet;
		} else if (finalPlayerScore > finalDealerScore) {
			System.out.println("You won $" + bet);
			player.setNumOfWins(player.getNumOfWins() + 1);
			return bet;
		} else if (finalPlayerScore < finalDealerScore) {
			System.out.println("You lost $" + bet);
			return -bet;
		} else {
			System.out.println("It's a tie (Stand-off)! Bet returned.");
			return 0;
		}
	}

	private boolean isNatural(ArrayList<Card> hand) {
		if (hand.size() != 2) return false;
		boolean hasAce = false;
		boolean hasTen = false;
		for (Card c : hand) {
			if (c.getRank() == 1) hasAce = true;
			if (c.getRank() >= 10 && c.getRank() <= 13) hasTen = true;
		}
		return hasAce && hasTen;
	}

	public int calculateScore(ArrayList<Card> hand) {
		int total = 0;
		int aces = 0;

		for (Card c : hand) {
			int rank = c.getRank();
			if (rank == 1) {
				aces++;
				total += 11;
			} else if (rank >= 10 && rank <= 13) {
				total += 10;
			} else {
				total += rank;
			}
		}

		while (total > 21 && aces > 0) {
			total -= 10;
			aces--;
		}

		return total;
	}
}
