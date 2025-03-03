package dev.nheggoe.cardgame.backend.util;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Nick Heggø
 * @version 2025.03.03
 */
class CardUtilityTest {

    @Test
    void testCorrectRandomIndexRange() {
        CardUtility.reset();
        int cardNumber = CardUtility.drawRandomCardNumber();
        assertTrue(cardNumber <= 52 && cardNumber >= 1);
    }

    @Test
    void testNoneRepeatNumber() {
        CardUtility.reset();
        List<Integer> randomNumberIndex = new ArrayList<>();
        for (int i = 0; i < 52; i++) {
            randomNumberIndex.add(CardUtility.drawRandomCardNumber());
        }

        assertEquals(52, randomNumberIndex.stream().distinct().count());
    }

    @Test
    void testRunningOutOfCards() {
        CardUtility.reset();
        assertThrows(IllegalStateException.class, () -> {
            for (int i = 0; i < 53; i++) {
                CardUtility.drawRandomCardNumber();
            }
        });
    }

}
