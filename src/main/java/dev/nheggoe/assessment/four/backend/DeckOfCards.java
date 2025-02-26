package dev.nheggoe.assessment.four.backend;

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

    public Set<PlayingCard> getDeckOfCards() {
        return deckOfCards;
    }

    private void initialDeck() {
        for (int suitIndex = 0; suitIndex < 4; suitIndex++) {
            for (int cardIndex = 0; cardIndex < 13; cardIndex++) {
                char suitChar = CardSuit.getSuit(suitIndex).getSuitChar();
                deckOfCards.add(new PlayingCard(suitChar, cardIndex + 1));
            }
        }
    }

}
