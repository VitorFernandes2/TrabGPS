package gui.components.buttons;

import javafx.scene.control.Button;

public class DefaultButton extends Button {

    public DefaultButton(String text) {
        super(text);
        this.getStyleClass().add("DefaultButton");
    }

    public DefaultButton(String text, int width, int height) {
        this(text);
        if (height > 0)
            this.setPrefHeight(height);
        if (width > 0)
            this.setPrefWidth(width);
    }
}
