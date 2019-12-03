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
import javafx.scene.text.Font;
import logic.cE2ULogic;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class QueryPane extends StackPane implements PropertyChangeListener {

    private cE2ULogic logic;
    private BorderPane bpBorderPane;
    private MainMenuBar mmbMainMenuBar;
    private VBox vbLeftbox;
    private HBox vbTopBox;
    private LabelTitle lblPesquisa;
    private ChoiceBox cbHorario;
    private HBox hbHorario;
    private ChoiceBox cbLocalidade;
    private VBox rightbox;
    private LabelTitle lblPostos;
    private LsView lvList;
    private HBox hbLocalidade;
    private Button btPesquisa;
    private Label lbLabel;
    private Label lbLabel2;
    private Label lbLabel3;

    public QueryPane(cE2ULogic logic) {
        this.logic = logic;
        this.logic.addPropertyChangeListener(this);

        createComponents();
        registerListeners();
        propertyChange(null);
    }

    private void createComponents() {
        ObservableList oblChildren = this.getChildren();
        oblChildren.add(interfaceA());
    }

    private BorderPane interfaceA(){
        lbLabel = new Label("Não existem postos nesta região");
        lbLabel.setTextFill(Color.RED);
        lbLabel.setVisible(false);

        lbLabel2 = new Label("Não existem horários para este intervalo");
        lbLabel2.setTextFill(Color.RED);
        lbLabel2.setVisible(false);

        lbLabel3 = new Label("Não existem postos para esta pesquisa");
        lbLabel3.setTextFill(Color.RED);
        lbLabel3.setVisible(false);

        btPesquisa = new GreyButton("Pesquisar");
        bpBorderPane = new BorderPane();
        mmbMainMenuBar = new MainMenuBar(logic);

        MenuBar mbRightBar = new MenuBar();
        Menu mLogout = new Menu();
        Label lbLogout = new Label("Logout");

        //Logout
        lbLogout.setOnMouseClicked(e -> {
            this.logic.goToLogin();
        });

        mLogout.setGraphic(lbLogout);
        mbRightBar.getMenus().addAll(mLogout);

        Region rSpacer = new Region();
        rSpacer.setBackground(
                new Background(
                        new BackgroundFill(Color.web("#383838"), CornerRadii.EMPTY, Insets.EMPTY)
                )
        );

        HBox.setHgrow(rSpacer, Priority.SOMETIMES);
        vbTopBox = new HBox(mmbMainMenuBar, rSpacer, mbRightBar);
        bpBorderPane.setTop(vbTopBox);

        vbLeftbox = new VBox();
        vbLeftbox.setPadding(new Insets(30,0,0,30));

        lblPesquisa = new LabelTitle("Pesquisa:");
        cbHorario = new ChoiceBox(
                FXCollections.observableArrayList(this.logic.getHorarios())
        );

        Label lbHorario = new Label("Horário: ");
        lbHorario.setFont(new Font(15));
        hbHorario = new HBox(lbHorario,cbHorario);
        hbHorario.setPadding(new Insets(0,0,15,0));
        hbHorario.setAlignment(Pos.CENTER_LEFT);

        Label lbLocalidade = new Label("Localidade: ");
        lbLocalidade.setFont(new Font(15));
        cbLocalidade = new ChoiceBox(
                FXCollections.observableArrayList(this.logic.getLocalidades())
        );

        hbLocalidade = new HBox(lbLocalidade, cbLocalidade);
        hbLocalidade.setPadding(new Insets(0,0,15,0));
        hbLocalidade.setAlignment(Pos.CENTER_LEFT);


        vbLeftbox.getChildren().addAll(lblPesquisa, hbHorario, hbLocalidade, btPesquisa, lbLabel, lbLabel2, lbLabel3);

        rightbox = new VBox();
        rightbox.setPadding(new Insets(100,100,100,100));
        lblPostos = new LabelTitle("Postos:");

        lvList = new LsView();
        lvList.setPrefHeight(30 * 24 + 2);

        for (String sItem : this.logic.getPostos()) {
            Label TextPost = new Label(sItem);
            QueryButton testButton = new QueryButton("Reservar");
            testButton.setOnMouseClicked(evt -> {

                this.logic.reserva(sItem);

            });

            HBox hbBox2 = new HBox(TextPost);
            hbBox2.setAlignment(Pos.CENTER_LEFT);
            hbBox2.setHgrow(TextPost, Priority.ALWAYS);

            HBox hbBox4 = new HBox(testButton);
            hbBox4.setAlignment(Pos.CENTER_RIGHT);

            HBox hbMainBox = new HBox(hbBox2);
            hbMainBox.setSpacing(20);
            hbMainBox.setAlignment(Pos.CENTER_LEFT);

            HBox hbBox = new HBox(hbMainBox, hbBox4);
            hbBox.setHgrow(hbMainBox, Priority.ALWAYS);
            hbBox.setSpacing(30);

            lvList.getItems().add(hbBox);
        }

        rightbox.getChildren().addAll(lblPostos, lvList);
        rightbox.setPadding(new Insets(30,30,0,30));

        bpBorderPane.setCenter(rightbox);
        bpBorderPane.setLeft(vbLeftbox);

        return bpBorderPane;
    }

    private void registerListeners(){
        btPesquisa.setOnMouseClicked(e ->{
            lbLabel.setVisible(false);
            lbLabel2.setVisible(false);
            lbLabel3.setVisible(false);
            lvList.getItems().clear();

            for (String sItem : this.logic.infoPostosByPesquisa((String) cbLocalidade.getValue(), (String) cbHorario.getValue())) {

                Label lbTextPost = new Label(sItem);
                QueryButton bTestButton = new QueryButton("Reservar");
                bTestButton.setOnMouseClicked(evt -> {

                    this.logic.reserva(sItem);

                });

                HBox hbBox2 = new HBox(lbTextPost);
                hbBox2.setAlignment(Pos.CENTER_LEFT);
                hbBox2.setHgrow(lbTextPost, Priority.ALWAYS);

                HBox hbBox4 = new HBox(bTestButton);
                hbBox4.setAlignment(Pos.CENTER_RIGHT);

                HBox hbMainBox = new HBox(hbBox2);
                hbMainBox.setSpacing(20);
                hbMainBox.setAlignment(Pos.CENTER_LEFT);

                HBox hbBox = new HBox(hbMainBox, hbBox4);
                hbBox.setHgrow(hbMainBox, Priority.ALWAYS);
                hbBox.setSpacing(30);

                lvList.getItems().add(hbBox);

            }

            switch (this.logic.getErro()){

                case 20:
                    lbLabel3.setVisible(true);
                    break;
                case 21:
                    lbLabel.setVisible(true);
                    break;
                case 22:
                    lbLabel2.setVisible(true);
                    break;
            }

            cbHorario.setValue(null);
            cbLocalidade.setValue(null);

        });
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        lvList.getItems().clear();

        if (!this.logic.inRegister() && !this.logic.inLogin()) {
            for (String sItem : this.logic.getPostos()) {
                Label lbTextPost = new Label(sItem);
                QueryButton bTestButton = new QueryButton("Reservar");

                if (sItem.contains("Indisponivel"))
                    bTestButton.setDisable(true);

                bTestButton.setOnMouseClicked(e -> {
                    this.logic.reserva(sItem);
                });

                HBox hbBox2 = new HBox(lbTextPost);
                hbBox2.setAlignment(Pos.CENTER_LEFT);
                hbBox2.setHgrow(lbTextPost, Priority.ALWAYS);

                HBox hbBox4 = new HBox(bTestButton);
                hbBox4.setAlignment(Pos.CENTER_RIGHT);

                HBox hbMainBox = new HBox(hbBox2);
                hbMainBox.setSpacing(20);
                hbMainBox.setAlignment(Pos.CENTER_LEFT);

                HBox hbBox = new HBox(hbMainBox, hbBox4);
                hbBox.setHgrow(hbMainBox, Priority.ALWAYS);
                hbBox.setSpacing(30);

                lvList.getItems().add(hbBox);
            }
        }
        setVisible(this.logic.inQuery());
    }

}
