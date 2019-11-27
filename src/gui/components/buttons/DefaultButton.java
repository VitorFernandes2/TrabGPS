package gui.components.buttons;

import javafx.scene.control.Button;

public class DefaultButton extends Button {

    public DefaultButton(String iText) {
        super(iText);
        this.getStyleClass().add("DefaultButton");
    }

    public DefaultButton(String iText, int iWidth, int iHeight) {
        this(iText);
        if (iHeight > 0)
            this.setPrefHeight(iHeight);
        if (iWidth > 0)
            this.setPrefWidth(iWidth);
    }
}
