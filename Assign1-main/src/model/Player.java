package model;

public class Player {

	private String name;
	private int balance;
	private int numOfWins;

	public Player(String name, int balance, int numOfWins) {
		this.name = name;
		this.balance = balance;
		this.numOfWins = numOfWins;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public int getBalance() {
		return balance;
	}

	public void setNumOfWins(int numOfWins) {
		this.numOfWins = numOfWins;
	}

	public int getNumOfWins() {
		return numOfWins;
	}

	public String toString() {
		return "NAME\t\t| # WINS\t| BALANCE\n" + name + "\t\t| " + numOfWins + "\t\t| " + balance + " $";
	}

	public String format() {
		return name + "," + balance + "," + numOfWins;
	}
}
