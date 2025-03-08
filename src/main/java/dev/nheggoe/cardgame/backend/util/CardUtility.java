package dev.nheggoe.cardgame.backend.util;

import java.util.*;

/**
 * Utility class for card-related operations. It cannot be instantiated.
 *
 * @author Nick Heggø
 * @version 2025.03.03
 */
public class CardUtility {

  private static final Random RANDOM = new Random();

  /**
   * Utility class for card-related operations. This class is designed to provide static methods for
   * generating and managing card numbers. It cannot be instantiated.
   */
  private CardUtility() {}

  /**
   * Draws a random card number from the currently available card numbers. Each card number is
   * removed from the list upon being drawn, ensuring that no card number is repeated until the list
   * is reset.
   *
   * @return a randomly selected card number from the available card numbers
   * @throws IllegalStateException if no card numbers are available to draw
   */
  public static int drawRandomCardNumber(int deckNumber) {
    if (deckNumber <= 0) {
      throw new IllegalStateException("Running out of cards!");
    }
    return RANDOM.nextInt(deckNumber);
  }
}
