package dev.nheggoe.cardgame.backend.hand;

import static org.junit.jupiter.api.Assertions.*;

import dev.nheggoe.cardgame.backend.card.CardRank;
import dev.nheggoe.cardgame.backend.card.CardSuit;
import dev.nheggoe.cardgame.backend.card.PlayingCard;
import java.util.List;
import org.junit.jupiter.api.Test;

class HandTest {

  @Test
  void testNoFlushOnRepeatCards() {
    Hand hand = new Hand();
    hand.addCard(
        List.of(
            new PlayingCard(CardSuit.CLUBS, CardRank.FIVE),
            new PlayingCard(CardSuit.CLUBS, CardRank.FIVE),
            new PlayingCard(CardSuit.CLUBS, CardRank.FIVE),
            new PlayingCard(CardSuit.CLUBS, CardRank.FIVE),
            new PlayingCard(CardSuit.CLUBS, CardRank.FIVE),
            new PlayingCard(CardSuit.CLUBS, CardRank.FIVE)));

    assertFalse(hand.isFlush());
  }

  @Test
  void testIsFlush() {
    Hand hand = new Hand();
    hand.addCard(
        List.of(
            new PlayingCard(CardSuit.CLUBS, CardRank.FIVE),
            new PlayingCard(CardSuit.CLUBS, CardRank.ACE),
            new PlayingCard(CardSuit.CLUBS, CardRank.TWO),
            new PlayingCard(CardSuit.CLUBS, CardRank.JACK),
            new PlayingCard(CardSuit.CLUBS, CardRank.KING),
            new PlayingCard(CardSuit.CLUBS, CardRank.TEN)));

    assertTrue(hand.isFlush());
  }

  @Test
  void testIsNotFlushOnDifferentSuit() {
    Hand hand = new Hand();
    hand.addCard(
        List.of(
            new PlayingCard(CardSuit.DIAMONDS, CardRank.FIVE),
            new PlayingCard(CardSuit.CLUBS, CardRank.ACE),
            new PlayingCard(CardSuit.CLUBS, CardRank.TWO),
            new PlayingCard(CardSuit.DIAMONDS, CardRank.JACK),
            new PlayingCard(CardSuit.CLUBS, CardRank.KING),
            new PlayingCard(CardSuit.CLUBS, CardRank.TEN)));

    assertFalse(hand.isFlush());
  }

  @Test
  void testGetHandValue() {
    Hand hand = new Hand();
    hand.addCard(
        List.of(
            new PlayingCard(CardSuit.CLUBS, CardRank.FIVE),
            new PlayingCard(CardSuit.CLUBS, CardRank.TEN),
            new PlayingCard(CardSuit.CLUBS, CardRank.JACK),
            new PlayingCard(CardSuit.CLUBS, CardRank.QUEEN),
            new PlayingCard(CardSuit.CLUBS, CardRank.KING)));

    assertEquals(45, hand.getHandValue());
  }

  @Test
  void testFilterHeart() {
    Hand hand = new Hand();
    hand.addCard(
        List.of(
            new PlayingCard(CardSuit.CLUBS, CardRank.FIVE),
            new PlayingCard(CardSuit.HEARTS, CardRank.TEN),
            new PlayingCard(CardSuit.HEARTS, CardRank.JACK),
            new PlayingCard(CardSuit.CLUBS, CardRank.QUEEN),
            new PlayingCard(CardSuit.HEARTS, CardRank.KING)));
    var cardsOfSuit = hand.getCardsOfSuit(CardSuit.HEARTS);
    assertEquals(3, cardsOfSuit.size());
  }
}
