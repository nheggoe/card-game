package dev.nheggoe.cardgame.backend;

import dev.nheggoe.cardgame.backend.playingcard.DeckOfCards;
import dev.nheggoe.cardgame.backend.playingcard.PlayingCard;
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
        assertEquals(13, cardDeck.stream()
                .map(PlayingCard::getFace)
                .distinct()
                .count());
    }

}
