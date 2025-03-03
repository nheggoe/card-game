package dev.nheggoe.cardgame.backend.engine;

import dev.nheggoe.cardgame.backend.hand.Hand;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Nick Heggø
 * @version 2025.03.03
 */
public class CardGameEngine {

    private static final Logger LOGGER = Logger.getLogger(CardGameEngine.class.getName());
    private static boolean running;

    private static Hand hand;

    /**
     * Default constructor
     */
    public CardGameEngine() {
    }

    /**
     * Starts the game engine by setting the running flag to true and invoking the engine method.
     * This method is designed to initiate a loop that continuously executes game logic
     * until the engine is instructed to terminate.
     */
    public void run() {
        running = true;
        engine();
    }

    private void engine() {
        while (running) {
            try {
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, "An exception occurred", e);
            }
        }
    }

    private void terminate() {
        running = false;
    }

    private void newPlayer() {
        hand = new Hand();
    }

    private void dealHands(int numberOfCards) {
        // hand.addCard();
    }

}
