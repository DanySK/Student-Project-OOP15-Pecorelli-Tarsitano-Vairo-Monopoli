package it.unibo.monopoli.model.cards;


/**
 * This is a specific implementation of one of classic game's {@link Deck}s.
 *
 */
public enum ClassicDecks {
    COMMUNITY_CHEST("PROBABILITÀ"),
    CHANCE("IMPREVISTI");

    private final String name;

    /**
     * Constructs an instance of {@link Deck} named {@link CommunityChest}.
     */
    ClassicDecks(final String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
