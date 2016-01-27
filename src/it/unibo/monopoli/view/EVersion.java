package it.unibo.monopoli.view;

public enum EVersion {
	CLASSIC("Classic"), 
	PROVA1("Prova1"), 
	PROVA2("Prova2");

	private String name;

	private EVersion(final String name) {
		this.name = name;

	}

	public String getName() {
		return this.name;
	}
}
