package gui.components.buttons;

import javafx.scene.control.Button;

public class QueryButton extends Button {

    public QueryButton(String text) {
        super(text);
        this.getStyleClass().add("QueryButton");
    }

    public QueryButton(String text, int width, int height) {
        this(text);
        if (height > 0)
            this.setPrefHeight(height);
        if (width > 0)
            this.setPrefWidth(width);
    }
}
