package peter.controller;

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
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Peter peter;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image peterImage = new Image(this.getClass().getResourceAsStream("/images/peter.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Duke instance */
    public void setDuke(Peter p) {
        peter = p;
        String greeting = peter.getGreeting();
        dialogContainer.getChildren().add(DialogBoxController.getPeterDialog(greeting, peterImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = peter.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBoxController.getUserDialog(input, userImage),
                DialogBoxController.getPeterDialog(response, peterImage)
        );
        userInput.clear();
        if (response.contains(" Bye. PETER chatbot hopes to see you again soon!")) {
            javafx.application.Platform.exit();
        }
    }
}
