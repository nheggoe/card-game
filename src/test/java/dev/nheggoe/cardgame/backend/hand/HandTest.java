package dev.nheggoe.cardgame.backend.hand;

import dev.nheggoe.cardgame.backend.card.CardRank;
import dev.nheggoe.cardgame.backend.card.CardSuit;
import dev.nheggoe.cardgame.backend.card.PlayingCard;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HandTest {

    @Test
    void testNoFlushOnRepeatCards() {
        Hand hand = new Hand();
        hand.addCard(List.of(
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
        hand.addCard(List.of(
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
        hand.addCard(List.of(
                new PlayingCard(CardSuit.DIAMONDS, CardRank.FIVE),
                new PlayingCard(CardSuit.CLUBS, CardRank.ACE),
                new PlayingCard(CardSuit.CLUBS, CardRank.TWO),
                new PlayingCard(CardSuit.DIAMONDS, CardRank.JACK),
                new PlayingCard(CardSuit.CLUBS, CardRank.KING),
                new PlayingCard(CardSuit.CLUBS, CardRank.TEN)));

        assertFalse(hand.isFlush());
    }

}
