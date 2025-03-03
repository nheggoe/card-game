package dev.nheggoe.cardgame.backend.util;

import java.util.*;

/**
 * Utility class for card-related operations.
 * This class is designed to provide static methods for generating and managing card numbers.
 * It cannot be instantiated.
 *
 * @author Nick Heggø
 * @version 2025.03.03
 */
public class CardUtility {

    private static final Random RANDOM = new Random();

    private static List<Integer> cardNumbers = new ArrayList<>();

    /**
     * Utility class for card-related operations.
     * This class is designed to provide static methods for generating and managing card numbers.
     * It cannot be instantiated.
     */
    private CardUtility() {
    }

    /**
     * Resets the card number list to its initial state, clearing any existing values
     * and repopulating it with integers from 1 to 52 inclusive. This method ensures
     * that the deck is restored to its original configuration for reuse.
     */
    public static void reset() {
        cardNumbers = new ArrayList<>();
        initializeCardNumber();
    }

    /**
     * Draws a random card number from the currently available card numbers.
     * Each card number is removed from the list upon being drawn, ensuring that
     * no card number is repeated until the list is reset.
     *
     * @return a randomly selected card number from the available card numbers
     * @throws IllegalStateException if no card numbers are available to draw
     */
    public static int drawRandomCardNumber() {
        if (cardNumbers.size() <= 0) {
            throw new IllegalStateException("Running out of cards!");
        }
        int randomIndex = RANDOM.nextInt(cardNumbers.size());
        return cardNumbers.remove(randomIndex);
    }

    /**
     * Initializes the CARD_NUMBER list with integers ranging from 1 to 52 inclusive.
     * This method is responsible for populating the list to represent a
     * standard deck of cards, where each integer corresponds to a unique card.
     * It is typically utilized to reset or initialize the card deck.
     */
    private static void initializeCardNumber() {
        for (int i = 0; i < 52; i++) {
            cardNumbers.add(i + 1);
        }
    }
}
