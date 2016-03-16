package jolson.michael.skyline.model;

public class Segment {
	private Segment previous;
	private int actorIndex;
	
	public Segment(int actorIndex, Segment previous) {
		this.previous = previous;
		this.actorIndex = actorIndex;
	}
	
	public Segment getPrevious() { return this.previous; }
	public int getActorIndex() { return this.actorIndex; }
}
