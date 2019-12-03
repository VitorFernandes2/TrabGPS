package gui.components.buttons;

import javafx.scene.control.Button;

public class DarkGreyButton extends Button {

    public DarkGreyButton(String sText) {
        super(sText);
        this.getStyleClass().add("DarkGreyButton");
    }

    public DarkGreyButton(String sText, int iWidth, int iHeight) {
        this(sText);
        if (iHeight > 0)
            this.setPrefHeight(iHeight);
        if (iWidth > 0)
            this.setPrefWidth(iWidth);
    }
}
