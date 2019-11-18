package gui.components.panes;

import gui.components.buttons.GreyButton;
import gui.components.buttons.QueryButton;
import gui.components.labels.LabelTitle;
import gui.components.listviews.LsView;
import gui.components.menubars.MainMenuBar;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import logic.E2ULogic;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ItineraryPane extends StackPane implements PropertyChangeListener {

    private E2ULogic logic;
    private BorderPane borderPane;
    private MainMenuBar mainMenuBar;
    private VBox leftbox;
    private VBox vBox;
    private LabelTitle lblPesquisa;
    private ChoiceBox cbHorario;
    private HBox hbHorario;
    private ChoiceBox cbLocalidade;
    private VBox rightbox;
    private LabelTitle lblPostos;
    private LsView list;
    private HBox hbLocalidade;
    private Button btPesquisa;
    private Label label;
    private Label label2;
    private Label label3;

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

        return borderPane;

    }

    private void registerListeners(){

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

}
