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
import logic.cE2ULogic;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ItineraryPane extends StackPane implements PropertyChangeListener {

    private cE2ULogic logic;
    private BorderPane bpBorderPane;
    private MainMenuBar mnbMainMenuBar;
    private VBox vbLeftbox;
    private ChoiceBox cbLocalidade;
    private ChoiceBox cbLocalidadeDestino;
    private VBox vbCenterbox;
    private Button btPesquisa;
    private LsView lvListaDados;
    private CheckBox cb;
    private HBox hbTopBox;

    public ItineraryPane(cE2ULogic logic) {
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
        bpBorderPane = new BorderPane();
        vbLeftbox = new VBox();
        vbCenterbox = new VBox();
        lvListaDados = new LsView();

        mnbMainMenuBar = new MainMenuBar(logic);

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

        VBox vbBox = new VBox(lbLocalidade, cb, cbLocalidade, lbLocalidade2, cbLocalidadeDestino);
        vbBox.setPadding(new Insets(0,0,15,0));

        vbLeftbox.getChildren().addAll(vbBox, btPesquisa);
        vbLeftbox.setPadding(new Insets(50,0,0,50));
        vbCenterbox.getChildren().addAll(lvListaDados);
        vbCenterbox.setPadding(new Insets(30,30,0,30));

        MenuBar mbRightBar = new MenuBar();
        Menu mnLogout = new Menu();
        Label lbLogout = new Label("Logout");

        //Logout
        lbLogout.setOnMouseClicked(e -> {
            this.logic.goToLogin();
        });

        mnLogout.setGraphic(lbLogout);
        mbRightBar.getMenus().addAll(mnLogout);

        Region rSpacer = new Region();
        rSpacer.setBackground(
                new Background(
                        new BackgroundFill(Color.web("#383838"), CornerRadii.EMPTY, Insets.EMPTY)
                )
        );

        HBox.setHgrow(rSpacer, Priority.SOMETIMES);
        hbTopBox = new HBox(mnbMainMenuBar, rSpacer, mbRightBar);
        bpBorderPane.setTop(hbTopBox);
        bpBorderPane.setCenter(vbCenterbox);
        bpBorderPane.setLeft(vbLeftbox);

        return bpBorderPane;
    }

    private void registerListeners(){
        btPesquisa.setOnMouseClicked(e -> {
            String sDestino = (String) cbLocalidadeDestino.getValue();
            String sPartida = (String) cbLocalidade.getValue();

            if (sDestino != null && sPartida != null){
                lvListaDados.getItems().clear();
                HashMap<String, ArrayList<String>> hmDirections = this.logic.getdirection(sPartida, sDestino);
                acrescentaDados(hmDirections.get("Itenerário Recomendado"));
                acrescentaDados(hmDirections.get("Itenerário Alternativo"));
            }
        });
    }

    /**
     * Esta função acrescenta todos os dados ao histórico, isto permite que o código seja mais limpo
     */
    private void acrescentaDados(List<String> lista){
        if (lista.size() > 0){
            for (int i = 0; i < lista.size(); i++) {
                HBox hbMainBox = new HBox();
                HBox hbLinha = new HBox();

                HBox hbInfoPosto = new HBox(new Label(lista.get(i)));
                hbInfoPosto.setAlignment(Pos.CENTER_LEFT);

                QueryButton btCancelar = new QueryButton("Reservar Posto");

                String sItem = lista.get(i);

                btCancelar.setOnMouseClicked(event -> {
                    this.logic.reserva(sItem);
                });

                HBox InfoDispo = new HBox(btCancelar);
 
                InfoDispo.setAlignment(Pos.CENTER_RIGHT);

                if(i == 0 || i == lista.size()-1){
                    hbLinha.getChildren().addAll(hbInfoPosto);}
                else {
                    hbLinha.getChildren().addAll(hbInfoPosto, InfoDispo);
                }
                hbLinha.setHgrow(hbInfoPosto, Priority.ALWAYS);
                hbLinha.setHgrow(InfoDispo,Priority.ALWAYS);

                hbMainBox.getChildren().addAll(hbLinha);
                hbMainBox.setHgrow(hbLinha, Priority.ALWAYS);
                hbMainBox.setSpacing(30);

                lvListaDados.getItems().add(hbMainBox);
            }
        }
        else{
            lvListaDados.getItems().add(new Label("Não existe nenhuma reserva feita até ao momento."));
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        setVisible(this.logic.inItinerario());
    }

}
