package jolson.michael.skyline.model;

public class ReactSegment extends Segment {
	private Role role;
	
	public ReactSegment(Role role, int actorIndex, Segment previous) {
		super(actorIndex, previous);
		
		this.role = role;
	}
	
	public Role getRole() { return this.role; } 
}
