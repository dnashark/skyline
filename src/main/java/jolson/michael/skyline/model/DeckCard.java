package jolson.michael.skyline.model;

public enum DeckCard implements Card {
	CONTRACTOR(Role.CONTRACTOR),
	INVESTOR(Role.INVESTOR),
	BUILDER(Role.BUILDER),
	LAWYER(Role.LAWYER),
	ACCOUNTANT(Role.ACCOUNTANT),
	EXECUTIVE(Role.EXECUTIVE);
	
	private Role role;
	
	private DeckCard(Role role) {
		this.role = role;
	}
	
	public Role getRole() { return this.role; }
}
