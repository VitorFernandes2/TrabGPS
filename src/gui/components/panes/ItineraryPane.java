package gui.components.panes;

import gui.components.buttons.GreyButton;
import gui.components.labels.LabelTitle;
import gui.components.listviews.LsView;
import gui.components.menubars.MainMenuBar;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import logic.E2ULogic;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ItineraryPane extends StackPane implements PropertyChangeListener {

    private E2ULogic logic;
    private BorderPane borderPane;
    private MainMenuBar mainMenuBar;
    private VBox leftbox;
    private ChoiceBox cbLocalidade;
    private ChoiceBox cbLocalidadeDestino;
    private VBox centerbox;
    private Button btPesquisa;
    private LsView lista;
    private CheckBox cb;

    public ItineraryPane(E2ULogic logic) {
        this.logic = logic;
        this.logic.addPropertyChangeListener(this);

        createComponents();
        registerListeners();
        propertyChange(null);
    }

    private void createComponents() {

        ObservableList children = this.getChildren();
        children.add(interfaceA());

    }

    private BorderPane interfaceA(){

        borderPane = new BorderPane();
        leftbox = new VBox();
        centerbox = new VBox();
        lista = new LsView();

        mainMenuBar = new MainMenuBar(logic);

        Label lbLocalidade = new LabelTitle("Partida: ");
        Label lbLocalidade2 = new LabelTitle("Destino: ");
        lbLocalidade2.setPadding(new Insets(15,0,20,0));
        btPesquisa = new GreyButton("Pesquisar");

        cbLocalidade = new ChoiceBox(
                FXCollections.observableArrayList(this.logic.getLocalidades())
        );

        cbLocalidadeDestino = new ChoiceBox(
                FXCollections.observableArrayList(this.logic.getLocalidades())
        );

        cb = new CheckBox("Localização Atual: ");
        cb.setPadding(new Insets(0,0,15,0));

        VBox box = new VBox(lbLocalidade, cb, cbLocalidade, lbLocalidade2, cbLocalidadeDestino);
        box.setPadding(new Insets(0,0,15,0));

        leftbox.getChildren().addAll(box, btPesquisa);
        leftbox.setPadding(new Insets(50,0,0,50));
        centerbox.getChildren().addAll(lista);
        centerbox.setPadding(new Insets(30,30,0,30));

        borderPane.setTop(mainMenuBar);
        borderPane.setCenter(centerbox);
        borderPane.setLeft(leftbox);

        return borderPane;

    }

    private void registerListeners(){

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

}
