package peter;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import peter.controller.MainController;

/**
 * A GUI for Peter using FXML.
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            stage.setTitle("Peter");
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.show();
            MainController controller = fxmlLoader.getController();
            controller.setPeter();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
