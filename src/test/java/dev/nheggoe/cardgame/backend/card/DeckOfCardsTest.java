package dev.nheggoe.cardgame.backend.card;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class DeckOfCardsTest {

  @Test
  void testDrawNextRandomCardRemovesCardFromDeck() {
    DeckOfCards deck = new DeckOfCards();
    for (int i = 0; i < 52; i++) {
      PlayingCard drawnCard = deck.drawNextRandomCard();
      assertNotNull(drawnCard);
    }
  }

  @Test
  void testDrawNextRandomCardUniqueCards() {
    DeckOfCards deck = new DeckOfCards();
    Set<PlayingCard> drawnCards = new HashSet<>();
    int originalDeckSize = deck.getRemainingCardCount();

    for (int i = 0; i < originalDeckSize; i++) {
      PlayingCard card = deck.drawNextRandomCard();
      assertNotNull(card);
      assertTrue(drawnCards.add(card), "Duplicate card drawn: " + card);
    }

    assertEquals(originalDeckSize, drawnCards.size());
    assertEquals(0, deck.getRemainingCardCount());
  }
}
