package dev.nheggoe.cardgame.frontend;

import dev.nheggoe.cardgame.backend.card.CardSuit;
import dev.nheggoe.cardgame.backend.card.PlayingCard;
import dev.nheggoe.cardgame.backend.engine.CardGameEngine;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
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
    private final BorderPane root = new BorderPane();
    private final VBox rightPanel = new VBox(20);
    private FlowPane cardDisplayArea;
    private GridPane bottomPanel;

    private TextField remainingCardField;
    private TextField flushCountField;
    private TextField flushField;
    private TextField queenOfSpadesFields;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) {
        root.setPadding(new Insets(20));

        initCardDisplayArea();
        initBottomPanel();

        Text placeholderText = new Text("------ Place Holder ------");
        placeholderText.setFill(Color.GRAY);
        cardDisplayArea.getChildren().add(placeholderText);

        rightPanel.setPadding(new Insets(10));
        rightPanel.setAlignment(Pos.TOP_CENTER);

        Button dealHandButton = new Button("Deal Hand");
        dealHandButton.setOnAction(e -> {
            dealCards();
            updateCardCount();
            updateCardView();
            root.setCenter(cardDisplayArea);
        });

        Button checkHandButton = new Button("Check Hand");
        checkHandButton.setOnAction(e -> {
            checkHand();
        });

        rightPanel.getChildren().addAll(dealHandButton, checkHandButton);

        Label remainingCardLabel = new Label("Card Stock:");
        remainingCardField = new TextField("%d".formatted(engine.getRemainingCardCount()));
        remainingCardField.setEditable(false);
        remainingCardField.setPrefWidth(80);

        Label flushCountLabel = new Label("Flush count:");
        flushCountField = new TextField("%d".formatted(engine.getFlushCount()));
        flushCountField.setEditable(false);
        flushCountField.setPrefWidth(150);

        Label flushLabel = new Label("Flush:");
        flushField = new TextField("Yes/NO");
        flushField.setEditable(false);
        flushField.setPrefWidth(80);

        Label queenOfSpacesLabel = new Label("Queen of spades:");
        queenOfSpadesFields = new TextField("Yes/NO");
        queenOfSpadesFields.setEditable(false);
        queenOfSpadesFields.setPrefWidth(80);

        bottomPanel.add(remainingCardLabel, 0, 0);
        bottomPanel.add(remainingCardField, 1, 0);

        bottomPanel.add(flushCountLabel, 2, 0);
        bottomPanel.add(flushCountField, 3, 0);

        bottomPanel.add(flushLabel, 0, 1);
        bottomPanel.add(flushField, 1, 1);
        // bottomPanel.add(queenOfSpacesLabel, 2, 1);
        // bottomPanel.add(queenOfSpadesFields, 3, 1);

        updateComponents();
        Scene scene = new Scene(root, 700, 500);
        primaryStage.setTitle("Flush Card Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void initBottomPanel() {
        bottomPanel = new GridPane();
        bottomPanel.setHgap(10);
        bottomPanel.setVgap(10);
        bottomPanel.setPadding(new Insets(20, 0, 0, 0));
    }

    private void updateComponents() {
        root.setCenter(cardDisplayArea);
        root.setBottom(bottomPanel);
        root.setRight(rightPanel);
    }

    private void updateCardCount() {
        remainingCardField = new TextField("%d".formatted(engine.getRemainingCardCount()));
        LOGGER.log(Level.INFO, "There are %d cards remaining.".formatted(engine.getRemainingCardCount()));
        remainingCardField.setEditable(false);
        remainingCardField.setPrefWidth(80);
        bottomPanel.add(remainingCardField, 1, 0);
    }

    private void updateFlushCount() {
        flushCountField = new TextField("%d".formatted(engine.getFlushCount()));
        flushCountField.setEditable(false);
        flushCountField.setPrefWidth(150);
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

    private void updateCardView() {
        var hand = engine.getHand();
        initCardDisplayArea();
        hand.getHand().forEach(card -> {
            cardDisplayArea.getChildren().add(createCardView(card));
        });
    }

    private Pane createCardView(PlayingCard card) {
        Pane cardPane = new Pane();
        cardPane.setPrefSize(80, 120);

        Rectangle cardBg = new Rectangle(0, 0, 80, 120);
        cardBg.setFill(Color.WHITE);
        cardBg.setStroke(Color.BLACK);
        cardBg.setArcWidth(10);
        cardBg.setArcHeight(10);

        Text rankText = new Text(10, 30, card.getCardRepresentation());
        rankText.setFont(Font.font("Arial", FontWeight.BOLD, 18));

        if (card.suit() == CardSuit.HEARTS || card.suit() == CardSuit.DIAMONDS) {
            rankText.setFill(Color.RED);
        } else {
            rankText.setFill(Color.BLACK);
        }
        cardPane.getChildren().addAll(cardBg, rankText);
        return cardPane;

    }
}
