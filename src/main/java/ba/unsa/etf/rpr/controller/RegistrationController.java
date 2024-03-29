package ba.unsa.etf.rpr.controller;

import ba.unsa.etf.rpr.auxiliary.AlertThrower;
import ba.unsa.etf.rpr.auxiliary.SceneLoader;
import ba.unsa.etf.rpr.business.UserManager;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exception.DBHandleException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

import static ba.unsa.etf.rpr.auxiliary.AlertThrower.addEmptyTextFieldError;

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
        addEmptyTextFieldError(usernameField, usernameErrorField, "Username field must not be empty");
        addEmptyTextFieldError(passwordField, passwordErrorField, "Password field must not be empty");
        addEmptyTextFieldError(firstNameField, firstNameErrorField, "First name field must not be empty");
        addEmptyTextFieldError(lastNameField, lastNameErrorField, "Last name field must not be empty");

        confirmPasswordField.textProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (!newValue.equals(passwordField.getText())) {
                        confirmPasswordErrorField.setText("Password confirmation must match password");
                    } else {
                        confirmPasswordErrorField.setText("");
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
        try {
            manager.createUser(usernameField.getText(), passwordField.getText(), firstNameField.getText(), lastNameField.getText());

            Stage stage = (Stage) usernameField.getScene().getWindow();

            SceneLoader.load(stage, "login", "Login", new LoginController(), false);
        } catch (IOException | DBHandleException e) {
            AlertThrower.throwAlert(e, Alert.AlertType.ERROR);
        }
    }

    public void registerAndLogin(ActionEvent actionEvent) {
        try {
            User user = manager.createUser(usernameField.getText(), passwordField.getText(), firstNameField.getText(), lastNameField.getText());

            Stage stage = (Stage) usernameField.getScene().getWindow();

            SceneLoader.load(stage, "home", "Home", new HomeController(user), true);
        } catch (IOException | DBHandleException e) {
            AlertThrower.throwAlert(e, Alert.AlertType.ERROR);
        }
    }

}
