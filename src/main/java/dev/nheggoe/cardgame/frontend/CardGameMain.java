package dev.nheggoe.cardgame.frontend;

import dev.nheggoe.cardgame.backend.card.CardSuit;
import dev.nheggoe.cardgame.backend.card.PlayingCard;
import dev.nheggoe.cardgame.backend.engine.CardGameEngine;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * The JavaFXMain class implements a graphical user interface (GUI) for a card game using JavaFX.
 * This class provides interaction and visualization for managing game states such as dealing cards,
 * checking flushes, and updating the game information dynamically.
 *
 * <p>It extends the JavaFX Application class, initializing the main stage of the application and
 * creating a responsive interface with components such as panels, buttons, and text fields.
 *
 * @author Nick Heggø
 * @version 2025.03.08
 */
public class CardGameMain extends Application {

  private final CardGameEngine engine;
  private final BorderPane root;
  private final FlowPane centerPanel;
  private final VBox rightPanel;
  private final GridPane bottomPanel;

  private TextField remainingCardField;
  private TextField sumOfHandValueField;
  private TextField cardsOfHeartsField;
  private TextField queenOfSpadesField;
  private TextField flushCountField;
  private TextField flushField;

  /**
   * Constructs a new instance of the {@code JavaFXMain} class. This constructor initializes the
   * layout components that form the graphical interface for the application. It sets up the root
   * container and its children, including the center panel, right panel, and bottom panel.
   *
   * <p>The initialized components are:
   * <li>A {@code BorderPane} instance as the root layout.
   * <li>A {@code FlowPane} for the center panel.
   * <li>A {@code VBox} for the right panel with vertical spacing of 20.
   * <li>A {@code GridPane} for the bottom panel.
   */
  public CardGameMain() {
    super();
    engine = new CardGameEngine();
    root = new BorderPane();
    centerPanel = new FlowPane();
    rightPanel = new VBox(20);
    bottomPanel = new GridPane();
  }

  /**
   * The main entry point of the application. This method initializes and launches the JavaFX
   * application by invoking the {@code launch()} method provided by the {@code Application} class.
   * It sets up the required components and starts the user interface for the card game.
   *
   * @param args command-line arguments passed to the application
   */
  public static void main(String[] args) {
    launch();
  }

  @Override
  public void start(Stage primaryStage) {
    initializeCenterPanel();
    initializeRightPanel();
    initializeBottomPanel();
    initializeLayout();
    initializeStage(primaryStage);
  }

  private Button getDealHandButton() {
    Button dealHandButton = new Button("Deal Hand");
    dealHandButton.setOnAction(
        e -> {
          dealHand();
          updateCardView();
          checkHand();
        });
    return dealHandButton;
  }

  private Button getCheckHandButton() {
    Button checkHandButton = new Button("Check Hand");
    checkHandButton.setOnAction(e -> checkHand());
    return checkHandButton;
  }

  private void initializeCenterPanel() {
    centerPanel.setPrefSize(500, 300);
    centerPanel.setPadding(new Insets(10));
    centerPanel.setHgap(10);
    centerPanel.setVgap(10);
    centerPanel.setAlignment(Pos.CENTER);

    Text placeholderText = new Text("------ Place Holder ------");
    placeholderText.setFill(Color.GRAY);
    centerPanel.getChildren().add(placeholderText);
  }

  private void initializeRightPanel() {
    rightPanel.setPadding(new Insets(10));
    rightPanel.setAlignment(Pos.TOP_CENTER);
    rightPanel.getChildren().addAll(getDealHandButton(), getCheckHandButton());
  }

  private void initializeBottomPanel() {
    initializeTextFields();

    bottomPanel.setHgap(10);
    bottomPanel.setVgap(10);
    bottomPanel.setPadding(new Insets(20, 0, 0, 0));

    bottomPanel.add(new Label("Sum of the faces:"), 0, 0);
    bottomPanel.add(sumOfHandValueField, 1, 0);
    bottomPanel.add(new Label("Cards of hearts:"), 2, 0);
    bottomPanel.add(cardsOfHeartsField, 3, 0);
    bottomPanel.add(new Label("Flush count:"), 4, 0);
    bottomPanel.add(flushCountField, 5, 0);

    bottomPanel.add(new Label("Flush:"), 0, 1);
    bottomPanel.add(flushField, 1, 1);
    bottomPanel.add(new Label("Queen of Spades:"), 2, 1);
    bottomPanel.add(queenOfSpadesField, 3, 1);
    bottomPanel.add(new Label("Remaining cards:"), 4, 1);
    bottomPanel.add(remainingCardField, 5, 1);
  }

  private void initializeLayout() {
    root.setPadding(new Insets(20));
    root.setCenter(centerPanel);
    root.setBottom(bottomPanel);
    root.setRight(rightPanel);
  }

  private void initializeStage(Stage primaryStage) {
    primaryStage.setScene(new Scene(root, 700, 500));
    primaryStage.setTitle("Flush Card Game");
    primaryStage.show();
  }

  private void initializeTextFields() {

    sumOfHandValueField = new TextField("0");
    cardsOfHeartsField = new TextField("");
    flushCountField = new TextField("%d".formatted(engine.getFlushCount()));
    flushField = new TextField("Yes/NO");
    queenOfSpadesField = new TextField("Yes/No");
    remainingCardField = new TextField("%d".formatted(engine.getRemainingCardCount()));

    List<TextField> fields =
        new ArrayList<>(
            Arrays.asList(
                sumOfHandValueField,
                cardsOfHeartsField,
                flushCountField,
                flushField,
                queenOfSpadesField,
                remainingCardField));

    fields.forEach(
        field -> {
          field.setEditable(false);
          field.setPrefWidth(120);
        });
  }

  private void checkHand() {
    sumOfHandValueField.setText(String.valueOf(engine.getHandValue()));
    cardsOfHeartsField.setText(
        engine.getHand().getCardsOfSuit(CardSuit.HEARTS).stream()
            .map(PlayingCard::getCardRepresentation)
            .collect(Collectors.joining(" ")));
    flushCountField.setText("%d".formatted(engine.getFlushCount()));
    flushField.setText(engine.isFlush() ? "Yes" : "No");
    queenOfSpadesField.setText(engine.getHand().isQueenOfSpadesPresent() ? "Yes" : "No");
    remainingCardField.setText("%d".formatted(engine.getRemainingCardCount()));
  }

  private void dealHand() {
    engine.getHand().clear();
    try {
      engine.drawCards(5);
    } catch (IllegalStateException e) {
      Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.setTitle("Game Result");
      alert.setHeaderText(
          engine.getFlushCount() == 0 ? "No flush this time :(" : "You have a flush!");
      alert.setContentText("Would you like to play again?");
      alert
          .showAndWait()
          .ifPresent(
              buttonType -> {
                if (buttonType == ButtonType.OK) {
                  engine.startNewGame();
                } else if (buttonType == ButtonType.CANCEL) {
                  Platform.exit();
                }
              });
    }
  }

  private void updateCardView() {
    var hand = engine.getHand();
    centerPanel.getChildren().clear();
    hand.getCards().forEach(card -> centerPanel.getChildren().add(createCardView(card)));
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
