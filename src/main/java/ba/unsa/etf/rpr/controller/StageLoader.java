package ba.unsa.etf.rpr.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

/**
 * Auxiliary class for stage loading.
 *
 */
public class StageLoader {

    public static void load(Stage stage, String fileName, String titleName, boolean resizable) throws IOException {
        Parent root = FXMLLoader.load(
                ClassLoader.getSystemResource("/fxml/" + fileName + ".fxml")
        );

        stage.setTitle(titleName);
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.setResizable(resizable);

        stage.show();
    }

}
