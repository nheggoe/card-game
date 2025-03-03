package dev.nheggoe.cardgame.backend.card;

/**
 * Represents the rank of a playing card in a standard deck.
 * A card rank can have values ranging from Ace ("1") to King ("K").
 * Each rank is associated with a specific symbol that represents it.
 *
 * @author Nick Heggø
 * @version 2025.03.03
 */
public enum CardRank {

    ACE("1"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8"),
    NINE("9"),
    TEN("10"),
    JACK("J"),
    QUEEN("Q"),
    KING("K");

    private final String rankSymbol;

    CardRank(String rankSymbol) {
        this.rankSymbol = rankSymbol;
    }

    /**
     * Retrieves the symbol representing the rank of the card.
     *
     * @return the symbol of the card rank as a string
     */
    public String getRankSymbol() {
        return this.rankSymbol;
    }
}
