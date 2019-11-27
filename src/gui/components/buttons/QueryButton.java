package gui.components.buttons;

import javafx.scene.control.Button;

public class QueryButton extends Button {

    public QueryButton(String sText) {
        super(sText);
        this.getStyleClass().add("QueryButton");
    }

    public QueryButton(String sText, int iWidth, int iHeight) {
        this(sText);
        if (iHeight > 0)
            this.setPrefHeight(iHeight);
        if (iWidth > 0)
            this.setPrefWidth(iWidth);
    }
}
