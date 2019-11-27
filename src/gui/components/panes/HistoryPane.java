package gui.components.panes;

import gui.components.labels.LabelTitle;
import gui.components.listviews.LsView;
import gui.components.menubars.MainMenuBar;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import logic.cE2ULogic;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;

public class HistoryPane extends StackPane implements PropertyChangeListener {

    private cE2ULogic logic;
    private BorderPane bpBorderPane;
    private VBox vbMainPanel;
    private Label lbTitleLabel;
    private VBox vbDataPanel;
    private LsView lvListaDados;
    private MainMenuBar mmbMainMenuBar;
    private HBox hbTopBox;

    public HistoryPane(cE2ULogic logic) {
        this.logic = logic;
        this.logic.addPropertyChangeListener(this);

        createComponents();
        propertyChange(null);
    }

    private void createComponents() {
        ObservableList oblChildren = this.getChildren();
        oblChildren.add(interfaceA());
    }

    private BorderPane interfaceA(){
        bpBorderPane = new BorderPane();
        vbMainPanel = new VBox();
        lbTitleLabel = new LabelTitle("Histórico de Postos Reservados");
        lbTitleLabel.setPadding(new Insets(30,0,0,10));
        vbDataPanel = new VBox();
        vbDataPanel.setPadding(new Insets(10,10,10,10));

        lvListaDados = new LsView();
        mmbMainMenuBar = new MainMenuBar(logic);

        acrescentaDados(this.logic.historico());

        vbDataPanel.getChildren().addAll(lvListaDados);
        vbMainPanel.getChildren().addAll(lbTitleLabel, vbDataPanel);
        bpBorderPane.setCenter(vbMainPanel);
        MenuBar mnbRightBar = new MenuBar();
        Menu mnLogout = new Menu();
        Label lbLogout = new Label("Logout");

        //Logout
        lbLogout.setOnMouseClicked(e -> {
            this.logic.goToLogin();
        });

        mnLogout.setGraphic(lbLogout);
        mnbRightBar.getMenus().addAll(mnLogout);

        Region rSpacer = new Region();
        rSpacer.setBackground(
                new Background(
                        new BackgroundFill(Color.web("#383838"), CornerRadii.EMPTY, Insets.EMPTY)
                )
        );

        HBox.setHgrow(rSpacer, Priority.SOMETIMES);
        hbTopBox = new HBox(mmbMainMenuBar, rSpacer, mnbRightBar);
        bpBorderPane.setTop(hbTopBox);

        return bpBorderPane;
    }

    /**
     * Esta função acrescenta todos os dados ao histórico, isto permite que o código seja mais limpo
     */
    private void acrescentaDados(HashMap<Integer,HashMap<String, String>> sLista){
        if (sLista.size() > 0){
            for (int i = 1; i < sLista.size() + 1; i++) {
                HBox hbMainBox = new HBox();
                HBox hbLinha = new HBox();

                HBox hbInfoPosto = new HBox(new Label(sLista.get(i).get("info")));
                hbInfoPosto.setAlignment(Pos.CENTER_LEFT);

                Label lblEstado = new Label(sLista.get(i).get("estado"));

                if (lblEstado.getText().equals("Efetuada"))
                    lblEstado.setTextFill(Color.GREEN);
                else
                    lblEstado.setTextFill(Color.RED);

                HBox hbInfoDispo = new HBox(lblEstado);
                hbInfoDispo.setAlignment(Pos.CENTER_RIGHT);

                hbLinha.getChildren().addAll(hbInfoPosto, hbInfoDispo);
                hbLinha.setHgrow(hbInfoPosto, Priority.ALWAYS);
                hbLinha.setHgrow(hbInfoDispo,Priority.ALWAYS);

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
        this.setVisible(this.logic.inHistorico());

        if (!this.logic.inRegister() && !this.logic.inLogin()){
            lvListaDados.getItems().clear();
            acrescentaDados(this.logic.historico());
        }
    }
}
