package dev.nheggoe.cardgame.frontend;

import dev.nheggoe.cardgame.backend.card.PlayingCard;
import dev.nheggoe.cardgame.backend.engine.CardGameEngine;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Nick Heggø
 * @version 2025.02.26
 */
public class JavaFXMain extends Application {

    private static final Logger LOGGER = Logger.getLogger(JavaFXMain.class.getName());

    // --------------- UI ----------------------
    private FlowPane cardDisplayArea;
    private TextField sumOfFacesFields;
    private TextField cardsOfHeartsField;
    private TextField flushField;
    private TextField queenOfSpadesFields;
    private List<PlayingCard> currentHand = new ArrayList<>();

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) {
        CardGameEngine engine = new CardGameEngine();
        var root = new BorderPane();
        root.setPadding(new Insets(20));

        // Create the card display area
        cardDisplayArea = new FlowPane();
        cardDisplayArea.setPrefSize(500, 300);
        cardDisplayArea.setPadding(new Insets(10));
        cardDisplayArea.setHgap(10);
        cardDisplayArea.setVgap(10);
        cardDisplayArea.setAlignment(Pos.CENTER);

        Text placeholderText = new Text("------ Place Holder ------");
        placeholderText.setFill(Color.GRAY);
        cardDisplayArea.getChildren().add(placeholderText);

        root.setCenter(cardDisplayArea);

        VBox rightPanel = new VBox(20);
        rightPanel.setPadding(new Insets(10));
        rightPanel.setAlignment(Pos.TOP_CENTER);

        Button dealHandButton = new Button("Deal Hand");
        dealHandButton.setOnAction(event -> {
            LOGGER.log(Level.INFO, "Dealing %d cards!".formatted(1));
            engine.dealHands(1);
        });

        Button checkHandButton = new Button("Check Hand");
        checkHandButton.setOnAction(event -> {
            LOGGER.log(Level.INFO, "Checking hand...");
            if (engine.isFlush()) {
                LOGGER.log(Level.INFO, "-------- Flush! ---------");
                var root2 = new VBox();
                root2.getChildren().add(new TilePane());

            }
        });

        root.getChildren().addAll(dealHandButton, checkHandButton);

        primaryStage.setScene(new Scene(root));

        primaryStage.show();
    }
}
