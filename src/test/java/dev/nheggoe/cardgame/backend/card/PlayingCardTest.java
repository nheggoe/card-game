package dev.nheggoe.cardgame.backend.card;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlayingCardTest {

    @Test
    void testPlayingCardStringRepresentation() {
        var aceOfDiamonds = new PlayingCard(CardSuit.DIAMONDS, CardRank.ACE);
        var fiveOfClubs = new PlayingCard(CardSuit.CLUBS, CardRank.FIVE);
        String expectedDiamonds = "D1";
        String expectedClub = "C5";
        assertEquals(expectedDiamonds, aceOfDiamonds.getCardRepresentation());
        assertEquals(expectedClub, fiveOfClubs.getCardRepresentation());
    }
}
