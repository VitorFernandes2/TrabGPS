package gui.components.panes;

import gui.Constants;
import gui.components.buttons.GreyButton;
import gui.components.buttons.QueryButton;
import gui.components.labels.LabelTitle;
import gui.components.listviews.LsView;
import gui.components.menubars.MainMenuBar;
import gui.components.textfield.StringTextfield;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import logic.cE2ULogic;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class VehiclePane extends StackPane implements Constants, PropertyChangeListener {

    private cE2ULogic logic;
    private BorderPane bpBorderPane;
    private MainMenuBar mmbMainMenuBar;
    private VBox vbLeftbox;
    private HBox vbTopBox;
    private VBox rightbox;
    private LabelTitle lblPostos;
    private LsView lvList;
    private Button btInserirVeiculos;
    private StringTextfield tfMarca;
    private StringTextfield tfModelo;
    private StringTextfield tfMatricula;
    private StringTextfield tfPotencia;
    private StringTextfield tfAutonomia;

    public VehiclePane(cE2ULogic logic) {
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

        btInserirVeiculos = new GreyButton("Inserir Veiculo");
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

        //Conteúdo

        vbLeftbox = new VBox();
        vbLeftbox.setPadding(new Insets(30,0,0,30));

        LabelTitle lblInserirVeiculos = new LabelTitle("Inserir Veículos:");
        
        Label lblAutonomia = new Label("Autonomia do carro: ");
        lblAutonomia.setFont(Font.font(15));
        tfAutonomia = new StringTextfield("Insira a autonomia do carro");

        Label lblModelo = new Label("Modelo do carro: ");
        lblModelo.setFont(Font.font(15));
        tfModelo = new StringTextfield("Insira o modelo do carro");

        Label lblMatricula = new Label("Matricula do carro: ");
        lblMatricula.setFont(Font.font(15));
        tfMatricula = new StringTextfield("Insira a matricula do carro");

        Label lblPotencia = new Label("Potência do carro: ");
        lblPotencia.setFont(Font.font(15));
        tfPotencia = new StringTextfield("Insira a potência do carro");

        Label lblMarca = new Label("Marca do carro: ");
        lblMarca.setFont(Font.font(15));
        tfMarca = new StringTextfield("Insira a marca do carro");

        vbLeftbox.getChildren().addAll(lblInserirVeiculos, lblMarca, tfMarca,
                lblModelo, tfModelo, lblMatricula,
                tfMatricula, lblPotencia, tfPotencia,
                lblAutonomia,tfAutonomia, btInserirVeiculos);
        vbLeftbox.setSpacing(15);

        rightbox = new VBox();
        rightbox.setPadding(new Insets(100,100,100,100));
        lblPostos = new LabelTitle("Veículos:");

        lvList = new LsView();
        lvList.setPrefHeight(15 * 24 + 2);

        insereDados();

        rightbox.getChildren().addAll(lblPostos, lvList);
        rightbox.setPadding(new Insets(30,30,0,30));

        bpBorderPane.setCenter(rightbox);
        bpBorderPane.setLeft(vbLeftbox);

        return bpBorderPane;
    }

    private void insereDados(){

        for (String sItem : this.logic.getVeiculos()) {
            Label TextPost = new Label(sItem);
            QueryButton testButton = new QueryButton("Eliminar Veículo");
            testButton.setOnMouseClicked(evt -> {

                this.logic.eliminaveiculo(sItem);
                lvList.getItems().clear();
                insereDados();

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

    }

    private void registerListeners() {
            
            btInserirVeiculos.setOnMouseClicked(e -> {
                this.logic.insereVeiculo(
                    tfModelo.getText(),
                    tfMarca.getText(),
                    tfMatricula.getText(),Integer.parseInt(tfPotencia.getText()),Integer.parseInt(tfAutonomia.getText())
                );
                lvList.getItems().clear();
                insereDados();
                tfMarca.setText("");
                tfPotencia.setText("");
                tfMatricula.setText("");
                tfModelo.setText("");
                tfAutonomia.setText("");
            });
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        setVisible(this.logic.inVeiculos());
    }

}
