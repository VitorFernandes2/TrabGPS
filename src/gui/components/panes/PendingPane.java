package gui.components.panes;

import gui.components.listviews.LsView;
import gui.components.menubars.MainMenuBar;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import logic.E2ULogic;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class PendingPane extends StackPane implements PropertyChangeListener {

    private E2ULogic logic;
    private BorderPane borderPane;
    private VBox mainPanel;
    private Label titleLabel;
    private HBox dataPanel;
    private Label titlePostosLabel;
    private LsView listaDados;
    private MainMenuBar mainMenuBar;

    public PendingPane(E2ULogic logic) {
        this.logic = logic;
        createComponents();
        propertyChange(null);
    }

    private void createComponents() {
        
        ObservableList children = this.getChildren();
        children.add(interfaceA());

    }

    private BorderPane interfaceA(){

        borderPane = new BorderPane();
        mainPanel = new VBox();
        titleLabel = new Label("Pendentes");
        dataPanel = new HBox();
        titlePostosLabel = new Label("Postos");
        listaDados = new LsView();
        mainMenuBar = new MainMenuBar(logic);

        acrescentaDados();

        dataPanel.getChildren().addAll(titlePostosLabel, listaDados);
        mainPanel.getChildren().addAll(titleLabel, dataPanel);
        borderPane.setCenter(mainPanel);
        borderPane.setTop(mainMenuBar);

        return borderPane;

    }

    /**
     * Esta função acrescenta todos os dados ao histórico, isto permite que o código seja mais limpo
     */
    private void acrescentaDados(){



    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

}
