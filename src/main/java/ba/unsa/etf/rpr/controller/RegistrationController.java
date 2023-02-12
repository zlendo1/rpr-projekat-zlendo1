package ba.unsa.etf.rpr.controller;

import ba.unsa.etf.rpr.auxiliary.AlertThrower;
import ba.unsa.etf.rpr.auxiliary.SceneLoader;
import ba.unsa.etf.rpr.business.UserManager;
import ba.unsa.etf.rpr.domain.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller class for the registration scene.
 *
 */
public class RegistrationController {

    // GUI objects
    public TextField usernameField;
    public PasswordField passwordField;
    public PasswordField confirmPasswordField;
    public TextField firstNameField;
    public TextField lastNameField;
    public Text usernameErrorField;
    public Text passwordErrorField;
    public Text confirmPasswordErrorField;
    public Text firstNameErrorField;
    public Text lastNameErrorField;

    // Manager objects
    private final UserManager manager = new UserManager();

    @FXML
    public void initialize() {
        addEmptyFieldError(usernameField, usernameErrorField, "Username field must not be empty");
        addEmptyFieldError(passwordField, passwordErrorField, "Password field must not be empty");
        addEmptyFieldError(firstNameField, firstNameErrorField, "First name field must not be empty");
        addEmptyFieldError(lastNameField, lastNameErrorField, "Last name field must not be empty");

        confirmPasswordField.textProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (!newValue.equals(passwordField.getText())) {
                        confirmPasswordErrorField.setText("Password confirmation must match password");
                    }
                }
        );
    }

    public void cancel(ActionEvent actionEvent) {
        Stage stage = (Stage) usernameField.getScene().getWindow();

        try {
            SceneLoader.load(stage, "login", "Login", new LoginController(), false);
        } catch (IOException e) {
            AlertThrower.throwAlert(e, Alert.AlertType.ERROR);
        }
    }

    public void register(ActionEvent actionEvent) {
        if (manager.existsUser(usernameField.getText())) {
            usernameErrorField.setText("Username occupied");

            return;
        }

        manager.createUser(usernameField.getText(), passwordField.getText(), firstNameField.getText(), lastNameField.getText());

        Stage stage = (Stage) usernameField.getScene().getWindow();

        try {
            SceneLoader.load(stage, "login", "Login", new LoginController(), false);
        } catch (IOException e) {
            AlertThrower.throwAlert(e, Alert.AlertType.ERROR);
        }
    }

    public void registerAndLogin(ActionEvent actionEvent) {
        if (manager.existsUser(usernameField.getText())) {
            usernameErrorField.setText("Username occupied");

            return;
        }

        User user = manager.createUser(usernameField.getText(), passwordField.getText(), firstNameField.getText(), lastNameField.getText());

        Stage stage = (Stage) usernameField.getScene().getWindow();

        try {
            SceneLoader.load(stage, "home", "Home", new HomeController(user), true);
        } catch (IOException e) {
            AlertThrower.throwAlert(e, Alert.AlertType.ERROR);
        }
    }

    private void addEmptyFieldError(TextField textField, Text errorField, String message) {
        textField.textProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue.isEmpty()) {
                        errorField.setText(message);
                    }
                }
        );
    }

}
