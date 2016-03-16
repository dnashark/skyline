package jolson.michael.skyline.model;

public class ActionSegment extends Segment {
	private Role role;
	private int numActions;
	
	public ActionSegment(Role role, int numActions, int actorIndex, Segment previous) {
		super(actorIndex, previous);
		
		this.role = role;
		this.numActions = numActions;
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
}
