package dev.nheggoe.cardgame.backend.hand;

import dev.nheggoe.cardgame.backend.card.PlayingCard;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Nick Heggø
 * @version 2025.03.03
 */
public class Hand {

    private final Set<PlayingCard> hand;

    public Hand() {
        hand = new HashSet<>();
    }

    public void addCard(PlayingCard card) {
        hand.add(card);
    }
}
