package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.controller.SceneLoader;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Hello world!
 *
 */
public class App extends Application {

    public static void main( String[] args ) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        SceneLoader.load(stage, "login", "Login", false);
    }

}