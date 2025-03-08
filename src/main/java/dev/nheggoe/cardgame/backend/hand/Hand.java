package dev.nheggoe.cardgame.backend.hand;

import dev.nheggoe.cardgame.backend.card.CardSuit;
import dev.nheggoe.cardgame.backend.card.PlayingCard;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Represents a hand of playing cards. Provides functionality to add cards individually or as a
 * collection and determine specific properties of the hand, such as whether it forms a flush.
 *
 * @author Nick Heggø
 * @version 2025.03.03
 */
public class Hand {

  private final Set<PlayingCard> cards;

  /** Default constructor */
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

  public int getHandSize() {
    return cards.size();
  }

  public Set<PlayingCard> getCards() {
    return new HashSet<>(cards);
  }

  public Set<PlayingCard> getCardsOfSuit(CardSuit suit) {
    Set<PlayingCard> cardsOfSuit = new HashSet<>(cards);
    cardsOfSuit.removeIf(card -> card.suit() != suit);
    return cardsOfSuit;
  }

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
