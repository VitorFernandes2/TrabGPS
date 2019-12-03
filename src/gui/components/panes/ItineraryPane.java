package gui.components.panes;

import gui.components.buttons.DarkGreyButton;
import gui.components.buttons.GreyButton;
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
        lvListaDados.setPrefHeight(30 * 24 + 2);

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

        cb.setOnMouseClicked(e -> {

            if (cb.isSelected()){

                cbLocalidade.setDisable(true);

            }else{

                cbLocalidade.setDisable(false);

            }

        });

        btPesquisa.setOnMouseClicked(e -> {
            String sDestino = (String) cbLocalidadeDestino.getValue();
            String sPartida = (String) cbLocalidade.getValue();

            if (cb.isSelected()){
                sPartida = this.logic.getLocalizacaoAtual();
            }

            if (sDestino != null && sPartida != null){
                lvListaDados.getItems().clear();
                HashMap<String, ArrayList<String>> hmDirections = this.logic.getdirection(sPartida, sDestino);
                acrescentaDados(hmDirections.get("Itenerário Recomendado"),1);
                if (hmDirections.get("Itenerário Alternativo").size() > 2)
                    acrescentaDados(hmDirections.get("Itenerário Alternativo"),2);
            }
        });
    }

    /**
     * Esta função acrescenta todos os dados ao histórico, isto permite que o código seja mais limpo
     */
    private void acrescentaDados(List<String> lista, int iopc){
        if (lista.size() > 2){
            
            LsView lsVista = new LsView();
            lsVista.setPrefHeight(6 * 24 + 2);
            HBox hbBoxVista = new HBox(lsVista);
            hbBoxVista.setVisible(false);
            hbBoxVista.managedProperty().bind(hbBoxVista.visibleProperty());

            VBox hbMainBox = new VBox();
            HBox hbLinha = new HBox();

            String sInfolocal = lista.get(lista.size() - 1).split("Dis")[1];
            sInfolocal = "Dis" + sInfolocal;
            
            HBox hbInfoPosto;
            if(iopc == 1)
                hbInfoPosto = new HBox(new Label("Itinerário Principal: " + sInfolocal));
            else
                hbInfoPosto = new HBox(new Label("Itinerário Alternativo: " + sInfolocal));
            //HBox hbInfoPosto = new HBox(new Label("Itinerário Principal :" + sInfolocal));
            hbInfoPosto.setAlignment(Pos.CENTER_LEFT);

            DarkGreyButton bEstado = new DarkGreyButton("Detalhes");

            bEstado.setOnMouseClicked(e ->{
                if(hbBoxVista.visibleProperty().getValue()){
                    hbBoxVista.setVisible(false);
                    hbBoxVista.managedProperty().bind(hbBoxVista.visibleProperty());
                }
                else{
                    hbBoxVista.setVisible(true);
                    hbBoxVista.managedProperty().bind(hbBoxVista.visibleProperty());
                }
            });

            HBox hbInfoDispo = new HBox(bEstado);
            hbInfoDispo.setAlignment(Pos.CENTER_RIGHT);

            hbLinha.getChildren().addAll(hbInfoPosto, hbInfoDispo);
            hbLinha.setHgrow(hbInfoPosto, Priority.ALWAYS);
            hbLinha.setHgrow(hbInfoDispo,Priority.ALWAYS);

            hbMainBox.getChildren().addAll(hbLinha,hbBoxVista);
            hbMainBox.setSpacing(30);
            
            //HBox oculta
            for (int i = 1; i < lista.size() - 1; i++){
                hbBoxVista.setHgrow(lsVista,Priority.ALWAYS);

                //lsVista.getItems().clear();

                HBox hbLinha2 = new HBox();

                HBox hbInfoPosto2 = new HBox(new Label(lista.get(i)));
                hbInfoPosto2.setAlignment(Pos.CENTER_LEFT);

                String tempreserva = lista.get(i);
                
                GreyButton bEstado2 = new GreyButton("Reserva");

                bEstado2.setOnMouseClicked(e -> {
                    this.logic.AddicionarReservaItenerario(tempreserva);
                });
                
                HBox hbInfoDispo2 = new HBox(bEstado2);
                hbInfoDispo2.setAlignment(Pos.CENTER_RIGHT);

                hbLinha2.getChildren().addAll(hbInfoPosto2, hbInfoDispo2);
                hbLinha2.setHgrow(hbInfoPosto2, Priority.ALWAYS);
                hbLinha2.setHgrow(hbInfoDispo2,Priority.ALWAYS);

                lsVista.getItems().add(hbLinha2);
 
            }
            lvListaDados.getItems().add(hbMainBox);
        }
        else{
            lvListaDados.getItems().add(new Label("Não existe nenhum posto no itenerário definido até ao momento."));
        }
    }

    
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        setVisible(this.logic.inItinerario());

        if (this.logic.inItinerario()){

            cbLocalidade.getSelectionModel().clearSelection();
            cbLocalidadeDestino.getSelectionModel().clearSelection();
            cb.setSelected(false);
            lvListaDados.getItems().clear();

        }

    }

}
