package jolson.michael.skyline.model;

public class ActionSegment {
	private Role role;
	private int actorIndex;
	private int numActions;
	private ActionSegment previous;
	
	public ActionSegment(Role role, int phashingPlayerIndex, int numActions, ActionSegment previous) {
		this.role = role;
		this.actorIndex = phashingPlayerIndex;
		this.numActions = numActions;
		this.previous = previous;
	}
	
	public int getNumActions() {
		return numActions;
	}

	public void setNumActions(int numActions) {
		this.numActions = numActions;
	}

	public Role getRole() {
		return role;
	}

	public int getActorIndex() {
		return actorIndex;
	}

	public ActionSegment getPrevious() {
		return previous;
	}
}
