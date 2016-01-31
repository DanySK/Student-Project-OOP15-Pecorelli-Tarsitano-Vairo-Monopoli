package it.unibo.monopoli.model.cards;


public enum ItalianDecks {
    PROBABILITÀ("PROBABILITÀ"),
    IMPREVISTI("IMPREVISTI");

    private final String name;

    /**
     * Constructs an instance of {@link Deck} named {@link CommunityChest}.
     */
   ItalianDecks(final String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
