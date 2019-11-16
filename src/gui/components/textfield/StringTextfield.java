package gui.components.textfield;

import javafx.scene.control.TextField;

public class StringTextfield extends TextField {

    public StringTextfield(String text) {
        this.setPromptText(text);
        this.getStyleClass().add("StringTextfield");
    }

}
