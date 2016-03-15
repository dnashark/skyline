package jolson.michael.skyline.model;

public class InProgressBuilding {
	private Role site;
	private int numInvestments;
	
	public InProgressBuilding(Role site, int numInvestments) {
		this.site = site;
		this.numInvestments = numInvestments;
	}
	
	public Role getSite() {
		return this.site;
	}

	public int getNumInvestments() {
		return this.numInvestments;
	}

}
