package dev.nheggoe.cardgame.backend.hand;

import dev.nheggoe.cardgame.backend.card.CardRank;
import dev.nheggoe.cardgame.backend.card.CardSuit;
import dev.nheggoe.cardgame.backend.card.PlayingCard;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Represents a hand of playing cards. Provides functionality to add cards individually or as a
 * collection and determine specific properties of the hand, such as whether it forms a flush.
 *
 * @author Nick Heggø
 * @version 2025.03.08
 */
public class Hand {

  private final Set<PlayingCard> cards;

  /**
   * Constructs an empty hand. This constructor initializes the hand with an empty set of cards,
   * allowing cards to be added later. The hand is represented as a set to ensure unique cards
   * within the hand.
   */
  public Hand() {
    cards = new HashSet<>();
  }

  /**
   * Add PlayingCard into the hand.
   *
   * @param card the card that will be added to the hand.
   */
  public void addCard(PlayingCard card) {
    cards.add(card);
  }

  /**
   * Adds a collection of cards into the hand.
   *
   * @param cards collection of cards that will be added to the hand.
   */
  public void addCard(Collection<PlayingCard> cards) {
    this.cards.addAll(cards);
  }

  /**
   * Determines if the hand is a flush, which means five cards in the hand are of the same suit.
   *
   * @return true if the cards in the hand contain flush, false otherwise
   */
  public boolean isFlush() {
    if (cards.size() < 5) {
      return false;
    }

    Map<CardSuit, Long> suitCounts =
        cards.stream().collect(Collectors.groupingBy(PlayingCard::suit, Collectors.counting()));

    return suitCounts.values().stream().anyMatch(count -> count >= 5);
  }

  /**
   * Retrieves all the playing cards currently in the hand.
   *
   * @return a set containing all the playing cards in the hand
   */
  public Set<PlayingCard> getCards() {
    return new HashSet<>(cards);
  }

  /**
   * Retrieves all the playing cards in the hand that belong to a specific suit.
   *
   * @param suit the suit of the cards to be retrieved
   * @return a set containing all the playing cards in the specified suit
   */
  public Set<PlayingCard> getCardsOfSuit(CardSuit suit) {
    Set<PlayingCard> cardsOfSuit = new HashSet<>(cards);
    cardsOfSuit.removeIf(card -> card.suit() != suit);
    return cardsOfSuit;
  }

  /**
   * Checks if the Queen of Spades is present in the hand.
   *
   * @return true if the Queen of Spades is present in the current set of cards, false otherwise
   */
  public boolean isQueenOfSpadesPresent() {
    var queenOfSpades = new PlayingCard(CardSuit.SPADES, CardRank.QUEEN);
    return cards.stream().anyMatch(card -> card.equals(queenOfSpades));
  }

  /**
   * Retrieves the number of cards currently in the hand.
   *
   * @return the total count of cards in the hand
   */
  public int getHandSize() {
    return cards.size();
  }

  /**
   * Calculates the total value of the hand by summing up the values of the cards in the hand.
   * Numeric cards contribute their face value (e.g., 2-10), and face cards (Jack, Queen, King) are
   * assigned a value of 10. The total value is computed based on the rank symbols of the cards.
   *
   * @return the total numerical value of the hand
   */
  public int getHandValue() {
    return cards.stream()
        .map(PlayingCard::rank)
        .mapToInt(
            rank -> {
              try {
                return Integer.parseInt(rank.getRankSymbol());
              } catch (NumberFormatException e) {
                return 10;
              }
            })
        .sum();
  }
}
