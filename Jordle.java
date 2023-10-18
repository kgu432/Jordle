import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.paint.Color;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
/**
 * Jordle is a class with using JavaFX to create a game.
 * This game gives the player 6 tries to guess a 5-letter word.
 * For each guess the player makes, the letters either appear green (correct letter in the correct place),
 * yellow (correct letter in the wrong place), or grey (wrong letter in the wrong place).
 * The goal is to guess the word correctly.
 * @author Katy Gu
 * @version 1.0
 */
public class Jordle extends Application {
    int k = 0;
    GridPane grid = new GridPane();
    Label[] label = new Label[30];
    int count = 0;
    String word = Words.list.get((int) (Math.random() * Words.list.size()));
    GridPane grid2 = new GridPane();
    Button button = new Button();
    Rectangle[] shape = new Rectangle[30];
    int count2 = 0;
    StackPane stackPane = new StackPane(grid2, grid);
    BorderPane borderPane = new BorderPane();
    HBox hBox = new HBox(10);
    Label status = new Label("Try guessing a word!");
    Button restart = new Button("Restart");
    Button instructions = new Button("Instructions");
    Label instructionsLabel = new Label("Welcome to Jordle! Try guessing some Java and OOP terms!\n"
                    + " you have 6 tries to guess a 5-letter word. For each guess the user makes,\n"
                    + "the letters will either appear green (correct letter in the correct place),\n"
                    + "yellow (correct letter in the wrong place), or grey (wrong letter in the wrong place).\n"
                    + "The goal is to guess the word correctly.");
    HBox hBox2 = new HBox(10);
    Label title = new Label("Jordle");
    VBox vBox = new VBox();
    VBox vBox2 = new VBox();
    StackPane secondaryLayout = new StackPane();
    Scene secondScene = new Scene(secondaryLayout, 500, 200);
    Stage newWindow = new Stage();
    Alert alert = new Alert(AlertType.ERROR);
    String alertInfo = "Please enter a 5-letter word.";
    Scene scene = new Scene(borderPane);
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Jordle");
        System.out.println(word);
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                label[count] = new Label("");
                grid.add(label[count], j, i, 1, 1);
                label[count].setFont(new Font("Arial", 30));
                label[count].setAlignment(Pos.CENTER);
                label[count].setMinWidth(30);
                count++;
            }
        }
        grid.setHgap(105);
        grid.setVgap(100);
        grid.setAlignment(Pos.CENTER);
        grid.setGridLinesVisible(false);
        grid2.add(button, 1, 1, 1, 1);

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                shape[count2] = new Rectangle();
                shape[count2].setWidth(100);
                shape[count2].setHeight(100);
                shape[count2].setFill(Color.DARKSLATEGREY);
                shape[count2].setStrokeWidth(5.0);
                shape[count2].setStroke(Color.BLACK);
                grid2.add(shape[count2], j, i, 1, 1);
                count2++;
            }
        }
        grid2.setHgap(30);
        grid2.setVgap(30);
        grid2.setAlignment(Pos.CENTER);
        grid2.setGridLinesVisible(false);

        borderPane.setCenter(stackPane);

        status.setFont(Font.font("Ariel", 20));
        restart.setFont(Font.font("Ariel", 20));
        instructions.setFont(Font.font("Ariel", 20));
        hBox.getChildren().addAll(status, restart, instructions);
        hBox.setAlignment(Pos.CENTER);
        hBox.setPadding(new Insets(10, 0, 15, 0));
        title.setFont(Font.font("Arial", FontWeight.BOLD, 70));
        hBox2.getChildren().addAll(title);
        hBox2.setAlignment(Pos.CENTER);
        hBox2.setPadding(new Insets(15, 0, 10, 0));
        vBox.setPadding(new Insets(0, 30, 0, 0));
        vBox2.setPadding(new Insets(0, 0, 0, 30));
        borderPane.setBottom(hBox);
        borderPane.setTop(hBox2);
        borderPane.setLeft(vBox);
        borderPane.setRight(vBox2);
        borderPane.setPadding(new Insets(0, 0, 10, 0));

        instructions.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                secondaryLayout.getChildren().add(instructionsLabel);

                newWindow.setTitle("Instructions");
                newWindow.setScene(secondScene);
                newWindow.centerOnScreen();
                newWindow.show();
                button.requestFocus();
            }
        }
        );
        alert.setTitle("Error");
        alert.setHeaderText("ERROR");
        alert.setContentText(alertInfo);

        button.requestFocus();
        button.setOnKeyPressed(
            (event) -> {
                if (event.getCode() == KeyCode.DOWN || event.getCode() == KeyCode.UP || event.getCode() == KeyCode.LEFT
                    || event.getCode() == KeyCode.RIGHT) {
                    button.requestFocus();
                }
                if (event.getCode() == KeyCode.BACK_SPACE) {
                    if (k % 5 == 4 && label[k].getText().length() == 1) {
                        label[k].setText("");
                    } else if (k % 5 == 0) {
                        label[k].setText("");
                    } else {
                        label[--k].setText("");
                    }
                } else if ("abcdefghijklmnopqrstuvwxyz".contains(event.getText().toLowerCase())
                    && event.getText().length() == 1) {
                    if (k % 5 == 4) {
                        if (label[k].getText().length() == 0) {
                            label[k].setText(event.getText().toUpperCase());
                        }
                    } else {
                        label[k++].setText(event.getText().toUpperCase());
                    }
                } else if (event.getCode() == KeyCode.ENTER) {
                    if (event.getCode() == KeyCode.ENTER && label[k].getText().length() == 0) {
                        alert.show();
                    } else if (k % 5 == 4) {
                        boolean won = true;
                        for (int i = 0; i < 5; i++) {
                            if (label[k - 4 + i].getText().toLowerCase().equals(word.substring(i, i + 1))) {
                                shape[k - 4 + i].setFill(Color.GREEN);
                            } else if (word.contains(label[k - 4 + i].getText().toLowerCase())) {
                                shape[k - 4 + i].setFill(Color.YELLOW);
                                won = false;
                            } else {
                                shape[k - 4 + i].setFill(Color.GREY);
                                won = false;
                            }
                        }
                        if (won) {
                            status.setText("Congratulations! You've guessed the word!");
                            k = 29;
                        } else {
                            if (k == 29) {
                                status.setText("Game over. The word was " + word + ".");
                            }
                        }
                        k++;
                    }
                }
            }
        );

        restart.setOnAction(
            (event) -> {
                int count4 = 0;
                for (int i = 0; i < 6; i++) {
                    for (int j = 0; j < 5; j++) {
                        label[count4].setText((""));
                        shape[count4].setFill(Color.DARKSLATEGREY);
                        count4++;
                    }
                }
                status.setText("Try guessing a word!");
                word = Words.list.get((int) (Math.random() * Words.list.size()));
                button.requestFocus();
                k = 0;
            }
        );

        primaryStage.setScene(scene);
        primaryStage.show();
    }
    /**
     * Driver class to launch the JavaFX application.
     * @param args Command line argument.
     */
    public static void main(String[] args) {
        launch(args);
    }
}