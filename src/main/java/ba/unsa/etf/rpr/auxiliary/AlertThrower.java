package ba.unsa.etf.rpr.auxiliary;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 * A simple class with a static method used for throwing alerts easier with exceptions.
 *
 */
public class AlertThrower {

    public static void throwAlert(Exception e, Alert.AlertType alertType) {
        new Alert(alertType, e.getMessage(), ButtonType.OK).show();
    }

}
