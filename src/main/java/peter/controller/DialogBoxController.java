package peter.controller;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Represents a dialog box consisting of an ImageView to represent the speaker's face
 * and a label containing text from the speaker.
 */
public class DialogBoxController extends HBox {
    private static final String ADD_COMMAND = "AddCommand";
    private static final String MARK_COMMAND = "MarkCommand";
    private static final String DELETE_COMMAND = "DeleteCommand";
    private static final String UPDATE_COMMAND = "UpdateCommand";
    private static final String UNKNOWN = "Unknown";
    private static final String REPLY_LABEL = "reply-label";
    private static final String ADD_LABEL = "add-label";
    private static final String MARKED_LABEL = "marked-label";
    private static final String DELETE_LABEL = "delete-label";
    private static final String UNKNOWN_LABEL = "unknown-label";
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Represents a dialog box consisting of an ImageView to represent the speaker's face
     * and a label containing text from the speaker.
     */
    public DialogBoxController(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainController.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException("Error loading DialogBox FXML: " + e.getMessage(), e);
        }
        dialog.setText(text);
        displayPicture.setImage(img);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    public void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
        dialog.getStyleClass().add(REPLY_LABEL);
    }

    private void changeDialogStyle(String commandType) {
        switch (commandType) {
        case ADD_COMMAND, UPDATE_COMMAND:
            dialog.getStyleClass().add(ADD_LABEL);
            break;
        case MARK_COMMAND:
            dialog.getStyleClass().add(MARKED_LABEL);
            break;
        case DELETE_COMMAND:
            dialog.getStyleClass().add(DELETE_LABEL);
            break;
        case UNKNOWN:
            dialog.getStyleClass().add(UNKNOWN_LABEL);
            break;
        default:
        }
    }

    public static DialogBoxController getUserDialog(String text, Image img) {
        return new DialogBoxController(text, img);
    }

    public static DialogBoxController getPeterDialogFirst(String text, Image img) {
        var db = new DialogBoxController(text, img);
        db.flip();
        return db;
    }

    public static DialogBoxController getPeterDialog(String text, Image img, String commandType) {
        var db = new DialogBoxController(text, img);
        db.flip();
        db.changeDialogStyle(commandType);
        return db;
    }
}
