package gui.components.panes;

import gui.Constants;
import gui.components.buttons.DefaultButton;
import gui.components.imageviews.ViewImage;
import gui.components.textfield.PassField;
import gui.components.textfield.StringTextfield;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Hyperlink;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;


public class LoginPane extends StackPane implements Constants {

    DefaultButton bLogin;
    StringTextfield tfUsername;
    PassField pfPalavraPasse;
    Hyperlink hlRegisto;
    ViewImage ivLogo;

    public LoginPane() {
        createComponents();
        registerListeners();
    }

    private void createComponents(){
        bLogin = new DefaultButton("Login");
        tfUsername = new StringTextfield("Nome do Utilizador");
        pfPalavraPasse = new PassField("Password");
        hlRegisto = new Hyperlink("Efetuar Registo");

        ivLogo = new ViewImage("../../" + sLogo);
        HBox hBox = new HBox(ivLogo);
        hBox.setAlignment(Pos.CENTER);
        hBox.setPadding(new Insets(20,0,60,0));

        VBox box = new VBox(hBox,tfUsername,pfPalavraPasse,bLogin,hlRegisto);
        box.setAlignment(Pos.CENTER);
        box.setSpacing(15);

        ObservableList children = this.getChildren();
        children.addAll(box);

    }

    private void registerListeners(){
        bLogin.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

            }
        });
        hlRegisto.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

            }
        });
    }

}
