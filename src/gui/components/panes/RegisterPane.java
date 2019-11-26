package gui.components.panes;

import gui.Constants;
import gui.components.buttons.DefaultButton;
import gui.components.imageviews.ViewImage;
import gui.components.textfield.PassField;
import gui.components.textfield.StringTextfield;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import logic.cE2ULogic;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class RegisterPane extends StackPane implements Constants, PropertyChangeListener {

    private cE2ULogic logic;
    private Button bRegisto;
    private StringTextfield tfUsername;
    private Hyperlink hlLogin;
    private ViewImage ivLogo;
    private PassField pfPalavraPasse,pfConfirmaPalavraPasse;
    private Label lErro;

    public RegisterPane(cE2ULogic logic) {
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
            lErro.setText("Utilizador já existe na base de dados");

            switch (this.logic.getErro()){

                case 1:
                    tfUsername.setError();
                    lErro.setText("Insira o nome de utilizador!");
                    lErro.setVisible(true);
                    break;
                case 2:
                    pfPalavraPasse.setError();
                    lErro.setText("Insira a palavra-passe!");
                    lErro.setVisible(true);
                    break;
                case 8:
                    pfConfirmaPalavraPasse.setError();
                    lErro.setText("Insira a confirmação da palavra-passe!");
                    lErro.setVisible(true);
                    break;
                case 6:
                    pfPalavraPasse.setError();
                    pfConfirmaPalavraPasse.setError();
                    lErro.setText("Palavra-passe e confirmação estão diferentes!");
                    lErro.setVisible(true);
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
