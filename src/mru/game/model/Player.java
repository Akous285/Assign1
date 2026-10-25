package mru.game.model;

public class Player {
	
		// this is not my territory rn lol
		// might have to comment all of this out
		private String name;
		private String id;
		private int numOfWins;
		
		public Player(String name, String id, int numOfWins) {
			this.name = name;
			this.id = id;
			this.numOfWins = numOfWins;
		}
		
		public void setName(String name) {
			this.name = name;
		}
		
		public String getName() {
			return name;
		}
		
		public void setId(String id) {
			this.id = id;
		}
		
		public String getId() {
			return id;
		}
		
		public void setNumOfWins(int numOfWins) {
			this.numOfWins = numOfWins;
			
		}
		
		public int getNumOfWins() {
			
			return numOfWins;
		}

		public String toString() {
			return "Name: " + name + "ID: " + id + "Number of Wins: " + numOfWins;
		}
		
		public String format() {
			return name + ";" + id + ";" + numOfWins;
		}
	
}
