package dev.nheggoe.cardgame.frontend;

import dev.nheggoe.cardgame.backend.engine.CardGameEngine;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Nick Heggø
 * @version 2025.02.26
 */
public class JavaFXMain extends Application {

    private static final Logger LOGGER = Logger.getLogger(JavaFXMain.class.getName());
    private static final CardGameEngine engine = new CardGameEngine();

    // --------------- UI ----------------------
    private FlowPane cardDisplayArea;
    private TextField sumOfFacesFields;
    private TextField cardsOfHeartsField;
    private TextField flushField;
    private TextField queenOfSpadesFields;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) {
        var root = new BorderPane();
        root.setPadding(new Insets(20));

        initCardDisplayArea();

        Text placeholderText = new Text("------ Place Holder ------");
        placeholderText.setFill(Color.GRAY);
        cardDisplayArea.getChildren().add(placeholderText);

        root.setCenter(cardDisplayArea);

        VBox rightPanel = new VBox(20);
        rightPanel.setPadding(new Insets(10));
        rightPanel.setAlignment(Pos.TOP_CENTER);

        Button dealHandButton = new Button("Deal Hand");
        dealHandButton.setOnAction(e -> dealCards());

        Button checkHandButton = new Button("Check Hand");
        checkHandButton.setOnAction(e -> checkHand());

        rightPanel.getChildren().addAll(dealHandButton, checkHandButton);
        root.setRight(rightPanel);

        GridPane bottomPanel = new GridPane();
        bottomPanel.setHgap(10);
        bottomPanel.setVgap(10);
        bottomPanel.setPadding(new Insets(20, 0, 0, 0));

        Label sumOfFacesLabel = new Label("Sum of the faces: ");
        // FIXME
        sumOfFacesFields = new TextField("25");
        sumOfFacesFields.setEditable(false);
        sumOfFacesFields.setPrefWidth(80);

        Label cardsOfHeartsLabel = new Label("Cards of hearts:");
        // FIXME
        cardsOfHeartsField = new TextField();
        cardsOfHeartsField.setEditable(false);
        cardsOfHeartsField.setPrefWidth(150);

        Label flushLabel = new Label("Flush:");
        flushField = new TextField("Yes/NO");
        flushField.setEditable(false);
        flushField.setPrefWidth(80);

        Label queenOfSpacesLabel = new Label("Queen of spades:");
        queenOfSpadesFields = new TextField("Yes/NO");
        queenOfSpadesFields.setEditable(false);
        queenOfSpadesFields.setPrefWidth(80);

        bottomPanel.add(sumOfFacesLabel, 0, 0);
        bottomPanel.add(sumOfFacesFields, 1, 0);
        bottomPanel.add(cardsOfHeartsLabel, 2, 0);
        bottomPanel.add(cardsOfHeartsField, 3, 0);
        bottomPanel.add(flushLabel, 0, 1);
        bottomPanel.add(flushField, 1, 1);
        bottomPanel.add(queenOfSpacesLabel, 2, 1);
        bottomPanel.add(queenOfSpadesFields, 3, 1);

        root.setBottom(bottomPanel);

        Scene scene = new Scene(root, 700, 500);
        primaryStage.setTitle("Flush Card Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void initCardDisplayArea() {
        cardDisplayArea = new FlowPane();
        cardDisplayArea.setPrefSize(500, 300);
        cardDisplayArea.setPadding(new Insets(10));
        cardDisplayArea.setHgap(10);
        cardDisplayArea.setVgap(10);
        cardDisplayArea.setAlignment(Pos.CENTER);
    }

    private void checkHand() {
        boolean hasFlush = engine.isFlush();
        if (hasFlush) {
            LOGGER.log(Level.INFO, "-------- Flush! ---------");
        }
        flushField.setText(hasFlush ? "Yes" : "No");
    }

    private void dealCards() {
        int cardsToDraw;
        if (engine.getHandSide() == 0) {
            cardsToDraw = 5;
        } else {
            cardsToDraw = 1;
        }
        engine.drawCards(cardsToDraw);
        LOGGER.log(Level.INFO, "Drawn %d cards!".formatted(cardsToDraw));
    }
}
