package gui.components.panes;

import gui.Constants;
import gui.components.buttons.DefaultButton;
import gui.components.imageviews.ViewImage;
import gui.components.textfield.PassField;
import gui.components.textfield.StringTextfield;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import logic.cE2ULogic;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class LoginPane extends StackPane implements Constants, PropertyChangeListener {

    private cE2ULogic logic;
    private DefaultButton bLogin;
    private StringTextfield tfUsername;
    private PassField pfPalavraPasse;
    private Hyperlink hlRegisto;
    private ViewImage ivLogo;
    private Label lbLabel;

    public LoginPane(cE2ULogic logic) {
        this.logic = logic;
        this.logic.addPropertyChangeListener(this);

        createComponents();
        registerListeners();
        propertyChange(null);
    }

    private void createComponents(){
        bLogin = new DefaultButton("Login");
        tfUsername = new StringTextfield("Nome do Utilizador");
        pfPalavraPasse = new PassField("Password");
        hlRegisto = new Hyperlink("Efetuar Registo");
        lbLabel = new Label("Nome de Utilizador ou Palavra-passe estão incorretos");
        lbLabel.setTextFill(Color.RED);
        lbLabel.setVisible(false);

        ivLogo = new ViewImage("../../" + sLogo);
        HBox hbBox = new HBox(ivLogo);
        hbBox.setAlignment(Pos.CENTER);
        hbBox.setPadding(new Insets(20,0,60,0));

        VBox vbBox = new VBox(hbBox,tfUsername,pfPalavraPasse, lbLabel,bLogin,hlRegisto);
        vbBox.setAlignment(Pos.CENTER);
        vbBox.setSpacing(15);

        ObservableList children = this.getChildren();
        children.addAll(vbBox);

    }

    private void registerListeners(){
        bLogin.setOnMouseClicked(e ->{
            this.logic.Login(tfUsername.getText(), pfPalavraPasse.getText());
            pfPalavraPasse.setNormal();
            tfUsername.setNormal();
            lbLabel.setVisible(false);
            lbLabel.setText("Nome de Utilizador ou Palavra-passe estão incorretos");

            switch (this.logic.getErro()){
                case 1:
                    tfUsername.setError();
                    lbLabel.setText("Utilizador errado!");
                    lbLabel.setVisible(true);
                    break;
                case 2:
                    pfPalavraPasse.setError();
                    lbLabel.setText("Palavra-passe errada!");
                    lbLabel.setVisible(true);
                    break;
                case 3:
                case 4:
                    //Login mal efetuado
                    tfUsername.setError();
                    pfPalavraPasse.setError();
                    lbLabel.setVisible(true);
                    break;

            }

            pfPalavraPasse.clear();

        });
        hlRegisto.setOnMouseClicked(e -> {
            pfPalavraPasse.setNormal();
            pfPalavraPasse.clear();
            tfUsername.setNormal();
            tfUsername.clear();
            lbLabel.setVisible(false);
            this.logic.goToRegister();
        });
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        setVisible(this.logic.inLogin());
    }
}
