package peter.controller;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import peter.Peter;

/**
 * Controller for the main GUI.
 */
public class MainController extends AnchorPane {

    private static final String WELCOME_MESSAGE = "Welcome to PETER chatbot!\n"
            + "Type \"instruction\" to know how to use";

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Peter peter;

    private final Image userImage = new Image(Objects.requireNonNull(
            this.getClass().getResourceAsStream("/images/user.png")));
    private final Image peterImage = new Image(Objects.requireNonNull(
            this.getClass().getResourceAsStream("/images/peter.png")));

    /**
     * Initializes the dialog container to automatically scroll to the bottom whenever new content
     * is added.
     */
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(DialogBoxController
                .getPeterDialogFirst(WELCOME_MESSAGE, peterImage));
    }

    /** Injects the Duke instance */
    public void setPeter() {
        peter = new Peter();
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = peter.getResponse(input);
        String commandType = peter.getCommandType();
        dialogContainer.getChildren().addAll(
                DialogBoxController.getUserDialog(input, userImage),
                DialogBoxController.getPeterDialog(response, peterImage, commandType)
        );
        userInput.clear();
        if (response.equals("Bye. PETER chatbot hopes to see you again soon!")) {
            CompletableFuture.delayedExecutor(2, java.util.concurrent.TimeUnit.SECONDS)
                    .execute(Platform::exit);
        }
    }
}
