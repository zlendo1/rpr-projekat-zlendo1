package ba.unsa.etf.rpr.controller;

import ba.unsa.etf.rpr.auxiliary.SceneLoader;
import ba.unsa.etf.rpr.business.UserManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
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
    }

    public void cancel(ActionEvent actionEvent) {
        Stage stage = (Stage) usernameField.getScene().getWindow();

        try {
            SceneLoader.load(stage, "login", "Login", false);
        } catch (IOException e) {
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }

    public void register(ActionEvent actionEvent) {
    }

    public void registerAndLogin(ActionEvent actionEvent) {
    }

}
