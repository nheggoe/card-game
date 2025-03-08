package dev.nheggoe.cardgame.backend.card;

/**
 * Represents a playing card with a specific suit and rank. A playing card is a combination of a
 * suit (e.g., hearts, spades) and a rank (e.g., ace, king, numeric values).
 *
 * @author Nick Heggø
 * @version 2025.03.03
 */
public record PlayingCard(CardSuit suit, CardRank rank) {

  /**
   * Returns the suit and rank of the card as a string. A 4 of hearts is returned as the string
   * "H4".
   *
   * @return the suit and rank of the card as a string
   */
  public String getCardRepresentation() {
    return String.format("%s%s", suit.getSuitChar(), rank.getRankSymbol());
  }

  @Override
  public String toString() {
    return "card: %s %s".formatted(suit, rank);
  }
}
