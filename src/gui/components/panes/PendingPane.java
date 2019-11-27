package gui.components.panes;

import gui.components.buttons.QueryButton;
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
import java.util.List;

public class PendingPane extends StackPane implements PropertyChangeListener {

    private cE2ULogic logic;
    private BorderPane pnBorderPane;
    private VBox pnMainPanel;
    private Label lblTitleLabel;
    private VBox vbDataPanel;
    private LsView lsListaDados;
    private MainMenuBar mmbMenuBar;
    private HBox hbTopBox;

    public PendingPane(cE2ULogic logic) {
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
        pnBorderPane = new BorderPane();
        pnMainPanel = new VBox();
        lblTitleLabel = new LabelTitle("Reservas Pendentes");
        lblTitleLabel.setPadding(new Insets(30,0,0,10));
        lsListaDados = new LsView();
        mmbMenuBar = new MainMenuBar(logic);
        vbDataPanel = new VBox();
        vbDataPanel.setPadding(new Insets(10,10,10,10));

        acrescentaDados(this.logic.getListaPendentes());

        vbDataPanel.getChildren().addAll(lsListaDados);
        pnMainPanel.getChildren().addAll(lblTitleLabel, vbDataPanel);
        pnBorderPane.setCenter(pnMainPanel);
        MenuBar mbRightBar = new MenuBar();
        Menu mLogout = new Menu();
        Label lblLogout = new Label("Logout");

        //Logout
        lblLogout.setOnMouseClicked(e -> {
            this.logic.goToLogin();
        });

        mLogout.setGraphic(lblLogout);
        mbRightBar.getMenus().addAll(mLogout);

        Region rSpacer = new Region();
        rSpacer.setBackground(
                new Background(
                        new BackgroundFill(Color.web("#383838"), CornerRadii.EMPTY, Insets.EMPTY)
                )
        );

        HBox.setHgrow(rSpacer, Priority.SOMETIMES);
        hbTopBox = new HBox(mmbMenuBar, rSpacer, mbRightBar);
        pnBorderPane.setTop(hbTopBox);

        return pnBorderPane;
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

                 QueryButton btCancelar = new QueryButton("Cancelar Reserva");

                 String sItem = lista.get(i);

                btCancelar.setOnMouseClicked(event -> {
                    this.logic.cancelarReservas(sItem);
                });

                HBox hbInfoDispo = new HBox(btCancelar);
                hbInfoDispo.setAlignment(Pos.CENTER_RIGHT);

                hbLinha.getChildren().addAll(hbInfoPosto, hbInfoDispo);
                hbLinha.setHgrow(hbInfoPosto, Priority.ALWAYS);
                hbLinha.setHgrow(hbInfoDispo,Priority.ALWAYS);

                hbMainBox.getChildren().addAll(hbLinha);
                hbMainBox.setHgrow(hbLinha, Priority.ALWAYS);
                hbMainBox.setSpacing(30);

                lsListaDados.getItems().add(hbMainBox);
            }
        }
        else{
            lsListaDados.getItems().add(new Label("Não existe nenhuma reserva feita até ao momento."));
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        setVisible(this.logic.inPendentes());

        if (!this.logic.inRegister() && !this.logic.inLogin()) {
            lsListaDados.getItems().clear();
            acrescentaDados(this.logic.getListaPendentes());
        }
    }

}
