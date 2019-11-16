package gui.components.labels;

import javafx.geometry.Insets;
import javafx.scene.control.Label;

public class LabelTitle extends Label {

    public LabelTitle(String text) {

        this.setText(text);
        this.getStyleClass().add("LabelTitle");
        this.setPadding(new Insets(0,0,20,0));

    }

}
