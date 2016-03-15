package jolson.michael.skyline.controller;

import java.util.Random;

import jolson.michael.skyline.model.DeckCard;
import jolson.michael.skyline.model.Phase;
import jolson.michael.skyline.model.Player;
import jolson.michael.skyline.model.Role;
import jolson.michael.skyline.model.State;

public class Controller {
	private State gameState;
	
	public void intializeGame(int numPlayers, Random random) {
		if (numPlayers < 2 || numPlayers > 5) throw new IllegalArgumentException();
		if (random == null) throw new NullPointerException();
		
		this.gameState = new State();
		this.initializeDeck(random);
		this.initializeTrumps(numPlayers);
		this.initializeLocations(numPlayers);
		this.initializePlayers(numPlayers);
		this.initializeGameState(numPlayers, random);
	}
	
	private void initializeDeck(Random random) {
		for (int i = 0; i < 20; i++) {
			this.gameState.getGame().getDeck().addToTop(DeckCard.ACCOUNTANT);
			this.gameState.getGame().getDeck().addToTop(DeckCard.BUILDER);
			this.gameState.getGame().getDeck().addToTop(DeckCard.CONTRACTOR);
			this.gameState.getGame().getDeck().addToTop(DeckCard.EXECUTIVE);
			this.gameState.getGame().getDeck().addToTop(DeckCard.INVESTOR);
			this.gameState.getGame().getDeck().addToTop(DeckCard.LAWYER);
		}
		
		this.gameState.getGame().getDeck().shuffle(random);;
	}
	
	private void initializeTrumps(int numPlayers) {
		this.gameState.getGame().setNumTrumps(numPlayers);
	}
	
	private void initializeLocations(int numPlayers) {
		final int numLocationsOfType = 6;
		
		this.gameState.getGame().getZonedLocations().put(Role.ACCOUNTANT, numPlayers);
		this.gameState.getGame().getUnzonedLocations().put(Role.ACCOUNTANT, numLocationsOfType - numPlayers);
		this.gameState.getGame().getZonedLocations().put(Role.BUILDER, numPlayers);
		this.gameState.getGame().getUnzonedLocations().put(Role.BUILDER, numLocationsOfType - numPlayers);
		this.gameState.getGame().getZonedLocations().put(Role.EXECUTIVE, numPlayers);
		this.gameState.getGame().getUnzonedLocations().put(Role.EXECUTIVE, numLocationsOfType - numPlayers);
		this.gameState.getGame().getZonedLocations().put(Role.INVESTOR, numPlayers);
		this.gameState.getGame().getUnzonedLocations().put(Role.INVESTOR, numLocationsOfType - numPlayers);
		this.gameState.getGame().getZonedLocations().put(Role.CONTRACTOR, numPlayers);
		this.gameState.getGame().getUnzonedLocations().put(Role.CONTRACTOR, numLocationsOfType - numPlayers);
		this.gameState.getGame().getZonedLocations().put(Role.LAWYER, numPlayers);
		this.gameState.getGame().getUnzonedLocations().put(Role.LAWYER, numLocationsOfType - numPlayers);
	}
	
	private void initializePlayers(int numPlayers) {
		final int initialHandSize = 5;
		
		for (int i = 0; i < numPlayers; i++) {
			Player player = new Player();
			player.setInfluence(2);
			for (int j = 0; j < initialHandSize; j++) {
				player.addCardToHand(this.gameState.getGame().getDeck().draw());
			}
		}
	}
	
	private void initializeGameState(int numPlayers, Random random) {
		this.gameState.setPhase(Phase.DECLARE);
		this.gameState.setInitiatorIndex(random.nextInt(numPlayers));
		this.gameState.setPhasingPlayerIndex(this.gameState.getInitiatorIndex());
	}
}
