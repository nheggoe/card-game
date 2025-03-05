package dev.nheggoe.cardgame.frontend;

import dev.nheggoe.cardgame.backend.engine.CardGameEngine;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Nick Heggø
 * @version 2025.02.26
 */
public class JavaFXMain extends Application {

    private static final Logger LOGGER = Logger.getLogger(JavaFXMain.class.getName());

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) {
        CardGameEngine engine = new CardGameEngine();
        var root = new VBox();
        Button dealHand = new Button("Deal Hand");
        dealHand.setOnAction(event -> {
            LOGGER.log(Level.INFO, "Dealing %d cards!".formatted(1));
            engine.dealHands(1);
        });

        Button checkHand = new Button("Check Hand");
        checkHand.setOnAction(event -> {
            LOGGER.log(Level.INFO, "Checking hand...");
            if (engine.isFlush()) {
                LOGGER.log(Level.INFO, "-------- Flush! ---------");
                var root2 = new VBox();
                root2.getChildren().add(new TilePane());

            }
        });

        root.getChildren().addAll(dealHand, checkHand);

        primaryStage.setScene(new Scene(root));

        primaryStage.show();
    }
}
