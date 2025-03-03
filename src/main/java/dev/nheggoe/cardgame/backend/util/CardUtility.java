package dev.nheggoe.cardgame.backend.util;

import java.util.*;

/**
 * @author Nick Heggø
 * @version 2025.03.03
 */
public class CardUtility {

    private static final Random RANDOM = new Random();
    private static List<Integer> CARD_NUMBER = new ArrayList<>();

    /**
     * Utility class for card-related operations.
     * This class is designed to provide static methods for generating and managing card numbers.
     * It cannot be instantiated.
     */
    private CardUtility() {
    }

    public static void reset() {
        CARD_NUMBER = new ArrayList<>();
        initializeCardNumber();
    }

    public static int drawRandomCardNumber() {
        if (CARD_NUMBER.size() <= 0) {
            throw new IllegalStateException("Running out of cards!");
        }
        int randomIndex = RANDOM.nextInt(CARD_NUMBER.size());
        return CARD_NUMBER.remove(randomIndex);
    }

    /**
     * Initializes the CARD_NUMBER list with integers ranging from 1 to 52.
     * This method is responsible for populating the list to represent a
     * standard deck of cards, where each integer corresponds to a unique card.
     * It is typically utilized to reset or initialize the card deck.
     */
    private static void initializeCardNumber() {
        for (int i = 0; i < 52; i++) {
            CARD_NUMBER.add(i + 1);
        }
    }
}
