package gui.components.textfield;

import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class StringTextfield extends TextField {

    public StringTextfield(String text) {
        this.setPromptText(text);
        this.getStyleClass().add("StringTextfield");
    }

    public void setError(){
        this.setBorder(new Border(new BorderStroke(Color.RED,
                BorderStrokeStyle.SOLID, new CornerRadii(10), BorderWidths.DEFAULT)));
    }

    public void setNormal(){
        this.setBorder(new Border(new BorderStroke(Color.TRANSPARENT,
                BorderStrokeStyle.SOLID, new CornerRadii(10), BorderWidths.DEFAULT)));
    }

}
