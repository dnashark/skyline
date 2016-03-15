package jolson.michael.skyline.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Game {
	private List<Player> players;
	private Deck deck;
	private int numTrumps;
	private Map<Role, Integer> market;
	private Map<Role, Integer> zonedLocations;
	private Map<Role, Integer> unzonedLocations;
	
	public Game() {
		this.players = new ArrayList<>();
		this.deck = new Deck();
		this.market = new HashMap<>();
		Utils.initializeRoleToIntegerMap(this.market, false);
		this.zonedLocations = new HashMap<>();
		Utils.initializeRoleToIntegerMap(this.zonedLocations, false);
		this.unzonedLocations = new HashMap<>();
		Utils.initializeRoleToIntegerMap(this.unzonedLocations, false);
	}

	public List<Player> getPlayers() {
		return players;
	}

	public Deck getDeck() {
		return deck;
	}

	public int getNumTrumps() {
		return numTrumps;
	}
	
	public void setNumTrumps(int numTrumps) {
		this.numTrumps = numTrumps;
	}

	public Map<Role, Integer> getMarket() {
		return market;
	}

	public Map<Role, Integer> getZonedLocations() {
		return zonedLocations;
	}

	public Map<Role, Integer> getUnzonedLocations() {
		return unzonedLocations;
	}
}
