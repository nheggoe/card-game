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
        newPlayer();
    }

    public void newPlayer() {
        deckOfCards = new DeckOfCards();
        hand = new Hand();
    }

    public void dealHands(int numberOfCards) {
        for (int i = 0; i < numberOfCards; i++) {
            hand.addCard(deckOfCards.drawNextRandomCard());
        }
    }

    public boolean isFlush() {
        return hand.isFlush();
    }
}
