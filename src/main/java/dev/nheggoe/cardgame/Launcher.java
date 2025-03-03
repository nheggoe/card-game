package dev.nheggoe.cardgame;

import dev.nheggoe.cardgame.backend.engine.CardGameEngine;

/**
 * @author Nick Heggø
 * @version 2025.02.26
 */
public class Launcher {
    public static void main(String[] args) {
        new CardGameEngine().run();
    }
}
