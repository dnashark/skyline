package jolson.michael.skyline.controller;

import java.util.List;
import java.util.Map;
import java.util.Random;

import jolson.michael.skyline.model.ActionSegment;
import jolson.michael.skyline.model.Card;
import jolson.michael.skyline.model.ReactSegment;
import jolson.michael.skyline.model.DeckCard;
import jolson.michael.skyline.model.Game;
import jolson.michael.skyline.model.InitiateSegment;
import jolson.michael.skyline.model.Player;
import jolson.michael.skyline.model.Role;
import jolson.michael.skyline.model.Segment;
import jolson.michael.skyline.model.Utils;

public class Controller {
	private Game game;
	
	public void intializeGame(int numPlayers, Random random) {
		if (numPlayers < 2 || numPlayers > 5) throw new IllegalArgumentException();
		if (random == null) throw new NullPointerException();
		
		this.game = new Game();
		this.initializeDeck(random);
		this.initializeTrumps(numPlayers);
		this.initializeLocations(numPlayers);
		this.initializePlayers(numPlayers);
		this.initializeGameState(numPlayers, random);
	}
	
	private void initializeDeck(Random random) {
		for (int i = 0; i < 20; i++) {
			this.game.getDeck().addToTop(DeckCard.ACCOUNTANT);
			this.game.getDeck().addToTop(DeckCard.BUILDER);
			this.game.getDeck().addToTop(DeckCard.CONTRACTOR);
			this.game.getDeck().addToTop(DeckCard.EXECUTIVE);
			this.game.getDeck().addToTop(DeckCard.INVESTOR);
			this.game.getDeck().addToTop(DeckCard.LAWYER);
		}
		
		this.game.getDeck().shuffle(random);;
	}
	
	private void initializeTrumps(int numPlayers) {
		this.game.setNumTrumps(numPlayers);
	}
	
	private void initializeLocations(int numPlayers) {
		final int numLocationsOfType = 6;
		
		this.game.getZonedLocations().put(Role.ACCOUNTANT, numPlayers);
		this.game.getUnzonedLocations().put(Role.ACCOUNTANT, numLocationsOfType - numPlayers);
		this.game.getZonedLocations().put(Role.BUILDER, numPlayers);
		this.game.getUnzonedLocations().put(Role.BUILDER, numLocationsOfType - numPlayers);
		this.game.getZonedLocations().put(Role.EXECUTIVE, numPlayers);
		this.game.getUnzonedLocations().put(Role.EXECUTIVE, numLocationsOfType - numPlayers);
		this.game.getZonedLocations().put(Role.INVESTOR, numPlayers);
		this.game.getUnzonedLocations().put(Role.INVESTOR, numLocationsOfType - numPlayers);
		this.game.getZonedLocations().put(Role.CONTRACTOR, numPlayers);
		this.game.getUnzonedLocations().put(Role.CONTRACTOR, numLocationsOfType - numPlayers);
		this.game.getZonedLocations().put(Role.LAWYER, numPlayers);
		this.game.getUnzonedLocations().put(Role.LAWYER, numLocationsOfType - numPlayers);
	}
	
	private void initializePlayers(int numPlayers) {
		final int initialHandSize = 5;
		
		for (int i = 0; i < numPlayers; i++) {
			Player player = new Player();
			player.setInfluence(2);
			for (int j = 0; j < initialHandSize; j++) {
				player.getHand().add(this.game.getDeck().draw());
			}
		}
	}
	
	private void initializeGameState(int numPlayers, Random random) {
		int firstPlayerIndex = random.nextInt(numPlayers);
		this.game.setCurrentSegment(new InitiateSegment(firstPlayerIndex, null));
		this.game.setInitiatorIndex(firstPlayerIndex);
	}
	
	public void initiate(int playerIndex, Role role, List<Card> cards) {
		if (role == null || cards == null) {
			throw new NullPointerException();
		}

		if (!this.isValidTimeToInitiate(playerIndex)) {
			throw new IllegalStateException();
		}
		
		if (!this.doCardsAllowRole(role, cards)) {
			throw new IllegalArgumentException();
		}
		
		int currentPlayerIndex = this.game.getCurrentSegment().getActorIndex();
		Player currentPlayer = this.game.getPlayers().get(currentPlayerIndex);
		if (!this.doesPlayerHaveCards(currentPlayer, cards)) {
			throw new IllegalStateException();
		}
		
		for (Card card : cards) {
			currentPlayer.getHand().remove(card);
			currentPlayer.getCardsDown().add(card);
		}
		
		int nextPlayerIndex = this.getNextPlayerIndex(currentPlayerIndex);
		this.game.setCurrentSegment(new ReactSegment(role, nextPlayerIndex, null));
	}
	
	private boolean isValidTimeToInitiate(int playerIndex) {
		return this.game.getCurrentSegment() instanceof InitiateSegment &&
			this.game.getCurrentSegment().getActorIndex() == playerIndex;
	}
	
