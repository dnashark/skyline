package jolson.michael.skyline.model;

public class State {
	private Game game;
	private int initiatorIndex;
	private Phase phase;
	private int phasingPlayerIndex;
	private ActionSegment currentActionPhase;
	
	public State() {
		this.game = new Game();
	}

	public int getInitiatorIndex() {
		return initiatorIndex;
	}

	public void setInitiatorIndex(int initiatorIndex) {
		this.initiatorIndex = initiatorIndex;
	}

	public Phase getPhase() {
		return phase;
	}

	public void setPhase(Phase phase) {
		this.phase = phase;
	}

	public int getPhasingPlayerIndex() {
		return phasingPlayerIndex;
	}

	public void setPhasingPlayerIndex(int phasingPlayerIndex) {
		this.phasingPlayerIndex = phasingPlayerIndex;
	}

	public ActionSegment getCurrentActionPhase() {
		return currentActionPhase;
	}

	public void setCurrentActionPhase(ActionSegment currentActionPhase) {
		this.currentActionPhase = currentActionPhase;
	}

	public Game getGame() {
		return game;
	}
}
