package gui.components.textfield;

import javafx.scene.control.PasswordField;

public class PassField extends PasswordField {

    public PassField(String text) {

        this.setPromptText(text);
        this.getStyleClass().add("PassField");

    }
}
