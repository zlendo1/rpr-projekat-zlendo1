package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.auxiliary.SceneLoader;
import ba.unsa.etf.rpr.controller.LoginController;
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
        SceneLoader.load(stage, "login", "Login", new LoginController(), false);
    }

}