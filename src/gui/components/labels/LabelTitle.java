package gui.components.labels;

import javafx.geometry.Insets;
import javafx.scene.control.Label;

public class LabelTitle extends Label {

    public LabelTitle(String sText) {

        this.setText(sText);
        this.getStyleClass().add("LabelTitle");
        this.setPadding(new Insets(0,0,20,0));

    }

}
