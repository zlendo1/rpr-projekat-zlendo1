package ba.unsa.etf.rpr.controller;

import ba.unsa.etf.rpr.business.UserManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

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
    }

    public void cancel(ActionEvent actionEvent) {
    }

    public void register(ActionEvent actionEvent) {
    }

    public void login(ActionEvent actionEvent) {
    }

}
