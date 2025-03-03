package dev.nheggoe.cardgame.backend.card;

/**
 * Represents the four suits of a standard deck of playing cards.
 *
 * @author Nick Heggø
 * @version 2025.03.03
 */
public enum CardSuit {

    HEARTS('H'),
    DIAMONDS('D'),
    CLUBS('C'),
    SPADES('S');

    private final char suitChar;

    CardSuit(char suitChar) {
        this.suitChar = suitChar;
    }

    /**
     * Retrieves the character representing the suit of the card.
     * The suit character corresponds to the abbreviations of the card suits,
     * such as 'H' for Hearts, 'D' for Diamonds, 'C' for Clubs, and 'S' for Spades.
     *
     * @return the character representing the card suit
     */
    public char getSuitChar() {
        return suitChar;
    }
}
