package dev.nheggoe.cardgame.frontend;

import dev.nheggoe.cardgame.backend.card.CardSuit;
import dev.nheggoe.cardgame.backend.card.PlayingCard;
import dev.nheggoe.cardgame.backend.engine.CardGameEngine;
import java.util.logging.Level;
import java.util.logging.Logger;
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

  private static final Logger LOGGER = Logger.getLogger(CardGameMain.class.getName());
  private static final CardGameEngine engine = new CardGameEngine();

  private final BorderPane root;
  private final FlowPane centerPanel;
  private final VBox rightPanel;
  private final GridPane bottomPanel;

  private TextField remainingCardField;
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
   * <li>A {@code VBox} for the right panel with a vertical spacing of 20.
   * <li>A {@code GridPane} for the bottom panel.
   */
  public CardGameMain() {
    super();
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

  private void initializeRightPanel() {
    rightPanel.setPadding(new Insets(10));
    rightPanel.setAlignment(Pos.TOP_CENTER);
    rightPanel.getChildren().addAll(getDealHandButton(), getCheckHandButton());
  }

  private void initializeBottomPanel() {
    bottomPanel.setHgap(10);
    bottomPanel.setVgap(10);
    bottomPanel.setPadding(new Insets(20, 0, 0, 0));
    bottomPanel.add(getRemainingCardLabel(), 0, 0);
    bottomPanel.add(remainingCardField, 1, 0);

    bottomPanel.add(getFlushCountLabel(), 2, 0);
    bottomPanel.add(flushCountField, 3, 0);

    bottomPanel.add(getFlushLabel(), 0, 1);
    bottomPanel.add(flushField, 1, 1);
  }

  private Button getCheckHandButton() {
    Button checkHandButton = new Button("Check Hand");
    checkHandButton.setOnAction(e -> checkHand());
    return checkHandButton;
  }

  private Button getDealHandButton() {
    Button dealHandButton = new Button("Deal Hand");
    dealHandButton.setOnAction(
        e -> {
          dealHand();
          updateCardCount();
          updateCardView();
          updateFlushCount();
        });
    return dealHandButton;
  }

  private void initializeStage(Stage primaryStage) {
    primaryStage.setScene(new Scene(root, 700, 500));
    primaryStage.setTitle("Flush Card Game");
    primaryStage.show();
  }

  private void initializeLayout() {
    root.setPadding(new Insets(20));
    root.setCenter(centerPanel);
    root.setBottom(bottomPanel);
    root.setRight(rightPanel);
  }

  private Label getFlushLabel() {
    Label flushLabel = new Label("Flush:");
    flushField = new TextField("Yes/NO");
    flushField.setEditable(false);
    flushField.setPrefWidth(80);
    return flushLabel;
  }

  private Label getFlushCountLabel() {
    Label flushCountLabel = new Label("Flush count:");
    flushCountField = new TextField("%d".formatted(engine.getFlushCount()));
    flushCountField.setEditable(false);
    flushCountField.setPrefWidth(150);
    return flushCountLabel;
  }

  private Label getRemainingCardLabel() {
    Label remainingCardLabel = new Label("Card Stock:");
    remainingCardField = new TextField("%d".formatted(engine.getRemainingCardCount()));
    remainingCardField.setEditable(false);
    remainingCardField.setPrefWidth(80);
    return remainingCardLabel;
  }

  private void updateCardCount() {
    remainingCardField = new TextField("%d".formatted(engine.getRemainingCardCount()));
    LOGGER.log(
        Level.INFO, "There are %d cards remaining.".formatted(engine.getRemainingCardCount()));
    remainingCardField.setEditable(false);
    remainingCardField.setPrefWidth(80);
    bottomPanel.add(remainingCardField, 1, 0);
  }

  private void updateFlushCount() {
    flushCountField = new TextField("%d".formatted(engine.getFlushCount()));
    flushCountField.setEditable(false);
    flushCountField.setPrefWidth(150);
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

  private void checkHand() {
    boolean hasFlush = engine.isFlush();
    if (hasFlush) {
      LOGGER.log(Level.INFO, "-------- Flush! ---------");
    }
    flushField.setText(hasFlush ? "Yes" : "No");
  }

  private void dealHand() {
    engine.newHand();
    int cardsToDraw;
    if (engine.getHandSide() == 0) {
      cardsToDraw = 5;
    } else {
      cardsToDraw = 1;
    }
    try {
      engine.drawCards(cardsToDraw);
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
    LOGGER.log(Level.INFO, "Drawn %d cards!".formatted(cardsToDraw));
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
