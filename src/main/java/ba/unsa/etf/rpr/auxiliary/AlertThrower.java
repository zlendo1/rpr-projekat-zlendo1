package ba.unsa.etf.rpr.auxiliary;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * A simple class with a static method used for throwing alerts easier with exceptions.
 *
 */
public class AlertThrower {

    public static void throwAlert(Exception e, Alert.AlertType alertType) {
        new Alert(alertType, e.getMessage(), ButtonType.OK).show();
    }

    public static void addEmptyFieldError(TextField textField, Text errorField, String message) {
        textField.textProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue.isEmpty()) {
                        errorField.setText(message);
                    }
                }
        );
    }

}
