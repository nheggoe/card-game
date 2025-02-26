package dev.nheggoe.assessment.four.backend;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Nick Heggø
 * @version 2025.02.26
 */
public enum CardRank {
    ACE(0),
    TWO(1),
    THREE(2),
    FOUR(3),
    FIVE(4),
    SIX(5),
    SEVEN(6),
    EIGHT(7),
    NINE(8),
    TEN(9),
    JACK(10),
    QUEEN(11),
    KING(12);

    private final int index;
    private static final Map<Integer, CardRank> cardRankMap = new HashMap<>();

    static {
        for (CardRank rank : CardRank.values()) {
            cardRankMap.put(rank.index, rank);
        }
    }

    CardRank(int index) {
        this.index = index;
    }

    /**
     * Retrieves the card rank corresponding to the given index.
     *
     * @param index The index of the card rank to retrieve.
     * @return The card rank associated with the specified index,
     * or null if no rank exists for the given index.
     */
    public static CardRank getCardRank(int index) {
        return cardRankMap.get(index);
    }
}
