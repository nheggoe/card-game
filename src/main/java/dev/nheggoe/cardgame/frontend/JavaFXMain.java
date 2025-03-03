package dev.nheggoe.cardgame.frontend;

import dev.nheggoe.cardgame.backend.engine.CardGameEngine;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * @author Nick Heggø
 * @version 2025.02.26
 */
public class JavaFXMain extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) {
        CardGameEngine engine = new CardGameEngine();
        var root = new VBox();
        Button dealHand = new Button("Deal Hand");
        dealHand.setOnAction(event -> engine.dealHands(1));

        Button checkHand = new Button("Check Hand");
        checkHand.setOnAction(event -> {
            if (engine.isFlush()) {
                // TODO winning scene
            }
        });

        root.getChildren().addAll(dealHand, checkHand);

        primaryStage.setScene(new Scene(root));

        primaryStage.show();
    }
}
