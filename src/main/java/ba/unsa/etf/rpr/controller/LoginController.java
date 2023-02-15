package ba.unsa.etf.rpr.controller;

import ba.unsa.etf.rpr.auxiliary.AlertThrower;
import ba.unsa.etf.rpr.auxiliary.SceneLoader;
import ba.unsa.etf.rpr.business.UserManager;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exception.DBHandleException;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller class for the login scene.
 *
 */
public class LoginController {

    // GUI objects
    public TextField usernameField;
    public PasswordField passwordField;
    public Text errorField;

    // Manager objects
    private final UserManager manager = new UserManager();

    @FXML
    public void initialize() {
        passwordField.textProperty().addListener(
                (observer, oldValue, newValue) -> {
                    errorField.setText("");
                }
        );
    }

    public void cancel(ActionEvent actionEvent) {
        Platform.exit();
        System.exit(0);
    }

    public void register(ActionEvent actionEvent) {
        Stage stage = (Stage) usernameField.getScene().getWindow();

        try {
            SceneLoader.load(stage, "registration", "Register", new RegistrationController(), false);
        } catch (IOException e) {
            AlertThrower.throwAlert(e, Alert.AlertType.ERROR);
        }
    }

    public void login(ActionEvent actionEvent) {
        try {
            User user = manager.findUser(usernameField.getText(), passwordField.getText());

            if (user == null) {
                errorField.setText("User does not exist");

                return;
            }

            Stage stage = (Stage) usernameField.getScene().getWindow();

            SceneLoader.load(stage, "home", "Home", new HomeController(user), true);
        } catch (IOException | DBHandleException e) {
            AlertThrower.throwAlert(e, Alert.AlertType.ERROR);
        }
    }

}
