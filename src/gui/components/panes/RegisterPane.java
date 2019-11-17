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
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import logic.E2ULogic;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class RegisterPane extends StackPane implements Constants, PropertyChangeListener {

    private E2ULogic logic;
    private Button bRegisto;
    private StringTextfield tfUsername;
    private Hyperlink hlLogin;
    private ViewImage ivLogo;
    private PassField pfPalavraPasse,pfConfirmaPalavraPasse;
    private Label lErro;

    public RegisterPane(E2ULogic logic) {
        this.logic = logic;
        this.logic.addPropertyChangeListener(this);

        createComponents();
        registerListeners();
        propertyChange(null);
    }

    private void createComponents(){
        bRegisto = new DefaultButton("Registar");
        tfUsername = new StringTextfield("Nome do Utilizador");
        pfPalavraPasse = new PassField("Password");
        pfConfirmaPalavraPasse = new PassField("Confirmar Password");
        hlLogin = new Hyperlink("Voltar ao Login");
        lErro = new Label("Utilizador já existe na base de dados");
        lErro.setTextFill(Color.RED);
        lErro.setVisible(false);

        ivLogo = new ViewImage("../../" + sLogo);

        HBox hBox = new HBox(ivLogo);
        hBox.setAlignment(Pos.CENTER);
        hBox.setPadding(new Insets(20,0,60,0));

        VBox box = new VBox(hBox,tfUsername,pfPalavraPasse,pfConfirmaPalavraPasse,lErro,bRegisto, hlLogin);
        box.setAlignment(Pos.CENTER);
        box.setSpacing(15);

        ObservableList children = this.getChildren();
        children.addAll(box);
    }

    private void registerListeners(){
        bRegisto.setOnMouseClicked(e -> {
            this.logic.Register(
                    tfUsername.getText(),
                    pfPalavraPasse.getText(),
                    pfConfirmaPalavraPasse.getText()
            );

            tfUsername.setNormal();
            pfConfirmaPalavraPasse.setNormal();
            pfPalavraPasse.setNormal();
            lErro.setVisible(false);

            switch (this.logic.getErro()){

                case 1:
                    tfUsername.setError();
                    break;
                case 2:
                    pfPalavraPasse.setError();
                    break;
                case 8:
                    pfConfirmaPalavraPasse.setError();
                    break;
                case 6:
                    pfPalavraPasse.setError();
                    pfConfirmaPalavraPasse.setError();
                    break;
                case 5:
                    //Login mal efetuado
                    tfUsername.setError();
                    lErro.setVisible(true);
                    break;
                case 9:
                    tfUsername.clear();
                    pfConfirmaPalavraPasse.clear();
                    pfPalavraPasse.clear();
                    tfUsername.setNormal();
                    pfConfirmaPalavraPasse.setNormal();
                    pfPalavraPasse.setNormal();
                    lErro.setVisible(false);
                    break;

            }

        });
        hlLogin.setOnMouseClicked(e -> {
            tfUsername.clear();
            pfConfirmaPalavraPasse.clear();
            pfPalavraPasse.clear();
            tfUsername.setNormal();
            pfConfirmaPalavraPasse.setNormal();
            pfPalavraPasse.setNormal();
            lErro.setVisible(false);
            this.logic.goToLogin();
        });
    }

    public Pane getRoot() {
        return this;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        setVisible(this.logic.inRegister());
    }
}
