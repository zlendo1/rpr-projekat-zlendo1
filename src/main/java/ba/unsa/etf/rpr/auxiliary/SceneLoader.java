package ba.unsa.etf.rpr.auxiliary;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

/**
 * Auxiliary class for stage loading.
 *
 */
public class SceneLoader {

    /**
     * A simple loader for scenes.
     * Implemeneted to avoid spaghetification/repetition.
     *
     * @param stage Stage object on which the scene will be set
     * @param fileName Name of the scene's file
     *                 Must be located in the resources/fxml/ and cannot have the extention included
     * @param titleName Title of the scene
     * @param resizable Boolean value for resizeability
     * @throws IOException In case the fileName is formated incorrectly or the file does not exist
     */
    public static void load(Stage stage, String fileName, String titleName, Object controller, boolean resizable) throws IOException {
        FXMLLoader loader = new FXMLLoader(
                ClassLoader.getSystemResource("fxml/" + fileName + ".fxml")
        );

        loader.setController(controller);

        stage.setTitle(titleName);
        stage.setScene(new Scene(loader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.setResizable(resizable);

        stage.show();
    }

}
