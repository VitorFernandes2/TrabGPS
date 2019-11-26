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
    private Label label;

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
        label = new Label("Nome de Utilizador ou Palavra-passe estão incorretos");
        label.setTextFill(Color.RED);
        label.setVisible(false);

        ivLogo = new ViewImage("../../" + sLogo);
        HBox hBox = new HBox(ivLogo);
        hBox.setAlignment(Pos.CENTER);
        hBox.setPadding(new Insets(20,0,60,0));

        VBox box = new VBox(hBox,tfUsername,pfPalavraPasse,label,bLogin,hlRegisto);
        box.setAlignment(Pos.CENTER);
        box.setSpacing(15);

        ObservableList children = this.getChildren();
        children.addAll(box);

    }

    private void registerListeners(){
        bLogin.setOnMouseClicked(e ->{
            this.logic.Login(tfUsername.getText(), pfPalavraPasse.getText());
            pfPalavraPasse.setNormal();
            tfUsername.setNormal();
            label.setVisible(false);
            label.setText("Nome de Utilizador ou Palavra-passe estão incorretos");

            switch (this.logic.getErro()){
                case 1:
                    tfUsername.setError();
                    label.setText("Utilizador errado!");
                    label.setVisible(true);
                    break;
                case 2:
                    pfPalavraPasse.setError();
                    label.setText("Palavra-passe errada!");
                    label.setVisible(true);
                    break;
                case 3:
                case 4:
                    //Login mal efetuado
                    tfUsername.setError();
                    pfPalavraPasse.setError();
                    label.setVisible(true);
                    break;

            }

            pfPalavraPasse.clear();

        });
        hlRegisto.setOnMouseClicked(e -> {
            pfPalavraPasse.setNormal();
            pfPalavraPasse.clear();
            tfUsername.setNormal();
            tfUsername.clear();
            label.setVisible(false);
            this.logic.goToRegister();
        });
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        setVisible(this.logic.inLogin());
    }
}
