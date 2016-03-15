package jolson.michael.skyline.model;

import java.util.HashMap;
import java.util.Map;

public class Player {
	private Map<Card, Integer> hand;
	private int influence;
	private Map<DeckCard, InProgressBuilding> buildings;
	private Map<Role, Integer> embezzlements;
	private Map<Card, Integer> cardsDown;

	public Player() {
		this.hand = new HashMap<>();
		this.influence = 2;
		this.buildings = new HashMap<>();
		this.embezzlements = new HashMap<>();
		Utils.initializeRoleToIntegerMap(this.embezzlements, true);
		this.cardsDown = new HashMap<>();
	}
	
	public Map<Card, Integer> getHand() { return this.hand; }
	public int getInfluence() { return this.influence; }
	public void setInfluence(int influence) { this.influence = influence; }
	public Map<DeckCard, InProgressBuilding> getBuildings() { return this.buildings; }
	public Map<Role, Integer> getEmbezzlements() { return this.embezzlements; }
	public Map<Card, Integer> getCardsDown() { return this.cardsDown; }
	
	public void addCardToHand(Card card) {
		if (this.hand.containsKey(card)) {
			this.hand.put(card, this.hand.get(card) + 1);
		} else {
			this.hand.put(card, 1);
		}
	}
	
	public void removeCardFromHand(Card card) {
		if (!this.hand.containsKey(card) || this.hand.get(card) <= 0) throw new IllegalStateException();
		
		if (this.hand.get(card) == 1) {
			this.hand.remove(card);
		} else {
			this.hand.put(card, this.hand.get(card) - 1);
		}
	}
}
