package gui.components.buttons;

import javafx.scene.control.Button;

public class GreyButton extends Button {

    public GreyButton(String text) {
        super(text);
        this.getStyleClass().add("GreyButton");
    }

    public GreyButton(String text, int width, int height) {
        this(text);
        if (height > 0)
            this.setPrefHeight(height);
        if (width > 0)
            this.setPrefWidth(width);
    }
}
