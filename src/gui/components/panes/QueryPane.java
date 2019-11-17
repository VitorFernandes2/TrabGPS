package gui.components.panes;

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
import javafx.scene.control.ListView;
import javafx.scene.layout.*;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class QueryPane extends StackPane implements PropertyChangeListener {

    public QueryPane() {
        createComponents();
    }

    private void createComponents() {

        ObservableList children = this.getChildren();
        children.add(interfaceA());

    }

    private BorderPane interfaceA(){

        BorderPane borderPane = new BorderPane();
        MainMenuBar mainMenuBar = new MainMenuBar();

        VBox vBox = new VBox(mainMenuBar);
        borderPane.setTop(vBox);

        VBox leftbox = new VBox();
        leftbox.setPadding(new Insets(100,0,0,100));

        LabelTitle lblPesquisa = new LabelTitle("Pesquisa:");
        ChoiceBox cbLocalidade = new ChoiceBox(FXCollections.observableArrayList(
                "First", "Second", "Third")
        );
        HBox hbLocalidade = new HBox(cbLocalidade);
        hbLocalidade.setPadding(new Insets(0,0,15,0));

        ChoiceBox cbPosto = new ChoiceBox(FXCollections.observableArrayList(
                "First", "Second", "Third")
        );

        leftbox.getChildren().addAll(lblPesquisa, hbLocalidade, cbPosto);

        VBox rightbox = new VBox();
        rightbox.setPadding(new Insets(100,100,100,100));
        LabelTitle lblPostos = new LabelTitle("Postos:");
        //ListView<String> list = new ListView<String>();

        LsView list = new LsView();

        rightbox.getChildren().addAll(lblPostos, list);

        borderPane.setCenter(rightbox);
        borderPane.setLeft(leftbox);

        return borderPane;

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
