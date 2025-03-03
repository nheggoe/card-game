package dev.nheggoe.cardgame.backend.card;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents the four suits of a standard deck of playing cards.
 * Each suit is assigned an index for reference.
 */
public enum CardSuit {
    HEARTS(0, 'H'),
    DIAMONDS(1, 'D'),
    CLUBS(2, 'C'),
    SPADES(3, 'S');

    private final int index;
    private final char suitChar;
    private static final Map<Integer, CardSuit> suitMap = new HashMap<>();

    CardSuit(int index, char suitChar) {
        this.index = index;
        this.suitChar = suitChar;
    }

    /**
     * Retrieves the card suit corresponding to the given index.
     *
     * @param index The index of the card suit to retrieve.
     * @return The card suit associated with the specified index,
     * or null if no suit exists for the given index.
     */
    public static CardSuit getSuit(int index) {
        return suitMap.get(index);
    }

    static {
        for (CardSuit suit : CardSuit.values()) {
            suitMap.put(suit.index, suit);
        }
    }

    public char getSuitChar() {
        return suitChar;
    }

}
