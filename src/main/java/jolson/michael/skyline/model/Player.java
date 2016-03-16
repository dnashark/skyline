package jolson.michael.skyline.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Player {
	private List<Card> hand;
	private int influence;
	private Map<DeckCard, InProgressBuilding> buildings;
	private Map<Role, Integer> embezzlements;
	private List<Card> cardsDown;

	public Player() {
		this.hand = new ArrayList<>();
		this.influence = 2;
		this.buildings = new HashMap<>();
		this.embezzlements = new HashMap<>();
		Utils.initializeRoleToIntegerMap(this.embezzlements, true);
		this.cardsDown = new ArrayList<>();
	}
	
	public List<Card> getHand() { return this.hand; }
	public int getInfluence() { return this.influence; }
	public void setInfluence(int influence) { this.influence = influence; }
	public Map<DeckCard, InProgressBuilding> getBuildings() { return this.buildings; }
	public Map<Role, Integer> getEmbezzlements() { return this.embezzlements; }
	public List<Card> getCardsDown() { return this.cardsDown; }
}
