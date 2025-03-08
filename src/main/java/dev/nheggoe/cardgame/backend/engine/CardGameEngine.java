package dev.nheggoe.cardgame.backend.engine;

import dev.nheggoe.cardgame.backend.card.DeckOfCards;
import dev.nheggoe.cardgame.backend.hand.Hand;

/**
 * @author Nick Heggø
 * @version 2025.03.03
 */
public class CardGameEngine {

  private static DeckOfCards deckOfCards;
  private static Hand hand;
  private static int flushCount;

  /** Default constructor */
  public CardGameEngine() {
    startNewGame();
  }

  /**
   * Initializes and starts a new game by resetting the deck of cards and the player's hand. A new
   * deck of cards is created, and the player's hand is cleared to prepare for gameplay.
   */
  public void startNewGame() {
    deckOfCards = new DeckOfCards();
    hand = new Hand();
    flushCount = 0;
  }

  /**
   * Deals a specified number of cards from the deck to the hand. This method draws random cards
   * from the current deck and adds them to the player's hand.
   *
   * @param numberOfCards the number of cards to be dealt to the hand
   */
  public void drawCards(int numberOfCards) {
    if (deckOfCards.getRemainingCardCount() <= 0) {
      throw new IllegalStateException("You're out of card!");
    }

    for (int i = 0; i < numberOfCards; i++) {
      hand.addCard(deckOfCards.drawNextRandomCard());
    }
  }

  public int getHandSide() {
    return hand.getHandSize();
  }

  public void newHand() {
    hand = new Hand();
  }

  /**
   * Checks if the current hand of cards forms a flush. A flush occurs when five cards in the hand
   * are of the same suit.
   *
   * @return true if the hand contains a flush, false otherwise
   */
  public boolean isFlush() {
    if (hand.isFlush()) {
      flushCount++;
    }
    return hand.isFlush();
  }

  /**
   * Retrieves the player's current hand of cards. The hand consists of the cards dealt and managed
   * during the game. This includes cards added through operations such as drawing from the deck.
   *
   * @return the current hand of cards
   */
  public Hand getHand() {
    return hand;
  }

  public int getRemainingCardCount() {
    return deckOfCards.getRemainingCardCount();
  }

  public int getFlushCount() {
    return flushCount;
  }
}
