package gui.components.panes;

import gui.components.buttons.QueryButton;
import gui.components.labels.LabelTitle;
import gui.components.listviews.LsView;
import gui.components.menubars.MainMenuBar;
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
import java.util.List;

public class PendingPane extends StackPane implements PropertyChangeListener {

    private E2ULogic logic;
    private BorderPane borderPane;
    private VBox mainPanel;
    private Label titleLabel;
    private VBox dataPanel;
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
        titleLabel = new LabelTitle("Reservas Pendentes");
        titleLabel.setPadding(new Insets(30,0,0,10));
        listaDados = new LsView();
        mainMenuBar = new MainMenuBar(logic);
        dataPanel = new VBox();
        dataPanel.setPadding(new Insets(10,10,10,10));

        acrescentaDados(this.logic.getListaPendentes());

        dataPanel.getChildren().addAll(listaDados);
        mainPanel.getChildren().addAll(titleLabel, dataPanel);
        borderPane.setCenter(mainPanel);
        borderPane.setTop(mainMenuBar);

        return borderPane;

    }

    /**
     * Esta função acrescenta todos os dados ao histórico, isto permite que o código seja mais limpo
     */
    private void acrescentaDados(List<String> lista){

        if (lista.size() > 0){
            for (int i = 0; i < lista.size(); i++) {

                HBox mainBox = new HBox();
                HBox linha = new HBox();

                HBox InfoPosto = new HBox(new Label(lista.get(i)));
                InfoPosto.setAlignment(Pos.CENTER_LEFT);

                 QueryButton btCancelar = new QueryButton("Cancelar Reserva");

                btCancelar.setOnMouseClicked(event -> {
                    //Ação para cancelar
                });

                HBox InfoDispo = new HBox(btCancelar);
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

    }

}
