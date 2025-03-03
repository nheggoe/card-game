package dev.nheggoe.cardgame.backend.card;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Nick Heggø
 * @version 2025.02.26
 */
public class DeckOfCards {

    private final Set<PlayingCard> deckOfCards;

    public DeckOfCards() {
        deckOfCards = new HashSet<>();
        initialDeck();
    }

    /**
     * Retrieves a copy of the current deck of cards.
     * The returned deck is a set of unique playing cards representing a standard deck.
     *
     * @return a copy of the deck of cards as a Set of PlayingCard objects
     */
    public Set<PlayingCard> getDeckOfCards() {
        return new HashSet<>(deckOfCards);
    }

    /**
     * Create a fresh new 52-card deck.
     */
    private void initialDeck() {
        for (int suitIndex = 0; suitIndex < 4; suitIndex++) {
            for (int cardIndex = 0; cardIndex < 13; cardIndex++) {
                char suitChar = CardSuit.getSuit(suitIndex).getSuitChar();
                deckOfCards.add(new PlayingCard(suitChar, cardIndex + 1));
            }
        }
    }

}
