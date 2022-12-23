package ba.unsa.etf.rpr.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

/**
 * Controller class for the registration scene.
 *
 */
public class RegistrationController {

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
    public Button cancelButton;
    public Button registerButton;
    public Button registerAndLoginButton;

    public void cancelButtonAction(ActionEvent actionEvent) {
    }

    public void registerButtonAction(ActionEvent actionEvent) {
    }

    public void registerAndLoginButtonAction(ActionEvent actionEvent) {
    }

}
