package dev.nheggoe.cardgame.backend.card;

import dev.nheggoe.cardgame.backend.util.CardUtility;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a deck of playing cards.
 * This class provides methods to manage a standard 52-card deck, including
 * initializing the deck, drawing cards randomly, and checking the remaining card count.
 *
 * @author Nick Heggø
 * @version 2025.03.03
 */
public class DeckOfCards {

    private final List<PlayingCard> deckOfCards;

    public DeckOfCards() {
        deckOfCards = new ArrayList<>();
        initialDeck();
    }

    /**
     * Draws a random card from the current deck of cards and removes it from the deck.
     * The card is randomly selected from the remaining cards in the deck.
     *
     * @return the randomly drawn card of type {@code PlayingCard}
     */
    public PlayingCard drawNextRandomCard() {
        int availableCards = deckOfCards.size();
        int randomIndex = CardUtility.drawRandomCardNumber(availableCards);
        return deckOfCards.remove(randomIndex);
    }

    /**
     * Retrieves the count of remaining cards in the deck.
     *
     * @return the number of cards currently present in the deck
     */
    public int getRemainingCardCount() {
        return deckOfCards.size();
    }

    /**
     * Create a fresh new 52-card deck.
     */
    private void initialDeck() {
        for (CardSuit suit : CardSuit.values()) {
            for (CardRank rank : CardRank.values()) {
                deckOfCards.add(new PlayingCard(suit, rank));
            }
        }
    }
}
