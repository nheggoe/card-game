package dev.nheggoe.assessment.four.backend;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Nick Heggø
 * @version 2025.02.26
 */
class DeckOfCardsTest {

    @Test
    void assertCompletePlayingCardDeck() {
        var cardDeck = new DeckOfCards().getDeckOfCards();
        assertEquals(52, cardDeck.size());
    }

}