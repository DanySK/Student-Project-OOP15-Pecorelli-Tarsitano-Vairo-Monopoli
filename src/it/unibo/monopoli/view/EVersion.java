package it.unibo.monopoli.view;

public enum EVersion {

    NOT_SELECTABLE_OPTION(" - Select an Option - "), CLASSIC("Classic"),
    ITALIAN_VERSION ("Italian Version");
    // PROVA1("Prova1"),
    // PROVA2("Prova2");

    private String name;

    private EVersion(final String name) {
        this.name = name;

    }

    public String getName() {
        return this.name;
    }
}
