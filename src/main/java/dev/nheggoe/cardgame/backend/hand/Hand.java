package dev.nheggoe.cardgame.backend.hand;

import dev.nheggoe.cardgame.backend.card.CardSuit;
import dev.nheggoe.cardgame.backend.card.PlayingCard;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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

    public void addCard(Collection<PlayingCard> cards) {
        hand.addAll(cards);
    }

    /**
     * Determines if the hand is a flush, which means all cards in the hand
     * are of the same suit.
     *
     * @return true if all the cards in the hand have the same suit, false otherwise
     */
    public boolean isFlush() {
        if (hand.size() < 5) {
            return false;
        }

        Map<CardSuit, Long> suitCounts = hand.stream()
                .collect(Collectors.groupingBy(
                        PlayingCard::suit,
                        Collectors.counting()));

        return suitCounts.values().stream().anyMatch(count -> count >= 5);
    }
}
