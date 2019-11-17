package gui.components.textfield;

import javafx.scene.control.PasswordField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class PassField extends PasswordField {

    public PassField(String text) {

        this.setPromptText(text);
        this.getStyleClass().add("PassField");

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
