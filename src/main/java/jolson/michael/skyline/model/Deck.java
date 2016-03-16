package jolson.michael.skyline.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Deck {
	private List<DeckCard> cards;
	
	public Deck() {
		this.cards = new ArrayList<>();
	}
	
	public void addToTop(DeckCard card) {
		this.cards.add(card);
	}
	
	public void shuffle(Random rnd) {
		for (int i = 0; i < this.cards.size(); i++) {
			int j = i + rnd.nextInt(this.cards.size() - i);
			this.swap(i, j);
		}
	}
	
	public DeckCard draw() {
		if (this.cards.isEmpty()) throw new IllegalStateException();
		
		return this.cards.remove(this.cards.size() - 1);
	}
	
	public int size() { return this.cards.size(); }
	
	private void swap(int i, int j) {
		DeckCard temp = this.cards.get(i);
		this.cards.set(i, this.cards.get(j));
		this.cards.set(j, temp);
	}
}