	private boolean doCardsAllowRole(Role role, List<Card> cards) {
		if (cards.size() == 1) {
			Card card = cards.get(0);
			if (card == Card.Trump) {
				return true;
			} else if (card instanceof DeckCard && ((DeckCard)card).getRole() == role) {
				return true;
			} else {
				return false;
			}
		} else if (cards.size() == 2) {
			Card card1 = cards.get(0);
			Card card2 = cards.get(1);
			
			if (card1 instanceof DeckCard && card2 instanceof DeckCard &&
				((DeckCard)card1).getRole() == ((DeckCard)card2).getRole()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	private boolean doesPlayerHaveCards(Player player, List<Card> cards) {
		List<Card> hand = player.getHand();
		
		Map<Card, Integer> cardsCounts = Utils.getCounts(cards);
		Map<Card, Integer> handCounts = Utils.getCounts(hand);
		
		for (Map.Entry<Card, Integer> entry : cardsCounts.entrySet()) {
			Integer handCount = handCounts.get(entry.getKey());
			if (handCount == null || handCount < entry.getValue()) {
				return false;
			}
		}
		
		return true;
	}
	
	private int getNextPlayerIndex(int currentPlayerIndex) {
		int numPlayers = this.game.getPlayers().size();
		return (currentPlayerIndex + 1) % numPlayers;
	}
	
	public void speculate(int playerIndex, boolean trump) {
		if (!this.isValidTimeToSpeculate(playerIndex)) {
			throw new IllegalStateException();
		}
		
		Player player = this.game.getPlayers().get(playerIndex);
		if (trump) {
			if (this.game.getNumTrumps() == 0) {
				throw new IllegalStateException();
			}
			
			this.game.setNumTrumps(this.game.getNumTrumps() - 1);
			player.getHand().add(Card.Trump);
		} else {
			int numberOfCardsToDraw = Math.max(1, this.getHandRefillSize(player) - player.getHand().size());
			
			for (; numberOfCardsToDraw > 0; numberOfCardsToDraw--) {
				player.getHand().add(this.game.getDeck().draw());
				if (this.game.getDeck().size() == 0) {
					this.game.setCurrentSegment(null);
					return;
				}
			}
		}
		
		int nextPlayerIndex = this.getNextPlayerIndex(playerIndex);
		if (this.game.getCurrentSegment() instanceof InitiateSegment) {
			this.game.setCurrentSegment(new InitiateSegment(nextPlayerIndex, null));
		} else if (this.game.getCurrentSegment() instanceof ReactSegment) {
			Role role = ((ReactSegment)this.game.getCurrentSegment()).getRole();
			if (nextPlayerIndex == this.game.getInitiatorIndex()) {
				this.game.setCurrentSegment(new ActionSegment(role, 1, nextPlayerIndex, null));
			} else {
				this.game.setCurrentSegment(new ReactSegment(role, nextPlayerIndex, null));
			}
		} else {
			// TODO: Separate out exceptions
			throw new IllegalStateException();
		}
	}
	
	private boolean isValidTimeToSpeculate(int playerIndex) {
		Segment currentSegment = this.game.getCurrentSegment();
		return currentSegment.getActorIndex() == playerIndex &&
				(currentSegment instanceof InitiateSegment || currentSegment instanceof ReactSegment);
	}
	
	private int getHandRefillSize(Player player) {
		final int baseHandRefillSize = 5;
		return baseHandRefillSize;
	}
	
	public void react(int playerIndex, List<Card> cards) {
		if (cards == null) {
			throw new NullPointerException();
		}

		if (!this.isValidTimeToReact(playerIndex)) {
			throw new IllegalStateException();
		}
		
		ReactSegment segment = (ReactSegment)this.game.getCurrentSegment();
		
		if (!this.doCardsAllowRole(segment.getRole(), cards)) {
			throw new IllegalArgumentException();
		}
		
		int currentPlayerIndex = this.game.getCurrentSegment().getActorIndex();
		Player currentPlayer = this.game.getPlayers().get(currentPlayerIndex);
		if (!this.doesPlayerHaveCards(currentPlayer, cards)) {
			throw new IllegalStateException();
		}
		
		for (Card card : cards) {
			currentPlayer.getHand().remove(card);
			currentPlayer.getCardsDown().add(card);
		}
		
		int nextPlayerIndex = this.getNextPlayerIndex(currentPlayerIndex);
		if (nextPlayerIndex == this.game.getInitiatorIndex()) {
			this.game.setCurrentSegment(new ActionSegment(segment.getRole(), 1, nextPlayerIndex, null));
		} else {
			this.game.setCurrentSegment(new ReactSegment(segment.getRole(), nextPlayerIndex, null));
		}
	}

	private boolean isValidTimeToReact(int playerIndex) {
		return this.game.getCurrentSegment() instanceof ReactSegment &&
			this.game.getCurrentSegment().getActorIndex() == playerIndex;
	}
}
