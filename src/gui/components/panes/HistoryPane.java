package gui.components.panes;

import gui.components.labels.LabelTitle;
import gui.components.listviews.LsView;
import gui.components.menubars.MainMenuBar;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import logic.E2ULogic;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class HistoryPane extends StackPane implements PropertyChangeListener {

    private E2ULogic logic;
    private BorderPane borderPane;
    private VBox mainPanel;
    private Label titleLabel;
    private VBox dataPanel;
    private Label titlePostosLabel;
    private LsView listaDados;
    private MainMenuBar mainMenuBar;

    public HistoryPane(E2ULogic logic) {
        this.logic = logic;
        this.logic.addPropertyChangeListener(this);

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
        titleLabel = new LabelTitle("Histórico de Postos Reservados");
        titleLabel.setPadding(new Insets(30,0,0,10));
        dataPanel = new VBox();
        dataPanel.setPadding(new Insets(10,10,10,10));

        listaDados = new LsView();
        mainMenuBar = new MainMenuBar(logic);

        acrescentaDados(this.logic.historico());

        dataPanel.getChildren().addAll(listaDados);
        mainPanel.getChildren().addAll(titleLabel, dataPanel);
        borderPane.setCenter(mainPanel);
        borderPane.setTop(mainMenuBar);

        return borderPane;

    }

    /**
     * Esta função acrescenta todos os dados ao histórico, isto permite que o código seja mais limpo
     */
    private void acrescentaDados(HashMap<Integer,HashMap<String, String>> lista){

        if (lista.size() > 0){
            for (int i = 1; i < lista.size() + 1; i++) {

                HBox mainBox = new HBox();
                HBox linha = new HBox();

                HBox InfoPosto = new HBox(new Label(lista.get(i).get("info")));
                InfoPosto.setAlignment(Pos.CENTER_LEFT);

                Label lblEstado = new Label(lista.get(i).get("estado"));

                if (lblEstado.getText().equals("Efetuada"))
                    lblEstado.setTextFill(Color.GREEN);
                else
                    lblEstado.setTextFill(Color.RED);

                HBox InfoDispo = new HBox(lblEstado);
                InfoDispo.setAlignment(Pos.CENTER_RIGHT);

                linha.getChildren().addAll(InfoPosto, InfoDispo);
                linha.setHgrow(InfoPosto, Priority.ALWAYS);
                linha.setHgrow(InfoDispo,Priority.ALWAYS);

                mainBox.getChildren().addAll(linha);
                mainBox.setHgrow(linha, Priority.ALWAYS);
                mainBox.setSpacing(30);

                listaDados.getItems().add(mainBox);

            }
        }
        else{
            listaDados.getItems().add(new Label("Não existe nenhuma reserva feita até ao momento."));
        }

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        this.setVisible(this.logic.inHistorico());
        listaDados.getItems().clear();
        acrescentaDados(this.logic.historico());
    }
}
