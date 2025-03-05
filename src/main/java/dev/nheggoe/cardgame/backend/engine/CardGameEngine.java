package dev.nheggoe.cardgame.backend.engine;

import dev.nheggoe.cardgame.backend.card.DeckOfCards;
import dev.nheggoe.cardgame.backend.hand.Hand;

/**
 * @author Nick Heggø
 * @version 2025.03.03
 */
public class CardGameEngine {

    private static DeckOfCards deckOfCards;
    private static Hand hand;

    /**
     * Default constructor
     */
    public CardGameEngine() {
        startNewGame();
    }

    /**
     * Initializes and starts a new game by resetting the deck of cards
     * and the player's hand.
     * A new deck of cards is created, and the player's
     * hand is cleared to prepare for gameplay.
     */
    public void startNewGame() {
        deckOfCards = new DeckOfCards();
        hand = new Hand();
    }

    /**
     * Deals a specified number of cards from the deck to the hand.
     * This method draws random cards from the current deck and adds them
     * to the player's hand.
     *
     * @param numberOfCards the number of cards to be dealt to the hand
     */
    public void drawCards(int numberOfCards) {
        for (int i = 0; i < numberOfCards; i++) {
            hand.addCard(deckOfCards.drawNextRandomCard());
        }
    }

    public int getHandSide() {
        return hand.getHandSize();
    }

    /**
     * Checks if the current hand of cards forms a flush.
     * A flush occurs when five cards in the hand are of the same suit.
     *
     * @return true if the hand contains a flush, false otherwise
     */
    public boolean isFlush() {
        return hand.isFlush();
    }
}
