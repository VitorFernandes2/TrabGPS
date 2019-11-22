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
import logic.E2ULogic;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class QueryPane extends StackPane implements PropertyChangeListener {

    private E2ULogic logic;
    private BorderPane borderPane;
    private MainMenuBar mainMenuBar;
    private VBox leftbox;
    private HBox topBox;
    private LabelTitle lblPesquisa;
    private ChoiceBox cbHorario;
    private HBox hbHorario;
    private ChoiceBox cbLocalidade;
    private VBox rightbox;
    private LabelTitle lblPostos;
    private LsView list;
    private HBox hbLocalidade;
    private Button btPesquisa;
    private Label label;
    private Label label2;
    private Label label3;

    public QueryPane(E2ULogic logic) {
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

        label = new Label("Não existem postos nesta região");
        label.setTextFill(Color.RED);
        label.setVisible(false);

        label2 = new Label("Não existem horários para este intervalo");
        label2.setTextFill(Color.RED);
        label2.setVisible(false);

        label3 = new Label("Não existem postos para esta pesquisa");
        label3.setTextFill(Color.RED);
        label3.setVisible(false);

        btPesquisa = new GreyButton("Pesquisar");
        borderPane = new BorderPane();
        mainMenuBar = new MainMenuBar(logic);

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

        leftbox = new VBox();
        leftbox.setPadding(new Insets(100,0,0,100));

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


        leftbox.getChildren().addAll(lblPesquisa, hbHorario, hbLocalidade, btPesquisa, label, label2, label3);

        rightbox = new VBox();
        rightbox.setPadding(new Insets(100,100,100,100));
        lblPostos = new LabelTitle("Postos:");

        list = new LsView();

        for (String item : this.logic.getPostos()) {

            Label TextPost = new Label(item);
            QueryButton testButton = new QueryButton("Reservar");
            testButton.setOnMouseClicked(evt -> {

                this.logic.reserva(item);

            });

            HBox box2 = new HBox(TextPost);
            box2.setAlignment(Pos.CENTER_LEFT);
            box2.setHgrow(TextPost, Priority.ALWAYS);

            HBox box4 = new HBox(testButton);
            box4.setAlignment(Pos.CENTER_RIGHT);

            HBox mainBox = new HBox(box2);
            mainBox.setSpacing(20);
            mainBox.setAlignment(Pos.CENTER_LEFT);

            HBox box = new HBox(mainBox, box4);
            box.setHgrow(mainBox, Priority.ALWAYS);
            box.setSpacing(30);

            list.getItems().add(box);

        }

        rightbox.getChildren().addAll(lblPostos, list);

        borderPane.setCenter(rightbox);
        borderPane.setLeft(leftbox);

        return borderPane;

    }

    private void registerListeners(){
        btPesquisa.setOnMouseClicked(e ->{
            label.setVisible(false);
            label2.setVisible(false);
            label3.setVisible(false);
            list.getItems().clear();

            for (String item : this.logic.infoPostosByPesquisa((String) cbLocalidade.getValue(), (String) cbHorario.getValue())) {

                Label TextPost = new Label(item);
                QueryButton testButton = new QueryButton("Reservar");
                testButton.setOnMouseClicked(evt -> {

                    this.logic.reserva(item);

                });

                HBox box2 = new HBox(TextPost);
                box2.setAlignment(Pos.CENTER_LEFT);
                box2.setHgrow(TextPost, Priority.ALWAYS);

                HBox box4 = new HBox(testButton);
                box4.setAlignment(Pos.CENTER_RIGHT);

                HBox mainBox = new HBox(box2);
                mainBox.setSpacing(20);
                mainBox.setAlignment(Pos.CENTER_LEFT);

                HBox box = new HBox(mainBox, box4);
                box.setHgrow(mainBox, Priority.ALWAYS);
                box.setSpacing(30);

                list.getItems().add(box);

            }

            switch (this.logic.getErro()){

                case 20:
                    label3.setVisible(true);
                    break;
                case 21:
                    label.setVisible(true);
                    break;
                case 22:
                    label2.setVisible(true);
                    break;
            }

            cbHorario.setValue(null);
            cbLocalidade.setValue(null);

        });

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        setVisible(this.logic.inQuery());
    }

}
