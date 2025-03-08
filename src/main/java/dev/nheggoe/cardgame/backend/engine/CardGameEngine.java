package dev.nheggoe.cardgame.backend.engine;

import dev.nheggoe.cardgame.backend.card.DeckOfCards;
import dev.nheggoe.cardgame.backend.hand.Hand;

/**
 * The CardGameEngine class provides the core functionalities required to manage a card game. It
 * includes the ability to handle the deck of cards, manage the player's hand, determine specific
 * card combinations like a flush, and track gaming events such as the total number of flushes
 * achieved.
 *
 * @author Nick Heggø
 * @version 2025.03.03
 */
public class CardGameEngine {

  private static DeckOfCards deckOfCards;
  private static Hand hand;
  private static int flushCount;

  /**
   * Constructs a new instance of the {@code CardGameEngine} class. This constructor automatically
   * initializes the game by invoking the {@code startNewGame()} method, which resets the deck,
   * clears the player's hand, and starts a fresh game session.
   */
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

  /**
   * Retrieves the number of cards currently in the player's hand.
   *
   * @return the total number of cards in the hand
   */
  public int getHandSide() {
    return hand.getHandSize();
  }

  /**
   * Resets the player's hand by creating a new instance of the {@code Hand} class. This method
   * effectively clears the current set of cards in the hand, preparing it for new cards to be
   * dealt.
   */
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
