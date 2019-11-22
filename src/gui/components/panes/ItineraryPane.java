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
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import logic.E2ULogic;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class ItineraryPane extends StackPane implements PropertyChangeListener {

    private E2ULogic logic;
    private BorderPane borderPane;
    private MainMenuBar mainMenuBar;
    private VBox leftbox;
    private ChoiceBox cbLocalidade;
    private ChoiceBox cbLocalidadeDestino;
    private VBox centerbox;
    private Button btPesquisa;
    private LsView listaDados;
    private CheckBox cb;
    private HBox topBox;

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
        listaDados = new LsView();

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
        centerbox.getChildren().addAll(listaDados);
        centerbox.setPadding(new Insets(30,30,0,30));

        MenuBar rightBar = new MenuBar();
        rightBar.getMenus().addAll(new Menu("Logout"));

        Region spacer = new Region();
        spacer.setBackground(
                new Background(
                        new BackgroundFill(Color.web("#383838"), CornerRadii.EMPTY, Insets.EMPTY)
                )
        );

        HBox.setHgrow(spacer, Priority.SOMETIMES);
        topBox = new HBox(mainMenuBar, spacer, rightBar);
        borderPane.setTop(topBox);
        borderPane.setCenter(centerbox);
        borderPane.setLeft(leftbox);

        return borderPane;

    }

    private void registerListeners(){

        btPesquisa.setOnMouseClicked(e -> {

            String Destino = (String) cbLocalidadeDestino.getValue();
            String Partida = (String) cbLocalidade.getValue();

            if (Destino != null && Partida != null){

                listaDados.getItems().clear();

                ArrayList<String> directions = this.logic.getdirection(Partida, Destino);

                for (String item : directions){

                    acrescentaDados(directions);

                }

            }

        });

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

                String item = lista.get(i);

                btCancelar.setOnMouseClicked(event -> {
                    this.logic.cancelarReservas(item);
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
        setVisible(this.logic.inItinerario());
    }

}
