package gui.components.buttons;

import javafx.scene.control.Button;

public class GreyButton extends Button {

    public GreyButton(String sText) {
        super(sText);
        this.getStyleClass().add("GreyButton");
    }

    public GreyButton(String sText, int iWidth, int iHeight) {
        this(sText);
        if (iHeight > 0)
            this.setPrefHeight(iHeight);
        if (iWidth > 0)
            this.setPrefWidth(iWidth);
    }
}
